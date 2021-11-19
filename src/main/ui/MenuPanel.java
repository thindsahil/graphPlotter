package ui;

import model.Equation;
import model.EquationList;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

//This class uses the idea of getting updates from another class show in this video:
// https://www.youtube.com/watch?v=T7c6lzbeFHE
public class MenuPanel extends JPanel implements ActionListener {

    private final int windowWidth;
    private final int windowHeight;
    private final JButton addEquationButton;
    private EquationList list;
    private JTable eqTable;
    private DefaultTableModel model;
    private final JTextField textField = new JTextField("");
    private final PersistencePanel persistencePanel;

    List<UpdateGraphEvent> listeners;

    public MenuPanel(int width, int height) {
        windowWidth = width;
        windowHeight = height;
        listeners = new ArrayList<>();
        setBorder(BorderFactory.createEmptyBorder(60,10,10,10));
        setBounds(20, 20, 400, 600);
        setLayout(new BorderLayout(5, 5));
        addEquationButton = new JButton("Add");
        add(textField, BorderLayout.CENTER);
        add(addEquationButton, BorderLayout.EAST);
        addEquationButton.addActionListener(this);
        persistencePanel = new PersistencePanel();
        persistencePanel.addLoadEquationsEventListener(this::loadEquations);

        add(persistencePanel, BorderLayout.WEST);

        setupEquationTable();

        list = new EquationList();
        textField.setFont(new Font("Cambria", Font.PLAIN, 26));

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);

    }

    public void draw(Graphics g) {
        g.setColor(Color.GRAY);
        g.fillRect(0,0, windowWidth, windowHeight);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addEquationButton) {
            //Color randColor = getRandomColour();
            String text = textField.getText();
            model.addRow(new Object[]{"y=", text, "X"});
            list.addEquation(new Equation(text));
            textField.setText("");
            update();
            persistencePanel.setList(list);
        }

    }

    public EquationList getEquationList() {
        return list;
    }

    public void addUpdateGraphEventListener(UpdateGraphEvent listener) {
        listeners.add(listener);
    }

    public void update() {
        for (UpdateGraphEvent listener : listeners) {
            listener.updateGraphs();
        }
    }



    private void setupEquationTable() {
        model = new DefaultTableModel();
        eqTable = new JTable(model);

        model.addColumn("");
        model.addColumn("Equations");
        model.addColumn("");

        eqTable.setPreferredScrollableViewportSize(new Dimension(200, 450));
        eqTable.getColumnModel().getColumn(0).setMaxWidth(30);
        eqTable.getColumnModel().getColumn(2).setMaxWidth(20);

        eqTable.setFont(new Font("Cambria", Font.PLAIN, 20));
        eqTable.getTableHeader().setFont(new Font("Cambria", Font.BOLD, 30));
        eqTable.setRowHeight(eqTable.getRowHeight() + 10);

        add(new JScrollPane(eqTable), BorderLayout.SOUTH);

        eqTable.setCellSelectionEnabled(true);

        setupEquationTableListeners();

    }

    private void setupEquationTableListeners() {
        model.addTableModelListener(e -> {
            if (e.getType() == TableModelEvent.UPDATE && e.getType() == TableModelEvent.DELETE) {
                int row = e.getLastRow();
                String newEq = (String) model.getValueAt(row, 1);

                list.updateEquation(row, new Equation(newEq));
                update();
                persistencePanel.setList(list);
            }
        });

        eqTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = eqTable.rowAtPoint(e.getPoint());
                int col = eqTable.columnAtPoint(e.getPoint());

                if (col == 2) {
                    model.removeRow(row);
                    list.removeEquation(row);
                    update();
                    persistencePanel.setList(list);
                }
            }
        });
    }

    public void loadEquations() {
        model.getDataVector().removeAllElements();
        model.fireTableRowsDeleted(0,list.length());

        EquationList savedList = persistencePanel.getList();
        list = savedList;
        for (int i = 0; i < savedList.length(); i++) {
            String eq = savedList.getEquation(i).getEquation();
            model.addRow(new Object[]{"y=", eq, "X"});
        }
        update();

    }


}
