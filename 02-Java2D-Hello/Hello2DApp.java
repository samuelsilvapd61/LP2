import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.QuadCurve2D;


import javax.swing.*;

public class Hello2DApp {
    public static void main (String[] args) {
        Hello2DFrame frame = new Hello2DFrame();
        frame.setVisible(true);
    }
}

class Hello2DFrame extends JFrame {
    public Hello2DFrame () {
        this.addWindowListener (
            new WindowAdapter() {
                public void windowClosing (WindowEvent e) {
                    System.exit(0);
                }
            }
        );
        this.setTitle("Java2D - Hello World!");
        this.setSize(500, 500);
        this.getContentPane().setBackground(Color.cyan);
    }

    public void paint (Graphics g) {
        super.paint(g);
        g.drawString("Retangulo", 50, 50);      
        Graphics2D g2d = (Graphics2D) g;
        g2d.setPaint(Color.red);
        g2d.drawRect(50, 60, 200, 100);
        
        g2d.setPaint(Color.black);
        g2d.drawString("Carinha feliz", 100, 250);
        g2d.setPaint(Color.red);
        g2d.draw(new Ellipse2D.Double(100, 290, 20, 20));
        g2d.draw(new Ellipse2D.Double(150, 290, 20, 20));
        g2d.draw(new QuadCurve2D.Float(90, 330, 135, 350, 180, 330));
    }
}