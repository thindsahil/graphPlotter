package ui;

import model.Equation;
import model.EquationList;

import javax.swing.*;
import java.awt.*;

public class EquationDrawer extends JPanel {

    private int width = (1920 * 3) / 4;
    private int height = (1080 * 3) / 4;
    private EquationList list;

    public EquationDrawer(EquationList list) {
        this.list = list;
        setOpaque(false);
        setBounds(0, 0, width, height);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawGraph(g);

    }

    public void drawGraph(Graphics g) {
        g.setColor(Color.RED);

        for (int a = 0; a < list.length(); a++) {
            Equation eq = list.getEquation(a + 1);
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

    public void setList(EquationList list) {
        this.list = list;
    }

    public void refresh() {
        revalidate();
        repaint();
    }
}
