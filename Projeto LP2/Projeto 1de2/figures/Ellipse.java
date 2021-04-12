package figures;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Ellipse extends Figure {
	public Color fundo;

	// Construtor da elipse.
    public Ellipse (int x, int y, int w, int h, Color contorno, Color fundo) {
    	super(x, y, w, h, contorno);
        this.fundo = fundo;
    }

    // Aqui e a pintura da elipse.
	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(contorno);
        g2d.draw(new Ellipse2D.Double(this.x,this.y, this.w,this.h));
        g2d.setColor(fundo);
        g2d.fillOval(x, y, w, h);
	}
    
}