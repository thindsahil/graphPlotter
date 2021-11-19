package ui;

import model.Equation;
import model.EquationList;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Random;

import static java.lang.Math.abs;

//Represents an Equation Drawer
public class EquationDrawer extends JPanel {

    private int width = (1920 * 3) / 4;
    private int height = (1080 * 3) / 4;
    private EquationList list;
    private double scale = 40.0;
    private ArrayList<Color> colors = new ArrayList<>();

    //MODIFIES: this,
    //EFFECTS: Constructs an equation drawer with given list, makes the drawing transparent
    //         ands sets the bounds
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

    //MODIFIES: this
    //EFFECTS: Draws all equations in list onto the panel
    public void drawGraph(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(2f));

        for (int a = 0; a < list.length(); a++) {
            if (a + 1 > colors.size()) {
                colors.add(getRandomColour());
            }
            g.setColor(colors.get(a));

            Equation eq = list.getEquation(a);

            for (int i = 0; i <= width; i++) {

                double x = (width / 2.0 - i) / scale;
                double y = eq.substituteDouble(x);

                double nextX = ((width / 2.0) - (i + 1)) / scale;
                double nextY = eq.substituteDouble(nextX);

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
    //EFFECTS: returns a random vibrant colour
    public Color getRandomColour() {

        //to get rainbow, pastel colors
        Random random = new Random();
        final float hue = random.nextFloat();
        final float saturation = 0.9f; //1.0 for brilliant, 0.0 for dull
        final float luminance = 1.0f; //1.0 for brighter, 0.0 for black

        return Color.getHSBColor(hue, saturation, luminance);
    }

    //MODIFIES: list
    //EFFECTS: sets the field list to given list
    public void setList(EquationList list) {
        this.list = list;
    }

    //EFFECTS: Redraws everything on the panel
    public void refresh() {
        revalidate();
        repaint();
    }
}
