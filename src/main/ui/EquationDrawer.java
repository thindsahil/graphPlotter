package ui;

import model.Equation;
import model.EquationList;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;

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
        // code from https://stackoverflow.com/questions/4246351/creating-random-colour-in-java
//        int R = (int)(Math.random()*256);
//        int G = (int)(Math.random()*256);
//        int B= (int)(Math.random()*256);
//        Color color = new Color(R, G, B); //random color, but can be bright or dull
//
//        //to get rainbow, pastel colors
//        Random random = new Random();
//        final float hue = random.nextFloat();
//        final float saturation = 0.9f;//1.0 for brilliant, 0.0 for dull
//        final float luminance = 1.0f; //1.0 for brighter, 0.0 for black
//        color = Color.getHSBColor(hue, saturation, luminance);


        g.setColor(Color.GREEN);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(2f));

        for (int a = 0; a < list.length(); a++) {
            Equation eq = list.getEquation(a + 1);
            double x;
            double y;
            double nextX;
            double nextY;
            for (int i = 0; i <= width; i++) {
                x = (width / 2.0 - i) / 40.0;
                y = eq.substituteDouble(x);


                nextX = ((width / 2.0) - (i + 1)) / 40.0;
                nextY = eq.substituteDouble(nextX);

                if (Math.abs(y - nextY) * 40 < height) {
                    Shape l = new Line2D.Double((width / 2.0) + x * 40.0,
                            (height / 2.0) - y * 40.0,
                            width / 2.0 + nextX * 40.0,
                            (height / 2.0) - nextY * 40.0);

                    g2d.draw(l);
                }


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
