package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GraphPlotterGUI extends JPanel {

    Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
    double frameWidth = screenDimension.width * 0.75;
    double frameHeight = screenDimension.height * 0.75;

    public GraphPlotterGUI() {

        JFrame frame = new JFrame();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Graph Plotter");
        frame.setSize((int)frameWidth,(int)frameHeight);

        Grid g = new Grid(frameWidth, frameHeight);
        frame.add(g);


        boolean running = true;

        while (running) {
            if (frame.getBounds().width * 0.75 != frameWidth) {
                frameWidth = frame.getBounds().width;
                frameHeight = frame.getBounds().height;
                frame.add(new Grid(frameWidth, frameHeight));

            }
        }

    }




}
