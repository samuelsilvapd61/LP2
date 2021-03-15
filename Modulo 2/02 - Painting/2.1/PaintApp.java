import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class PaintApp {
    public static void main (String[] args) {
        PaintFrame frame = new PaintFrame();
        frame.setVisible(true);
    }
}

class PaintFrame extends JFrame {
    Rect r1, r2, r3, r4;

    PaintFrame () {
        this.addWindowListener (
            new WindowAdapter() {
                public void windowClosing (WindowEvent e) {
                    System.exit(0);
                }
            }
        );
        this.setTitle("Painting Figures");
        this.setSize(350, 350);
        this.r1 = new Rect(50,50, 100, 30, Color.red, Color.blue);
        this.r2 = new Rect(50, 90, 150, 40, Color.green, Color.yellow);
        this.r3 = new Rect(50, 140, 130, 70, Color.black, Color.white);
        this.r4 = new Rect(50, 220, 160, 60, Color.orange, Color.cyan);
        
    }

    public void paint (Graphics g) {
        super.paint(g);
        this.r1.paint(g);
        this.r2.paint(g);
        this.r3.paint(g);
        this.r4.paint(g);
    }
}

class Rect {
    int x, y;
    int w, h;
    Color contorno;
    Color fundo;

    Rect (int x, int y, int w, int h, Color contorno, Color fundo) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.contorno = contorno;
        this.fundo = fundo;
    }

    void print () {
        System.out.format("Retangulo de tamanho (%d,%d) na posicao (%d,%d).\n",
            this.w, this.h, this.x, this.y);
    }

    void paint (Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(contorno);
        g2d.drawRect(this.x,this.y, this.w,this.h);
        g2d.setColor(fundo);
        
        //Aqui eu faco um conserto de calculo 
        //para que a cor de fundo nao corte a cor do contorno
        g2d.fillRect(x+1, y+1, w-1, h-1);
    }
}
