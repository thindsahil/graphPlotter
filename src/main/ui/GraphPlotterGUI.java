package ui;

import model.EquationList;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GraphPlotterGUI {

    //Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
    int frameWidth = (1920 * 3) / 4;
    int frameHeight = (1080 * 3) / 4;

    JFrame frame;

    JLayeredPane layers;
    EquationDrawer drawer;
    EquationPanel menu;
    EquationList list;
    JButton button;

    public GraphPlotterGUI() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Graph Plotter");
        frame.setSize(frameWidth,frameHeight);

        GridDrawer g = new GridDrawer(frameWidth,frameHeight);
        g.setBounds(0,0,frameWidth,frameHeight);

        menu = new EquationPanel(400, 600);


        list = new EquationList();
//        Equation eq1 = new Equation("x^3");
//        Equation eq2 = new Equation("x^2");
//
//        list.addEquation(eq1);
//        list.addEquation(eq2);

        button = new JButton("☰");
        button.setBounds(30, 30, 50, 50);

        layers = new JLayeredPane();
        layers.add(g, new Integer(100));

        drawer = new EquationDrawer(list);

        layers.add(drawer, new Integer(101));
        layers.add(menu, new Integer(102));
        layers.add(button, new Integer(103));

        frame.add(layers);


        frame.setVisible(true);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //updateEquations();
                menu.setVisible(!menu.isVisible());
            }
        });
    }

    public void updateEquations() {
        list = menu.getEquationList();
        drawer.setList(list);
        drawer.refresh();

    }

}
