package figures;

import java.awt.*;

public class Pentagon {
	private int x, y, w, h;
	private Polygon p = new Polygon();
	
	public Pentagon (int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        
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
	
	private void print () {
        System.out.format("Pentagono de tamanho (%d,%d) na posicao (%d,%d).\n",
            this.w, this.h, this.x, this.y);
    }
	
	public void paint (Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        //g2d.draw(p);
        g2d.drawPolygon(p.xpoints, p.ypoints, p.npoints);
    }
}
