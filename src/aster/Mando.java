package aster;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Mando implements KeyListener {

	private int velocidad_mando;
	private int base;
	private int altura;
	private static JButton btn;
	private int x;
	private int y;
	private JPanel campo;
	private boolean arriba = false;
	private boolean abajo = false;
	private boolean derecha = false;
	private boolean izquierda = false;

	public Mando(int velocidad_mando, int ancho_mando, int alto_mando,
			JPanel campo) {

		super();
		this.velocidad_mando = velocidad_mando;
		base = ancho_mando;
		altura = alto_mando;
		btn = new JButton();
		btn.setBackground(Color.WHITE);
		y = campo.getHeight() / 2;
		x = campo.getWidth() / 2;
		btn.setBounds(x, y, base, altura);
		this.campo = campo;
		this.campo.add(btn);
	}

	public void keyPressed(KeyEvent e) {
		
		if (e.getKeyCode() == 37) {
			izquierda = true;
		}
		if (e.getKeyCode() == 39) {
			derecha = true;
		}
		if (e.getKeyCode() == 38) {
			arriba = true;
		}
		if (e.getKeyCode() == 40) {
			abajo = true;
		}
		runMando();
	}

	public void keyReleased(KeyEvent arg0) {
		
	}

	public void keyTyped(KeyEvent arg0) {

	}

	public void runMando() {

		if (abajo) {
			y += velocidad_mando;
			if (y > campo.getHeight() - btn.getHeight()) {
				y = campo.getHeight() - btn.getHeight();
			}
			abajo = false;
		}
		if (arriba) {
			y -= velocidad_mando;
			if (y < 0) {
				y = 0;
			}
			arriba = false;
		}
		if (derecha) {
			x += velocidad_mando;
			if (x > campo.getWidth() - base) {
				x = campo.getWidth() - base;
			}
			derecha = false;
		}
		if (izquierda) {
			x -= velocidad_mando;
			if (x < 0) {
				x = 0;
			}
			izquierda = false;
		}
		btn.setBounds(x, y, base, altura);
	}

	public int getPosicionMandoY() {

		return y;
	}
	
	public int getPosicionMandoX() {

		return x;
	}
	
	public static void cambiaColor(int estado) {
		
		if(estado == 1){
			btn.setBackground(Color.red);
		}
	}

	public int getAlturaMando() {

		return altura;
	}

	public int getAnchoMando() {

		return base;
	}

	public static void btnInv() {
		btn.setVisible(false);
	}
	
}
