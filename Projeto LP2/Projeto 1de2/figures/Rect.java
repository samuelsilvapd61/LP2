package figures;

import java.awt.*;

public class Rect extends Figure {
	public Color fundo;

	// Construtor do Retangulo vai servir de foco
	public Rect (int x, int y, int w, int h, Color contorno) {
        super(x, y, w, h, contorno);
    }
	
	// Construtor que vai ser retangulos normais 
    public Rect (int x, int y, int w, int h, Color contorno, Color fundo) {
        super(x, y, w, h, contorno);
        this.fundo = fundo;
    }

    // Aqui e a pintura de uma figura rengular.
	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(contorno);
        g2d.drawRect(this.x,this.y, this.w,this.h);
        g2d.setColor(fundo);
        
        //Aqui eu faco um conserto de calculo 
        //para que a cor de fundo nao engula a cor do contorno
        g2d.fillRect(x+1, y+1, w-1, h-1);
		
	}
	
	// Aqui e a pintura do retangulo vermelho de foco.
	public void paintFocus(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(contorno);
        g2d.drawRect(this.x,this.y, this.w,this.h);
		
	}


}