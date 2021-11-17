package ui;

import javax.swing.*;
import java.awt.*;

public class GridDrawer extends JPanel {

    private int width;
    private int height;

    public GridDrawer(int frameWidth, int frameHeight) {
        width = frameWidth;
        height = frameHeight;

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setBackground(Color.BLACK);
        drawGrid(g);

    }

    public void drawGrid(Graphics g) {
        g.setColor(Color.GRAY);

        for (int i = 0; i < width; i += 20) {
            g.drawLine(i,0,i,height);
            g.drawLine(0,i,width,i);
        }
    }


}
