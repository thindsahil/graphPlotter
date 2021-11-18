package ui;

import model.Equation;
import model.EquationList;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.util.Random;

import static java.lang.Math.abs;

public class EquationDrawer extends JPanel {

    private int width = (1920 * 3) / 4;
    private int height = (1080 * 3) / 4;
    private EquationList list;
    private double scale = 40.0;

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
        g.setColor(getRandomColour());
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(2f));

        for (int a = 0; a < list.length(); a++) {
            Equation eq = list.getEquation(a);
            double x;
            double y;
            double nextX;
            double nextY;
            for (int i = 0; i <= width; i++) {
                x = (width / 2.0 - i) / scale;
                y = eq.substituteDouble(x);

                nextX = ((width / 2.0) - (i + 1)) / scale;
                nextY = eq.substituteDouble(nextX);

                if (abs(y - nextY) * scale < height) {
                    Shape l = new Line2D.Double((width / 2.0) + x * scale,
                            (height / 2.0) - y * scale,
                            width / 2.0 + nextX * scale,
                            (height / 2.0) - nextY * scale);

                    g2d.draw(l);
                }


            }
        }

    }

    //this method uses code from:
    // https://stackoverflow.com/questions/4246351/creating-random-colour-in-java
    public Color getRandomColour() {

        //to get rainbow, pastel colors
        Random random = new Random();
        final float hue = random.nextFloat();
        final float saturation = 0.9f; //1.0 for brilliant, 0.0 for dull
        final float luminance = 1.0f; //1.0 for brighter, 0.0 for black

        return Color.getHSBColor(hue, saturation, luminance);
    }


    public void setList(EquationList list) {
        this.list = list;
    }

    public void addEquationToGraph(Equation eq) {
        list.addEquation(eq);
    }

    public void refresh() {
        revalidate();
        repaint();
    }
}
