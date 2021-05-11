package figures;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Line extends Figure {
	
	// Contrutor da linha.
	// A LINHA NAO TEM COR DE FUNDO.
	public Line (int x, int y, int w, int h, Color contorno) {
		super(x, y, w, h, contorno);
	}

	// Aqui e a pintura da linha.
	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(contorno);
        g2d.drawLine(x, y, x+w, y+h);
        
		
	}

}
