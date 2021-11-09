package medium;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import medium.*;

public class Display extends JPanel {

    private int scale;
    private int scaledSizeX, scaledSizeY;
    private JFrame frame;
    private Medium medium;
    private MouseEvent mouseEvent;
    public static Display display;
    private Color[][] output;

    public Display(Medium medium, int scale) {
        this.scale = scale;
        this.scaledSizeX = medium.getSizeX() * scale;
        this.scaledSizeY = medium.getSizeY() * scale;
        this.output = new Color[scaledSizeX][scaledSizeY];
        this.medium = medium;
        instantiate();
        display = this;
    }

    public void draw(JFrame freme) {
        freme.repaint();
    }

    private JFrame getFrame() {
        return frame;
    }

    public void update() {
        draw(getFrame());
    }

    public void paint(Graphics g) {
        /* Background Color */
        g.setColor(Medium.DEFAULT_COLOR);
        g.fillRect(0, 0, scaledSizeX, scaledSizeY + 2);

        this.output = Medium.getMedium().getOutput();

        /* Draw Medium */
        for (int xn = 0; xn < scaledSizeX; xn += scale) {
            for (int yn = scaledSizeY - 1; yn >= 0; yn -= scale) {
                int x = xn / scale, y = yn / scale;
                if (output[x][y] != null) {
                    g.setColor(output[x][y]);
                    g.fillRect(xn, yn, scale, scale);
                }
            }
        }
    }

    @Override
    public java.awt.Point getMousePosition() throws HeadlessException {
        java.awt.Point mousePosition = super.getMousePosition();
        if (mousePosition == null) return null;
        mousePosition.x /= scale;
        mousePosition.y /= scale;
        return mousePosition;
    }

    
    private void instantiate() {
        this.frame = new JFrame("Medium");
        frame.getContentPane().add(this);
        frame.setSize(scaledSizeX + 12, scaledSizeY + 40);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        getFrame().addMouseListener(new MouseListener() {
            @Override
            public void mousePressed(MouseEvent e) {
                Medium.getMedium().processClick(e, true);
                mouseEvent = e;
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                Medium.getMedium().processClick(e, false);
                mouseEvent = null;
            }

            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        });
    }
}