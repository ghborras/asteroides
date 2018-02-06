package aster;

import javax.swing.JLabel;

public class Cronometro implements Runnable {

	private static int uds = 0;
	private JLabel marcador;

	public Cronometro(JLabel marcador) {

		this.marcador = marcador;
	}

	//cronometro horas-min-sg
//	public void run() {
//
//		while (Bola2.getEstado() < 1) {
//			total++;
//			sg++;
//			if (sg == 10) {
//				dsg++;
//				sg = 0;
//			}
//			if (dsg == 6 & sg == 0) {
//				dsg = 0;
//				min++;
//			}
//			if (min == 10) {
//				dmin++;
//				min = 0;
//			}
//			if (dmin == 6 & min == 0) {
//				dmin = 0;
//				hor++;
//			}
//			marcador.setText("0" + hor + ":" + dmin + min + ":" + dsg + sg);
//			try {
//				Thread.sleep(1000);
//			} catch (InterruptedException e) {
//				Thread.currentThread().interrupt();
//			}
//		}
//	}
	
	//cronometro solo total 4 digitos
	public void run() {

		while (Bola2.getEstado() < 1) {
			
			uds++;
			String total = "";
			total=cronoFormat(uds);	
			marcador.setText(total);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
	}
	
	public static String cronoFormat(int uds){
		
		String parcial = String.valueOf(uds);
		String total = "";
		System.out.println(total);
		for (int i = 0; i < 4 - parcial.length(); i++) {
			total += "0";
		}
		return total + uds;
	}
	
	public static int getUds() {
		return uds;
	}
}
