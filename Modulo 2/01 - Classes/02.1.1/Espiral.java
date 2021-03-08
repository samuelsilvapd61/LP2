public class Espiral {
	int x, y, h, w, numVoltas;
	
	Espiral (int x, int y, int h, int w, int numVoltas) {
		this.x = x;
		this.y = y;
		this.h = h;
		this.w = w;
		this.numVoltas = numVoltas;
	}
	
	void print () {
		System.out.format("Posicao (%d, %d) / Tamanho (%d, %d) / Numero de voltas (%d)", 
				this.x, this.y, this.h, this.w, this.numVoltas);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Espiral e1 = new Espiral(1, 1, 3, 3, 4);
		e1.print();
		
	}

}