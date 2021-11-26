package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;

//Represents an GridDrawer
public class GridDrawer extends JPanel {

    private int width;
    private int height;
    private int scale;

    //MODIFIES:
    //EFFECTS: Constructs a grid drawer with given dimensions
    public GridDrawer(int frameWidth, int frameHeight) {
        width = frameWidth;
        height = frameHeight - 5;
        scale = 40;
        setBounds(0, 0, width, height);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setBackground(Color.BLACK);
        drawGrid(g);

    }

    //MODIFIES: this
    //EFFECTS: Draws a grid of squares of size scale
    public void drawGrid(Graphics g) {
        g.setColor(Color.GRAY);

        for (int i = 0; i < scale; i += 1) {
            g.drawLine(0,i * scale,width,i * scale);
            g.drawLine(i * scale,0,i * scale,height);
        }

        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(3f));
        g2d.setColor(Color.LIGHT_GRAY);
        Shape l = new Line2D.Double(0,height / 2.0, width, height / 2.0);
        g2d.draw(l);
        l = new Line2D.Double(width / 2.0, 0, width / 2.0, height);
        g2d.draw(l);
    }


}
