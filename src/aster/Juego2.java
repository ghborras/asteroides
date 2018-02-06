package aster;

import java.awt.Color;



import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
class Juego2 extends JFrame {

	
	@SuppressWarnings("unused")
	private Marcador miMarcador;
	private Mando izquierdo;
	private static Thread b;
	private Runnable bola;
	
	private JPanel campo;
	private static int bolas;
	private int nb = 1;
	
	//me da la bola que choca
	private List<Thread> listaHilos = new ArrayList<Thread>();
	
	public Juego2() {

		setVisible(true);
		setBounds(10, 10, 1300, 700);
		campo = new JPanel();
		campo.setBorder(new EmptyBorder(5, 5, 5, 5));
		campo.setBackground(Color.BLACK);
		campo.setBounds(10, 10, 1300, 700);
		setContentPane(campo);
		campo.setLayout(null);
		miMarcador = new Marcador(campo);
	}
	
	public void aJugar() {
			
		String nbola = "bol" + nb;
		bola = new Bola2(campo, izquierdo);
		b = new Thread(bola, nbola);
		b.start();
		nb++;
		listaHilos.add(b);
	}
	
	public void dameBolas(){
		for(int i=0; i < bolas; i++){
			aJugar();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
	}
	
	public void imprimeListaHilos(){
		for (Thread p: listaHilos){
			System.out.println(p.getName());
		}
	}

	public void dameMandos() {

		izquierdo = new Mando(30, 30, 30, campo);
		addKeyListener(izquierdo);
		campo.repaint();
	}
	
	public void configuraJuego(){
		
		try{
			bolas = Integer.parseInt(JOptionPane.showInputDialog(null,"Numero de bolas: (10 para tabla de records o enter)"));
		}
		catch(NumberFormatException e){
			bolas = 10;
		}
	}
	
	public static int getBolas(){
		return bolas;
	}
}


