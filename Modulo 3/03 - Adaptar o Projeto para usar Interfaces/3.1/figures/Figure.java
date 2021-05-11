package figures;

import java.awt.Color;

import ivisible.IVisible;

public abstract class Figure implements IVisible{
	public int x, y, w, h;
	public Color contorno;
    //public abstract void paint (Graphics g);
    
	// Construtor da figura abstrata, que servira de base para as figuras reais
    public Figure (int x, int y, int w, int h, Color contorno) {
		this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.contorno = contorno;
	}
    
    public void drag (int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }

    public boolean clicked (int x, int y) {
        return (this.x<=x && x<=this.x+this.w && this.y<=y && y<=this.y+this.h);
    }
    
}