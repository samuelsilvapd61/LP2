import javax.swing.JOptionPane;

class Rect {
	int x, y;
	int w, h;
	
	Rect (int x, int y, int w, int h){
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}
	
	void print () {
		System.out.format("Tamanho(%d,%d) / Posicao(%d,%d) \n",
				this.w, this.h, this.x, this.y);
	}
	
	void area (){
		System.out.format("Area  = %d \n", this.h * this.w);
	}
	
	void drag(int dragx, int dragy) {
		this.x = x+dragx;
		this.y = y+dragy;
	}
}

public class RectApp {
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Rect r1 = new Rect(1, 1, 10, 10);
		r1.print();
		r1.area();
		int dragx = Integer.parseInt(JOptionPane.showInputDialog("Digite um valor de arrasto na direcao X"));
		int dragy = Integer.parseInt(JOptionPane.showInputDialog("Digite um valor de arrasto na direcao Y"));
		r1.drag(dragx, dragy);
		r1.print();
		
	}

}