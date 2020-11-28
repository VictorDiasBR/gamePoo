package jogo;

import jplay.Keyboard;
import jplay.Scene;
import jplay.URL;
import jplay.Window;

public class Cenario1 {

	private Window janela;
	private Scene cena;
	private Jogador jogador;
	private Zumbi zumbi[];
  private Keyboard teclado;
  
	public Cenario1(Window window) {
		super();
		this.janela = window;
		this.cena = new Scene();
		zumbi = new Zumbi[10];
		cena.loadFromFile(URL.scenario("cenario1.scn"));
		jogador = new Jogador(540, 350);
		teclado=janela.getKeyboard();
		run();
	}

	private void run() {
		
		for (int i = 0; i < zumbi.length; i++) {
			zumbi[i]=new Zumbi(30*i+10,50*i+10);
		}
		while (true) {

			jogador.controle(janela,teclado);
			
//            jogador.caminho(cena); 
//			cena.moveScene(jogador);
//			zumbi.caminho(cena);
			
			
			jogador.energia(janela);
			jogador.x+= cena.getXOffset();
			jogador.y+= cena.getYOffset();
			
			for (int i = 0; i < zumbi.length; i++) {
				zumbi[i].morrer(); 
				zumbi[i].atacar(jogador);
				zumbi[i].perseguir(jogador.x, jogador.y);
				zumbi[i].x+= cena.getXOffset();
				zumbi[i].y+= cena.getYOffset();
				zumbi[i].draw();
				jogador.atirar(janela, cena, teclado,zumbi[i]);
				}
			
			janela.update();
			cena.draw();
			jogador.draw();

		}

	}

}
