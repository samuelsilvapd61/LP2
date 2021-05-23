import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import buttons.Botao;

import java.util.ArrayList;
import java.util.Random;

import figures.*;

import java.io.*;

class ListApp {
    public static void main (String[] args) {
        ListFrame frame = new ListFrame();
        frame.setVisible(true);
    }
}

class ListFrame extends JFrame {
	// Conjunto de variaveis necessarias
    private ArrayList<Figure> figs = new ArrayList<Figure>();
    private ArrayList<Rect> cores = new ArrayList<Rect>();
    private ArrayList<Botao> botoes = new ArrayList<Botao>();
    private Botao botaoFocus = null;
    private Random rand = new Random();
    private Point posMouse = null;
    private Figure focus = null;
    private Rect rectFocus = null;
    private Ellipse ellipseFocus = new Ellipse(0, 0, 12, 12, Color.red, Color.white);
    private boolean bolinhaFocus = false;
    private boolean coresFocus = false;

    // Construtor ListFrame()
    ListFrame () {
    	// Configurações iniciais da janela 
        this.setTitle("Projeto LP2");
        this.setSize(700, 700);
        setLocationRelativeTo(null);
        
        // Aqui acontece uma tentativa de abrir um arquivo ja existente com algum estado salvo do programa
        // Se o arquivo nao existir, o programa inicia normalmente.
        try {
        	FileInputStream f = new FileInputStream("projeto.bin");
        	ObjectInputStream o = new ObjectInputStream(f);
        	this.figs = (ArrayList<Figure>) o.readObject();
        	o.close();
        } catch (Exception x) {
        	System.out.println("Erro ao tentar abrir o arquivo!");
        }
        
        // Aqui nos salvamos o estado atual da execucao do programa em um arquivo, 
        // para que esse estado possa ser recuperado da proxima vez que o programa for executado.
        this.addWindowListener (
            new WindowAdapter() {
                public void windowClosing (WindowEvent e) {
                	try {
                		FileOutputStream f = new FileOutputStream("projeto.bin");
                		ObjectOutputStream o = new ObjectOutputStream(f);
                		o.writeObject(figs);
                		o.flush();
                		o.close();
                	} catch (Exception x) {
                		System.out.println("Erro ao tentar gravar o arquivo!");
                	}
                	
                    System.exit(0);
                }
            }
        );
        
        //Inicializacao da lista de botoes
        botoes.add(new Botao(1, new Line(20, 40, 35, 35, Color.black, Color.black)));
        botoes.add(new Botao(2, new Rect(20, 85, 35, 35, Color.black, Color.black)));
        botoes.add(new Botao(3, new Ellipse(20, 130, 35, 35, Color.black, Color.black)));
        botoes.add(new Botao(4, new Pentagon(20, 175, 35, 35, Color.black, Color.black)));
        
        
        // Daqui ate o final do construtor ListFrame() sao os codigos de manipulacao de figuras 
        
        // Aqui estao codigos do programa que sao executados a partir do clique do mouse.
        this.addMouseListener(new MouseAdapter() {
        	public void mousePressed (MouseEvent evt) {
        		posMouse = getMousePosition();
        		
        		// Testando se o clique foi em algum dos botoes.
        		if ((15 <= posMouse.x && posMouse.x <= 60) && (35 <= posMouse.y && posMouse.y <= 215)) {
        			bolinhaFocus = false;
                	coresFocus = false;
                	focus = null;
        			for (Botao botao: botoes) {
        				if (botao.clicked(posMouse.x, posMouse.y)) {
        					botaoFocus = botao;
        				}       				       				
        			}
        			repaint();
        		} else {
        			// Testando se o clique foi na paleta de cores.
            		if (focus != null && (cores.get(0).x <= posMouse.x && posMouse.x <= (cores.get(21).x + cores.get(21).w)) &&
            				(cores.get(0).y <= posMouse.y && posMouse.y <= (cores.get(21).y + cores.get(21).h))) {
            			coresFocus = true;
    		        	bolinhaFocus = false;
    		        	figs.remove(focus);      			
            			int i = 0;
            			for (Rect cor: cores) {
            		        if (cor.clicked(posMouse.x, posMouse.y)) {      		        	       		     
            		        	// Aqui nos testamos se o clique foi na parte de cima da paleta, se sim, troca a cor de contorno.
            		        	// Se nao, entao foi na parte de baixo, entao troca a cor de fundo.
             		        	if (i <= 10) {                      
                            		focus.contorno = cor.fundo;
                            		figs.add(focus);	
                            	} else {   
                            		// Para trocar a cor de fundo, precisamos testar primeiro se a figura e uma linha,
                            		// pois na pratica, a linha so tem 1 cor, e eu considerei que essa cor e a cor de fundo.
                            		// Se nao for uma linha, usamos polimorfismo para trocar a cor de fundo da figura.
                            		if (focus.getClass().getSimpleName().equals("Line")) {
                            			focus.contorno = cor.fundo;                        			
                            		} else {
                            			focus.fundo = cor.fundo;
                            		}                		
                            		figs.add(focus);
                            	}
             		        	repaint();
                            	break;
                            }
                            i++;
                        }
            		// Aqui nos testamos se o clique foi na bolinha de foco.
            		} else if (ellipseFocus.clicked(posMouse.x, posMouse.y)) {
            			bolinhaFocus = true;
            			coresFocus = false;
                    } else {
                    	bolinhaFocus = false;
                    	coresFocus = false;
                    	focus = null;
                    	
                    	// Aqui testamos se um dos botoes esta selecionado.
                    	// Se sim, cria a figura na posicao onde for dado um clique.
                    	if (botaoFocus != null) {
                    		int idx = botaoFocus.idx;
                    		if (idx == 1) {                  			
                    			figs.add(new Line(posMouse.x, posMouse.y, 70, 0, Color.black, Color.white));                           
                    		} else if (idx == 2) {
                    			figs.add(new Rect(posMouse.x, posMouse.y, 70, 70, Color.black, Color.white));
                    		} else if (idx == 3) {
                    			figs.add(new Ellipse(posMouse.x, posMouse.y, 70, 70, Color.black, Color.white));
                    		} else {
                    			figs.add(new Pentagon(posMouse.x, posMouse.y, 70, 70, Color.black, Color.white));
                    		}
                    		focus = figs.get(figs.size()-1);
                    		botaoFocus = null;
                    		
                    	// Por fim, testamos se o clique foi em alguma figura ja existente.
                    	} else {
                    		for (Figure fig: figs) {
                                if (fig.clicked(posMouse.x, posMouse.y)) {                   
                                	focus = fig;                              	
                                }
                            }
                    	}
                    	
                    	// Se o clique foi numa figura, nos realocamos ela para o final da lista.
                		if (focus != null) {
                			figs.remove(focus);       	
                        	figs.add(focus);
                		} else {
                			// Se nao foi numa figura, redefinimos a localizacao da bolinha de foco 
                			// para uma posicao onde nao e possivel clicar.
                			ellipseFocus.x = -10;
                        	ellipseFocus.y = -10;
                		}
                		repaint();
                    }
        		}
        	}
		});      
        
        // Aqui nos fazemos a manipulacao de arrasto das figuras ou da bolinha de alteracao de tamanho das figuras.
        this.addMouseMotionListener(new MouseMotionAdapter() {
        	public void mouseDragged(MouseEvent evt) {
        		// Sempre que o mouse é arrastado nos obtemos a nova possicao dele.
        		Point posNovaMouse = getMousePosition();
        		
        		// Se o clique foi na bolinha, nos utilizamos o arrasto do mouse
        		// para alterar o tamanho da figura em questao.
        		if (bolinhaFocus) {     			
        			figs.remove(focus);

        			// Aqui nos alteramos o tamanho W-H das figuras usando como base o valor de arrasto do mouse.
        			// O arrasto do mouse é igual a posicao nova do mouse menos a posicao antiga.
        			focus.w += posNovaMouse.x - posMouse.x;
        			focus.h += posNovaMouse.y - posMouse.y;
        			
        			// Aqui e necessario saber se a figura e um pentagono,
        			// pois para alterar o tamanho de um pentagono e necessario instanciar ele, 
        			// para que sejam feitos os calculos das posicoes dos 5 pontos.
        			if (focus.getClass().getSimpleName().equals("Pentagon")) {
            			Pentagon p = (Pentagon) focus;
            			focus = new Pentagon(focus.x, focus.y, focus.w, focus.h, focus.contorno, p.fundo);
            		}
        			figs.add(focus);
        			repaint();  
        			
        			// Por fim e necessario atualizar a posicao antiga do mouse.
        			posMouse.x = posNovaMouse.x;
            		posMouse.y = posNovaMouse.y;
        			
        		// Se o clique nao foi na bolinha, ha a possibilidade de ter sido numa figura.
        		} else  {
        			// Primeiro verificamos se o clique NAO foi na paleta de cores.
        			if (!coresFocus) {
        				// Agora nos testamos se existe alguma figura focada.
        				// Se sim nos utulizamos o arrasto do mouse para alterar a posicao da figura em questao.
        				if (focus != null) {             			        			
                    		figs.remove(focus);
                    		
                    		// Aqui nos alteramos a posicao X-Y das figuras usando como base o valor de arrasto do mouse
                    		// O arrasto do mouse é igual a posicao nova do mouse menos a posicao antiga
                    		focus.drag(posNovaMouse.x - posMouse.x, posNovaMouse.y - posMouse.y);
                    		
                    		// Aqui e necessario saber se a figura e um pentagono,
                			// pois para movimentar um pentagono e necessario instanciar ele, 
                			// para que sejam feitos os calculos das posicoes dos 5 pontos.
                    		if (focus.getClass().getSimpleName().equals("Pentagon")) {
                    			Pentagon p = (Pentagon) focus;
                    			focus = new Pentagon(focus.x, focus.y, focus.w, focus.h, focus.contorno, p.fundo);
                    		}
                        	figs.add(focus); 
                    		repaint();	
                    		
                    		// Por fim e necessario atualizar a posicao antiga do mouse
                    		posMouse.x = posNovaMouse.x;
                    		posMouse.y = posNovaMouse.y;
                		}
        			}
        			
        		}	
        	}
		});
        
        // Aqui nos criamos figuras novas ou removemos alguma que esteja focada
        // a partir das teclas que forem pressionadas no teclado.
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
                    figs.add(new Line(x, y, w, 0, contorno, fundo));
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
                	// Apos remover uma figura, redefinimos a localizacao da bolinha de foco 
        			// para uma posicao onde nao e possivel clicar.
                	ellipseFocus.x = -10;
                	ellipseFocus.y = -10;
                }
                repaint();
        	}
		});
    }

    // Aqui nos pintamos a janela desde o inicio sempre que a funcao "repaint()" e chamada.
    public void paint (Graphics g) {
        super.paint(g);
        
        // Aqui sao pintadas as figuras.
        for (Figure fig: this.figs) {
        	if (fig == focus) {
        		fig.paint(g, true);
        		
        		// Aqui e atualizada a localizacao da bolinha de foco.
        		ellipseFocus.x = focus.x + focus.w - 4;
            	ellipseFocus.y = focus.y + focus.h - 4;
        	} else {
        		fig.paint(g, false);
        	}
        }
        
        // Aqui sao pintados os botoes
        for (Botao botao: botoes) {
        	botao.paint(g, botao == botaoFocus);
        }
        
        // Aqui nos testamos se existe alguma figura em foco.
        // Se sim, pintamos a paleta de cores.
        if (focus != null) {      	
        	// Aqui nos pintamos a paleta de cores.
        	criaListaCores(this);
        	for (Rect cor: cores) {
                cor.paint(g, false);
            }
        }
    }
    
    // Aqui e criada a lista de cores da paleta de cores.
    // Ela e uma lista de retangulos, sendo a fileira de cima para trocar o contorno e a de baixo o fundo de uma figura.
    // Essa funcao e chamada sempre que necessario para acompanhar o tamanho da janela da aplicacao.
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