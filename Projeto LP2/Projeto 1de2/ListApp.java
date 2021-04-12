import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.util.ArrayList;
import java.util.Random;

import figures.*;

class ListApp {
    public static void main (String[] args) {
        ListFrame frame = new ListFrame();
        frame.setVisible(true);
    }
}

class ListFrame extends JFrame {
	// Conjunto de variaveis necessarias
    ArrayList<Figure> figs = new ArrayList<Figure>();
    ArrayList<Rect> cores = new ArrayList<Rect>();
    Random rand = new Random();
    Point posMouse = null;
    Figure focus = null;
    Rect rectFocus = null;
    Ellipse ellipseFocus = new Ellipse(0, 0, 12, 12, Color.red, Color.white);
    boolean bolinhaFocus = false;
    boolean coresFocus = false;
    int distanciaX, distanciaY;

    // Construtor ListFrame()
    ListFrame () {
    	// Configurações iniciais da janela 
        this.setTitle("Projeto 1/2");
        this.setSize(700, 700);
        setLocationRelativeTo(null);
        
        this.addWindowListener (
            new WindowAdapter() {
                public void windowClosing (WindowEvent e) {
                    System.exit(0);
                }
            }
        );
        
        
        // Daqui ate o final do construtor ListFrame() sao os codigos de manipulacao de figuras
        
        // Aqui nos criamos a lista de cores que servira para trocar as cores de contorno e fundo
        // quando houver algum objeto em destaque.
        criaListaCores(this);
        
        // Aqui sao as manipulacoes feitas a partir do clique do mouse
        // Resumidamente, primeiro testamos se o clique foi na paleta de cores,
        // se nao, testamos se o clique foi na bolinha que serve para alterar o tamanho de um desenho
        // se nao, por fim testamos se o clique foi em alguma figura ou em um espaco vazio
        this.addMouseListener(new MouseAdapter() {
        	public void mousePressed (MouseEvent evt) {
        		posMouse = getMousePosition();
        		
        		// Testando se o clique foi na paleta de cores, 
        		// se sim, executa o que e necessario e ignora as outras possibilidades
        		if (focus != null && (cores.get(0).x <= posMouse.x && posMouse.x <= (cores.get(21).x + cores.get(21).w)) &&
        				(cores.get(0).y <= posMouse.y && posMouse.y <= (cores.get(21).y + cores.get(21).h))) {
        			int i = 0;
        			for (Rect cor: cores) {
        		        if ((cor.x <= posMouse.x && posMouse.x <= (cor.x + cor.w)) &&
        		        		(cor.y <= posMouse.y && posMouse.y <= (cor.y + cor.h))) {
        		        	coresFocus = true;
        		        	bolinhaFocus = false;
        		        	figs.remove(focus);
        		        	
        		        	// Aqui nos testamos se o clique foi na parte de cima da paleta, se sim, troca a cor de contorno
        		        	// Se nao, entao foi na parte de baixo e troca a cor de fundo
         		        	if (i <= 10) {                      
                        		focus.contorno = cor.fundo;
                        		figs.add(focus);	
                        	} else {   
                        		if (focus.getClass().getSimpleName().equals("Line")) {
                        			focus.contorno = cor.fundo;                        			
                        		} else if (focus.getClass().getSimpleName().equals("Rect")) {
                        			Rect r  = new Rect(focus.x, focus.y, focus.w, focus.h, focus.contorno, cor.fundo);
                        			focus = r;                        			
                        		} else if (focus.getClass().getSimpleName().equals("Ellipse")) {
                        			Ellipse e = new Ellipse(focus.x, focus.y, focus.w, focus.h, focus.contorno, cor.fundo);
                        			focus = e;                        			
                        		} else {
                        			Pentagon p = new Pentagon(focus.x, focus.y, focus.w, focus.h, focus.contorno, cor.fundo);
                        			focus = p;                       			
                        		}                        		
                        		figs.add(focus);
                        	}
         		        	repaint();
                        	break;
                        }
                        i++;
                    }
        		// Aqui nos testamos se o clique foi na bolinha de foco.
        		// Se sim, nos obtemos a distancia entre a bolinha e o mouse, 
        		// para podermos manipular o arrasto posteriormente.
        		} else if ((ellipseFocus.x <= posMouse.x && posMouse.x <= (ellipseFocus.x + ellipseFocus.w)) &&
                		(ellipseFocus.y <= posMouse.y && posMouse.y <= (ellipseFocus.y + ellipseFocus.h))) {
        			bolinhaFocus = true;
        			coresFocus = false;
        			distanciaX = ellipseFocus.x - posMouse.x;
            		distanciaY = ellipseFocus.y - posMouse.y;
        		
            	// Por fim, se o clique nao foi na paleta de cores, nem na bolinha,
            	// entao nos testamos se o clique foi em uma das figuras, ou em algum espaco vazio.
            	// Se o clique foi numa figura, nos colocamos suas informacoes na variavel "focus",
            	// se não, "focus" fica como null.
                } else {
                	bolinhaFocus = false;
                	coresFocus = false;
                	focus = null;
                	for (Figure fig: figs) {
                        if ( ((fig.x <= posMouse.x && posMouse.x <= (fig.x + fig.w)) && (fig.y <= posMouse.y && posMouse.y <= (fig.y + fig.h))) || 
                        		((fig.x >= posMouse.x && posMouse.x >= (fig.x + fig.w)) && (fig.y >= posMouse.y && posMouse.y >= (fig.y + fig.h)))) {
                        	focus = fig;                              	
                        }
                    }
                	
                	// Se o clique foi numa figura, nos realocamos ela para o final da lista.
                	// Tambem obtemos a distancia entre o mouse e a figura, para poder manipular o arrasto, posteriormente.
            		if (focus != null) {
            			figs.remove(focus);       	
                    	figs.add(focus);
                    	distanciaX = focus.x - posMouse.x;
                		distanciaY = focus.y - posMouse.y;
            		}
            		repaint();
                }
        	}
		});      
        
        // Aqui nos fazemos a manipulacao de arrasto das figuras ou da bolinha de alteracao de tamanho das figuras.
        this.addMouseMotionListener(new MouseMotionAdapter() {
        	public void mouseDragged(MouseEvent evt) {
        		posMouse = getMousePosition();
        		
        		// Se o clique foi na bolinha, nos utilizamos o arrasto do mouse
        		// para alterar o tamanho da figura em questao.
        		if (bolinhaFocus) {     			
        			int novaDistanciaX = ellipseFocus.x - posMouse.x;
        			int novaDistanciaY = ellipseFocus.y - posMouse.y;
        			int arrastoX = distanciaX - novaDistanciaX;
        			int arrastoY = distanciaY - novaDistanciaY;
        			figs.remove(focus);
        			focus.w += arrastoX;
        			focus.h += arrastoY;
        			if (focus.getClass().getSimpleName().equals("Pentagon")) {
            			Pentagon p = (Pentagon) focus;
            			focus = new Pentagon(focus.x, focus.y, focus.w, focus.h, focus.contorno, p.fundo);
            		}
        			figs.add(focus);
        			repaint();  
        			
        		// Se o clique nao foi na bolinha, ha a possibilidade de ter sido numa figura.
        		} else  {
        			// Primeiro verificamos se o clique NAO foi na paleta de cores.
        			if (!coresFocus) {
        				// Agora nos testamos se existe alguma figura focada.
        				// Se sim nos itulizamos o arrasto do mouse para alterar a posicao da figura em questao.
        				if (focus != null) {             			        			
                    		figs.remove(focus);
                    		focus.x = posMouse.x + distanciaX;
                    		focus.y = posMouse.y + distanciaY;
                    		if (focus.getClass().getSimpleName().equals("Pentagon")) {
                    			Pentagon p = (Pentagon) focus;
                    			focus = new Pentagon(focus.x, focus.y, focus.w, focus.h, focus.contorno, p.fundo);
                    		}
                        	figs.add(focus); 
                    		repaint();	
                		}
        			}
        			
        		}	
        	}
		});
        
        // Aqui nos criamos figuras novas ou removemos alguma que esteja focada
        // a partir das teclas que foram pressionadas no teclado.
        this.addKeyListener(new KeyAdapter() {
        	public void keyPressed (KeyEvent evt) {
        		
        		// Aqui nos damos configuracoes iniciais para uma figura nova sempre que qualquer tecla e pressionada.
        		// A figura tera a posicao do mouse no momento do pressionamento da tecla.
        		// A figura tera tamanhos fixos.
        		// A figura tera cores de contorno e fundo aleatorias, podendo ser trocadas posteriormente pelo usuario.
        		posMouse = getMousePosition();
        		int x = posMouse.x;
                int y = posMouse.y;
                int w = 70;
                int h = 70;
                Color contorno = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
                Color fundo = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
                
                // Qual figura sera desenhada depende de qual tecla for pressionada.
                // L para Linha.
                // R para Retangulo.
                // E para Ellipse.
                // P para Pentagono.
                // Delete para remover uma figura que esteja em foco.
                if (evt.getKeyChar() == 'l') {
                    figs.add(new Line(x, y, w, 0, contorno));
                    focus = figs.get(figs.size()-1);
                } else if (evt.getKeyChar() == 'r') {
                	figs.add(new Rect(x, y, w, h, contorno, fundo));
                	focus = figs.get(figs.size()-1);                	
                } else if (evt.getKeyChar() == 'e') {
                	figs.add(new Ellipse(x, y, w, h, contorno, fundo));
                	focus = figs.get(figs.size()-1);               	
                } else if (evt.getKeyChar() == 'p') {
                	figs.add(new Pentagon(x, y, w, h, contorno, fundo));
                	focus = figs.get(figs.size()-1);               	
                } else if (evt.getKeyCode() == 127) {
                	figs.remove(focus);
                	focus = null;
                	rectFocus = null;
                }
                repaint();
        	}
		});

    }

