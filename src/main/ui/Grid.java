package ui;

import javax.swing.*;
import java.awt.*;

import model.Equation;

public class Grid extends JPanel {

    private int width;
    private int height;

    public Grid(double frameWidth, double frameHeight) {
        width = (int) frameWidth;
        height = (int) frameHeight;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setBackground(Color.BLACK);
        drawGrid(g);
        drawGraph(g);
    }

    public void drawGrid(Graphics g) {
        g.setColor(Color.GRAY);

        for (int i = 0; i < width; i += 20) {
            g.drawLine(i,0,i,height);
            g.drawLine(0,i,width,i);
        }
    }

    public void drawGraph(Graphics g) {
        g.setColor(Color.GREEN);
        Equation eq = new Equation("1/3 x^3-40x^2+200x -40");

        int prevX = -1 * width;
        int prevY = height;
        for (int i = (-1 * width) / 2; i <= width / 2; i++) {
            int y = eq.substitute(i) / 1200;
            g.drawLine(prevX + width / 2,
                    (height / 2) - prevY,
                    i + width / 2,
                    (height / 2) - y);
            prevX = i;
            prevY = y;
        }
    }

}
