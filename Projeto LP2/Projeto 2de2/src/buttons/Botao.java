package buttons;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import figures.Figure;
import ivisible.IVisible;

public class Botao implements IVisible {
	public int idx;
	public Figure fig;
	
	// Construtor do botao.
	// O botao e formado por um inteiro e uma figura.
	public Botao (int idx, Figure fig) {
		this.idx = idx;
		this.fig = fig;
	}
	
	// Metodo para testar se o clique foi no botao.
	@Override
	public boolean clicked(int x, int y) {
		return (fig.x-5<=x && x<=fig.x+fig.w+10 && fig.y-5<=y && y<=fig.y+fig.h+10);
	}

	// Metodo para desenhar o botao.
	@Override
	public void paint(Graphics g, boolean focado) {
		Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.black);
        g2d.drawRect(fig.x-5, fig.y-5, fig.w+10, fig.h+10);
        
        if (focado) {
        	g2d.setColor(Color.gray);
        } else {
        	g2d.setColor(Color.LIGHT_GRAY);
        }
        g2d.fillRect(fig.x-4, fig.y-4, fig.w+9, fig.h+9);
        
        fig.paint(g2d, false);
		
	}

}
