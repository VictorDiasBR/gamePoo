package jogo;

import java.awt.Point;
import java.util.Vector;

import jplay.GameObject;
import jplay.Scene;
import jplay.TileInfo;
import jplay.URL;

public class Zumbi extends Ator {

	public Zumbi(int x, int y) {
		super(URL.sprite("zumbi.png"), 16);
		// TODO Auto-generated constructor stub
		this.x = x;
		this.y = y;
		this.setTotalDuration(2000);
		this.velocidade = 0.2;
		// TODO Auto-generated constructor stub
	}

	public void perseguir(double x, double y) {

		if (this.x > x && this.y <= y + 50 && this.y >= y + 50) {
			moveTo(x, y, velocidade);
			if (direcao != 1) {
				setSequence(5, 8);
				direcao = 1;
			}
			movendo = true;
		} else if (this.x < x && this.y <= y + 50 && this.y >= -50) {
			moveTo(x, y, velocidade);
			if (direcao != 2) {
				setSequence(9, 12);
				direcao = 2;
			}
			movendo = true;
		} else if (this.y > y) {
			moveTo(x, y, velocidade);
			if (direcao != 4) {
				setSequence(13, 16);
				direcao = 4;
			}
			movendo = true;
		} else if (this.y < y) {
			moveTo(x, y, velocidade);
			if (direcao != 5) {
				setSequence(1, 4);
				direcao = 5;
			}
			movendo = true;
		}
		if (movendo) {
			update();
			movendo = false;
		}
	}
	Controle controle = new Controle();
	public double energia=1000;
	private double ataque =1;
	public void caminho(Scene cena) {
		// TODO Auto-generated method stub
		Point min = new Point((int)this.x,(int)this.y);
		Point max = new Point((int)this.x+this.width,(int)this.y+this.height);
		
		Vector<?>tiles=cena.getTilesFromPosition(min,max);
		for (int i = 0; i < tiles.size(); i++) {
			TileInfo tile = (TileInfo) tiles.elementAt(i);
			
			if(controle.colisao(this, tile)==true) {
				if(colisaoVertical(this,tile)) {
				if(tile.y+tile.height-2<this.y) {
					this.y=tile.y+tile.height;
				}else if(tile.y>this.y+this.height-2) {
					this.y= tile.y -  tile.height;
				}
			}	
			if(colisaoHorizontal(this,tile)) {
				if(tile.x>this.x+this.width-2) {
					this.x=tile.x-this.width;
				}else {
					this.x =tile.x + tile.width; 
					
				}
			}
			}
					
		}
		
	}
	
	private boolean colisaoVertical(GameObject obj, GameObject obj2) {
		if(obj2.x+obj2.width<=obj.x) 
			return false;
		if(obj.x+obj.width<=obj2.x) 
			return false;
		return true;
	}
    private boolean colisaoHorizontal(GameObject obj, GameObject obj2) {
    	if(obj2.y+obj2.height<=obj.y) 
			return false;
		if(obj.y+obj.height<=obj2.y) 
			return false;
		return true;
	}
	public static int qtdMortes =0;
	public void morrer() {
		if(this.energia<=0) {
		this.velocidade=0;
		this.ataque=0;
		this.direcao=0;
		this.movendo=false;
		this.x=1_000_000;
		if(energia==0) {
		qtdMortes++;
		}
	}
	}
	public void atacar(Jogador jogador) {
		
	if(this.collided(jogador)) {
		Jogador.energia-=this.ataque;
	}
	if(Jogador.energia<=0) {
		System.exit(0);
	}
	}
}