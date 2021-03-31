package figures;

import java.awt.*;

public class Pentagon extends Figure {
	private Polygon p = new Polygon();

	public Pentagon (int x, int y, int w, int h, Color contorno, Color fundo) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.contorno = contorno;
        this.fundo = fundo;
        
        p.npoints = 4;
        p.xpoints[0] = (int) (x + w/2);
        p.xpoints[1] = x + w;
        p.xpoints[2] = (int) (x + w*0.75);
        p.xpoints[3] = (int) (x + w*0.25);
        //p.xpoints[4] = x;
        
        p.ypoints[0] = y;
        p.ypoints[1] = (int) (y + h*0.40);
        p.ypoints[2] = y+h;
        p.ypoints[3] = y+h;
        //p.ypoints[4] = (int) (y + h*0.40);
        
        p.addPoint(x, (int) (y + h*0.40));
    }
	
	@Override
	public void print() {
		System.out.format("Pentagono de tamanho (%d,%d) na posicao (%d,%d).\n",
	            this.w, this.h, this.x, this.y);
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(contorno);
        g2d.drawPolygon(p.xpoints, p.ypoints, p.npoints);
        g2d.setColor(fundo);
        g2d.fillPolygon(p);
	}

}
