package aster;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Bola2 implements Runnable {

	private JButton a;
	private int anchoBola;
	private int altoBola;
	private JPanel campo;
	private Mando izq;
	private Runnable sound1;
	private Thread g;
	private int paradaNuevaBola = 2000;
	private int mili = 4;
	private int nano = 999999;
	private static int estado = 0;
	private Runnable sound2;
	private Thread r;
	private List<JButton> listaButtons = new ArrayList<JButton>();
	
	public Bola2(JPanel campo, Mando izq) {

		super();
		anchoBola = 20;
		altoBola = 20;
		this.a = new JButton();
		this.a.setBackground(Color.YELLOW);
		this.a.setBounds(0, 0, anchoBola, altoBola);
		this.campo = campo;
		this.campo.add(a);
		this.izq = izq;
		listaButtons.add(a);
		
	}

	public static int aleatorio(int min, int max) {
		return (int) (Math.random() * (max - min + 1) + min);
	}

	public void run() {
		
		int dx = 1;
		int dy = aleatorio(0, 1);
		if (dy == 0) dy = -1;
		int aleatorio = aleatorio(0,1);
		int x;
		if (aleatorio == 0){
			x = 0;
		}
		else x = campo.getWidth();
		int y = aleatorio(0, campo.getHeight());
		mili = 4;

		do {
			if (dx == 1 & dy == 1) {
				if (y > campo.getHeight() - 10) {// -10 para deducir espesor del marco								
					dy = -1;
				}
				if (x > campo.getWidth() - 10) {
					dy = 1;
					dx = -1;
					aceleraBola();	
				}
				y++;
				x++;
			}
				
			if (dx == 1 & dy == -1) {
				if (y == 0) {
					dy = 1;
					dx = 1;
				}
				if (x > campo.getWidth() - 10) {
					dy = -1;
					dx = -1;
					aceleraBola();
				}
				y--;
				x++;
			}

			if (dx == -1 & dy == -1) {
				if (y == 0) {
					dy = 1;
					dx = -1;
				}
				if (x < 0) {
					dy = -1;
					dx = 1;
					aceleraBola();
				}
				x--;
				y--;
			}

			if (dx == -1 & dy == 1) {
				if (y == campo.getHeight() - 10) {
					dy = -1;
					dx = -1;
				}
				if (x < 0) {
					dy = 1;
					dx = 1;
					aceleraBola();
				}
				x--;
				y++;
			}
			a.setBounds(x, y, anchoBola, altoBola);
			
			try {
				if (mili<0) mili=0;
				Thread.sleep(mili, nano);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}

			if (mili == paradaNuevaBola) {
				mili = 4;
				nano = 999999;
			}
			
			int pos1x = izq.getPosicionMandoX()-20;
			int pos2x = izq.getPosicionMandoX()+20;
			int pos1y = izq.getPosicionMandoY()-20;
			int pos2y = izq.getPosicionMandoY()+20;
		
			if (x > pos1x & x < pos2x) {
				if (y > pos1y & y < pos2y) {
					estado++;
					Mando.cambiaColor(estado);
					sonidoEnd();		
					Marcador.recordsGame(campo);
					Marcador.finalGame();	
				}
			}
			
			if(estado == 1){
				for (JButton a: listaButtons){
					a.setVisible(false);
				}
			}
		} while (estado < 1);
	}

	public void sonidoAmbiente() {

		sound1 = new Sonido("src/sonido/tick-tock.mp3");
		g = new Thread(sound1);
		g.start();
	}
	
	public void aceleraBola() {

		int dificultad=150000;
		if (nano <= dificultad) {
			nano = 999999;
			mili--;
		} else
			nano -= dificultad;
	}
	
	public static int getEstado(){
		return estado;
	}
	
	public void sonidoEnd() {

		sound2 = new Sonido("src/sonido/game-over.mp3");
		r = new Thread(sound2);
		r.start();
	}
}
