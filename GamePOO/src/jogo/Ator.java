package jogo;

import jplay.Keyboard;
import jplay.Sprite;
import jplay.Window;

public class Ator extends Sprite {

	protected double velocidade = 0.5;
	protected int direcao = 3;
	private Keyboard teclado;
	protected boolean movendo = false;

	public Ator(String fileName, int numFrames) {
		super(fileName, numFrames);
		// TODO Auto-generated constructor stub
	}
	 ControleTiros tiros = new ControleTiros();
	public void mover(Window janela) {
		if(teclado==null) {
			teclado=janela.getKeyboard();
		}
		
		if(teclado.keyDown(Keyboard.LEFT_KEY)) {
			if(this.x>0) 
				this.x-=velocidade;
				if(direcao!=1) {
					setSequence(4,8);
					direcao=1;
				}movendo=true;
			
			
		}else if(teclado.keyDown(Keyboard.RIGHT_KEY)) {
			if(this.x<janela.getWidth()-60) 
				this.x+=velocidade;
				if(direcao!=2) {
					setSequence(8,12);
					direcao=2;
				}movendo=true;
		}else if(teclado.keyDown(Keyboard.UP_KEY)) {
			if(this.y>0) 
				this.y-=velocidade;
				if(direcao!=4) {
					setSequence(12,16);
					direcao=4;
				}movendo=true;
		}else if(teclado.keyDown(Keyboard.DOWN_KEY)) {
			if(this.y<janela.getHeight()-60) 
				this.y+=velocidade;
				if(direcao!=5) {
					setSequence(0,4);
					direcao=5;
				}movendo=true;
		}
		if(movendo) {
			update();
			movendo = false;
		}
		
	}
}
