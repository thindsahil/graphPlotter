package ui;


import model.EquationList;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GraphPlotterGUI {

    int frameWidth = (1920 * 3) / 4;
    int frameHeight = (1080 * 3) / 4;

    JFrame frame;

    JLayeredPane layers;
    EquationDrawer drawer;
    EquationPanel menu;
    JButton button;


    public GraphPlotterGUI() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Graph Plotter");
        frame.setSize(frameWidth,frameHeight);



        GridDrawer g = new GridDrawer(frameWidth,frameHeight);
        g.setBounds(0,0,frameWidth,frameHeight);

        menu = new EquationPanel(400, 600);

        menu.addUpdateGraphEventListener(this::updateGraphs);


        button = new JButton("☰");
        button.setBounds(30, 30, 50, 50);

        layers = new JLayeredPane();
        layers.add(g, new Integer(100));

        drawer = new EquationDrawer(new EquationList());

        layers.add(drawer, new Integer(101));
        layers.add(menu, new Integer(102));
        layers.add(button, new Integer(103));

        frame.add(layers);


        frame.setVisible(true);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menu.setVisible(!menu.isVisible());
            }
        });
    }

    public void updateGraphs() {
        drawer.setList(menu.getEquationList());
        drawer.refresh();
    }
}
