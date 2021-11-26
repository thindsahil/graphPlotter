package ui;


import model.EquationList;
import model.Event;
import model.EventLog;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

//Represents a frame of the GUI
public class GraphPlotterGUI implements GraphUpdateObserver {

    int frameWidth = (1920 * 3) / 4;
    int frameHeight = (1080 * 3) / 4;

    JFrame frame;
    EquationDrawer equationDrawer;
    MenuPanel menu;
    JButton button;
    GridDrawer gridDrawer;

    //MODIFIES: this
    //EFFECTS: constructs GUI adds every panel to frame
    public GraphPlotterGUI() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Graph Plotter");
        frame.setSize(frameWidth,frameHeight);

        gridDrawer = new GridDrawer(frameWidth,frameHeight);

        menu = new MenuPanel(400, 600);
        menu.addUpdateGraphObserver(this);

        button = new JButton("☰");
        button.setBounds(30, 30, 50, 50);


        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.add(gridDrawer, new Integer(100));
        equationDrawer = new EquationDrawer(new EquationList());
        layeredPane.add(equationDrawer, new Integer(101));
        layeredPane.add(menu, new Integer(102));
        layeredPane.add(button, new Integer(103));


        frame.add(layeredPane);
        frame.setVisible(true);

        button.addActionListener(e -> menu.setVisible(!menu.isVisible()));
        printEventLog();

    }

    //MODIFIES: drawer
    //EFFECTS: sets drawer's equation list to the same one in menu
    @Override
    public void updateGraphs() {
        equationDrawer.setList(menu.getEquationList());
        equationDrawer.refresh();
    }

    //EFFECTS: prints EventLog when window is closed
    private void printEventLog() {
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                for (Event next : EventLog.getInstance()) {
                    System.out.println(next + "\n");

                }
                e.getWindow().dispose();
            }
        });
    }
}
