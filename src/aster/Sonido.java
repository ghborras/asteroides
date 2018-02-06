package aster;

import java.io.FileInputStream;
import javazoom.jl.player.Player;

public class Sonido implements Runnable{
	
	private String tema;
	
	public Sonido(String tema){
		
		this.tema=tema;
	}

	public void run() {
		
		try{
			 FileInputStream archivo = new FileInputStream(tema);
			 Player playMP3 = new Player(archivo);
			 playMP3.play();
			 
		 } catch(Exception exc){
			 exc.printStackTrace();
			 System.out.println("No se pudo reproducir el archivo");
		} 
	}
}
