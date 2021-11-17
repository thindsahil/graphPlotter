package ui;


import model.Equation;
import model.EquationList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class EquationPanel extends JPanel implements ActionListener {

    private final int windowWidth;
    private final int windowHeight;
    private final JButton addEquationButton;
    private final EquationList list;
    private JTable eqTable;
    private DefaultTableModel model;
    private final JTextField textField = new JTextField("",10);

    List<UpdateGraphEvent> listeners;

    public EquationPanel(int width, int height) {
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
            String text = textField.getText();
            model.addRow(new Object[]{"y= " + text, "X"});
            list.addEquation(new Equation(text));
            textField.setText("");
            update();
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

        model.addColumn("Equations");
        model.addColumn("");

        eqTable.setPreferredScrollableViewportSize(new Dimension(200, 450));
        eqTable.getColumnModel().getColumn(1).setMaxWidth(20);

        eqTable.setFont(new Font("Cambria", Font.PLAIN, 20));
        eqTable.getTableHeader().setFont(new Font("Cambria", Font.BOLD, 30));
        eqTable.setRowHeight(eqTable.getRowHeight() + 10);


        add(new JScrollPane(eqTable), BorderLayout.SOUTH);

        eqTable.setCellSelectionEnabled(true);
        //eqTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        eqTable.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = eqTable.rowAtPoint(e.getPoint());
                int col = eqTable.columnAtPoint(e.getPoint());

                if (col == 1) {
                    model.removeRow(row);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }


}
