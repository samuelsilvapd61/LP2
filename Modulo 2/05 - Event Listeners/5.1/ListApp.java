import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.*;

import figures.Rect;
import figures.Ellipse;
import figures.Pentagon;

class ListApp {
    public static void main (String[] args) {
        ListFrame frame = new ListFrame();
        frame.setVisible(true);
    }
}

class ListFrame extends JFrame {
    ArrayList<Rect> rs = new ArrayList<Rect>();
    ArrayList<Ellipse> es = new ArrayList<Ellipse>();
    ArrayList<Pentagon> ps = new ArrayList<Pentagon>();
    Random rand = new Random();
    
    ListFrame () {
        this.addWindowListener (
            new WindowAdapter() {
                public void windowClosing (WindowEvent e) {
                    System.exit(0);
                }
            }
        );
        
        this.addKeyListener(
    		new KeyAdapter() {
                public void keyPressed (KeyEvent evt) {
                    if (evt.getKeyChar() == 'r') {
                        int x = rand.nextInt(350);
                        int y = rand.nextInt(350);
                        int w = rand.nextInt(60);
                        int h = rand.nextInt(60);
                        Color contorno = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
                        Color fundo = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
                        rs.add(new Rect(x, y, w, h, contorno, fundo));
                        repaint();  // outer.repaint()
                    } else {
                    	if (evt.getKeyChar() == 'e') {
                    		int x = rand.nextInt(350);
                            int y = rand.nextInt(350);
                            int w = rand.nextInt(60);
                            int h = rand.nextInt(60);
                            Color contorno = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
                            Color fundo = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
                            es.add(new Ellipse(x, y, w, h, contorno, fundo));
                            repaint();  // outer.repaint()
                    	} else {
                    		if (evt.getKeyChar() == 'p') {
                    			int x = rand.nextInt(350);
                                int y = rand.nextInt(350);
                                int w = rand.nextInt(60);
                                int h = rand.nextInt(60);
                                Color contorno = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
                                Color fundo = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
                                ps.add(new Pentagon(x, y, w, h, contorno, fundo));
                                repaint();  // outer.repaint()
                    		}
                    	}
                    }
                }
            }	
        );
     
        this.setTitle("Listas homogeneas");
        this.setSize(450, 450);
    }

    public void paint (Graphics g) {
        super.paint(g);
        for (Rect r: this.rs) {
            r.paint(g);
        }
        for (Ellipse e: this.es) {
            e.paint(g);
        }
        for (Pentagon p: this.ps) {
            p.paint(g);
        }
    }
}