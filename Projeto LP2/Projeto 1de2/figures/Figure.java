package figures;

import java.awt.Color;
import java.awt.Graphics;

public abstract class Figure {
	public int x, y, w, h;
	public Color contorno;
    public abstract void paint (Graphics g);
    
    public Figure (int x, int y, int w, int h, Color contorno) {
		this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.contorno = contorno;
	}
}