    // Aqui nos pintamos a janela desde o inicio sempre que essa funcao "repaint()" e chamada.
    public void paint (Graphics g) {
        super.paint(g);
        
        // Aqui sao pintadas as figuras
        for (Figure fig: this.figs) {
            fig.paint(g);
        }
        
        // Aqui nos testamos se existe alguma figura em foco.
        // Se sim, nos pintamos o quadrado vermelho e a bolinha que indicam o foco de algum objeto.
        // Tambem pintamos a paleta de cores.
        if (focus != null) {
        	rectFocus = new Rect(focus.x -4, focus.y -4, focus.w + 8, focus.h + 8, Color.red);
        	rectFocus.paintFocus(g);
        	ellipseFocus.x = rectFocus.x + rectFocus.w - 8;
        	ellipseFocus.y = rectFocus.y + rectFocus.h - 8;
        	ellipseFocus.paint(g);
        	
        	// Aqui nos pintamos a paleta de cores.
        	for (Rect cor: cores) {
                cor.paint(g);
            }
        }
    }
    
    // Aqui e criada a lista de cores da paleta de cores.
    // Ela e uma lista de retangulos, sendo a fileira de cima para trocar o contorno e a de baixo o fundo de uma figura.
    // Essa funcao e chamada apenas uma vez, apenas para criar a lista.
    void criaListaCores(JFrame janela) {
    	cores.clear();
    	cores.add(new Rect(10, janela.getHeight()-100, 30, 30, Color.black, Color.black));
    	cores.add(new Rect(40, janela.getHeight()-100, 30, 30, Color.black, Color.white));
    	cores.add(new Rect(70, janela.getHeight()-100, 30, 30, Color.black, Color.gray));
    	cores.add(new Rect(100, janela.getHeight()-100, 30, 30, Color.black, Color.green));
    	cores.add(new Rect(130, janela.getHeight()-100, 30, 30, Color.black, Color.blue));
    	cores.add(new Rect(160, janela.getHeight()-100, 30, 30, Color.black, Color.pink));
    	cores.add(new Rect(190, janela.getHeight()-100, 30, 30, Color.black, Color.yellow));
    	cores.add(new Rect(220, janela.getHeight()-100, 30, 30, Color.black, Color.orange));
    	cores.add(new Rect(250, janela.getHeight()-100, 30, 30, Color.black, Color.red));
    	cores.add(new Rect(280, janela.getHeight()-100, 30, 30, Color.black, new Color(150, 75, 0)));
    	cores.add(new Rect(310, janela.getHeight()-100, 30, 30, Color.black, new Color(148, 0, 211)));
    	
    	cores.add(new Rect(10, janela.getHeight()-70, 30, 30, Color.black, Color.black));
    	cores.add(new Rect(40, janela.getHeight()-70, 30, 30, Color.black, Color.white));
    	cores.add(new Rect(70, janela.getHeight()-70, 30, 30, Color.black, Color.gray));
    	cores.add(new Rect(100, janela.getHeight()-70, 30, 30, Color.black, Color.green));
    	cores.add(new Rect(130, janela.getHeight()-70, 30, 30, Color.black, Color.blue));
    	cores.add(new Rect(160, janela.getHeight()-70, 30, 30, Color.black, Color.pink));
    	cores.add(new Rect(190, janela.getHeight()-70, 30, 30, Color.black, Color.yellow));
    	cores.add(new Rect(220, janela.getHeight()-70, 30, 30, Color.black, Color.orange));
    	cores.add(new Rect(250, janela.getHeight()-70, 30, 30, Color.black, Color.red));
    	cores.add(new Rect(280, janela.getHeight()-70, 30, 30, Color.black, new Color(150, 75, 0)));
    	cores.add(new Rect(310, janela.getHeight()-70, 30, 30, Color.black, new Color(148, 0, 211)));
    }
}