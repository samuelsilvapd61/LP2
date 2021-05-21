package ivisible;

import java.awt.Graphics;

// Essa interface serve para podermos implementar os metodos que sao comuns as figuras e botoes. 
public interface IVisible {
	public boolean clicked (int x, int y);
    public void    paint   (Graphics g, boolean focado);

}
