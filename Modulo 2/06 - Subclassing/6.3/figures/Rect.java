package figures;

import java.awt.*;

public class Rect extends Figure {

    public Rect (int x, int y, int w, int h, Color contorno, Color fundo) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.contorno = contorno;
        this.fundo = fundo;
    }

	@Override
	public void print() {
		System.out.format("Retangulo de tamanho (%d,%d) na posicao (%d,%d).\n",
	            this.w, this.h, this.x, this.y);
		
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(contorno);
        g2d.drawRect(this.x,this.y, this.w,this.h);
        g2d.setColor(fundo);
        
        //Aqui eu faco um conserto de calculo 
        //para que a cor de fundo nao corte a cor do contorno
        g2d.fillRect(x+1, y+1, w-1, h-1);
		
	}




}