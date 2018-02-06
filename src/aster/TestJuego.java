package aster;

import javax.swing.JFrame;

class TestJuego {
	
	

	public static void main(String[] args){
		
		Runnable sound1;
		Thread g;	
		Juego2 partida = new Juego2();
		partida.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		partida.setVisible(true);
		partida.configuraJuego();
		sound1 = new Sonido("src/sonido/tick-tock.mp3");
		g = new Thread(sound1);
		g.start();
		Marcador.setCrono();
		partida.dameMandos();
		partida.dameBolas();
	}
}


