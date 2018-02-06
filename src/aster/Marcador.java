package aster;

import java.awt.Color;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Marcador {

	private static JLabel marcador;
	private static JLabel tiempo;
	private static Thread c;
	private static Runnable crono;
	private static ResultSet rec;
	private static ResultSet top;
	private static String nombre;
	private static String time;
	private static Connection conexion;
	private static int total;
	
	public Marcador(JPanel campo) {

		super();
		marcador = new JLabel();
		marcador.setBounds(0, 0, campo.getWidth(), 60);
		marcador.setForeground(Color.WHITE);
		marcador.setHorizontalAlignment(SwingConstants.CENTER);
		marcador.setFont(new java.awt.Font("BankGothic Lt BT", 1, 50));
		
		tiempo = new JLabel();
		tiempo.setBounds(0, 10, campo.getWidth(), 200);
		tiempo.setForeground(Color.WHITE);
		tiempo.setHorizontalAlignment(SwingConstants.CENTER);
		tiempo.setFont(new java.awt.Font("BankGothic Lt BT", 1, 50));
		
		campo.add(marcador);
		campo.add(tiempo);
	}

	public static void finalGame() {

		c.interrupt();
		tiempo.setText("Game Over");
	}

	public static void recordsGame(JPanel campo) {
			
		time = marcador.getText();
		total = Cronometro.getUds();
		boolean record = false;
		rec = null;
		try {
			rec = calculaResultSet();
			while (rec.next()) {
				if (rec.getInt("total") < Integer.parseInt(time)) {
					record = true;
				}
			}
			
			rec.first();
			
			if (record & Juego2.getBolas() == 10) {
				nombre = JOptionPane.showInputDialog(null,"Introduce tu nombre: ", "Record TOP-10", 3);
				insertaRegistro(nombre, total);
				top = calculaResultSet();
				int ylabel = 175;
				int n = 1;
				while (top.next()){
					
					JLabel jugador = new JLabel();
					jugador.setForeground(Color.ORANGE);
					jugador.setFont(new java.awt.Font("BankGothic Lt BT", 1, 24));
					jugador.setText(n+". "+top.getString("nombre"));
					jugador.setBounds((campo.getWidth()/2)-200, ylabel, 200, 50);
					campo.add(jugador);
					
					JLabel segundos = new JLabel();
					segundos.setForeground(Color.ORANGE);
					segundos.setFont(new java.awt.Font("BankGothic Lt BT", 1, 24));
					segundos.setText(Cronometro.cronoFormat(top.getInt("total")));
					segundos.setBounds((campo.getWidth()/2)+150, ylabel, 200, 50);
					campo.add(segundos);
					
					n++;
					ylabel+=40;
				}
				Mando.btnInv();
				campo.repaint();
				conexion.close();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void insertaRegistro(String nom, int total) {

		conexion = ConexBD.dameConexion();
		try {
			PreparedStatement ps = conexion
					.prepareStatement("insert into aster (nombre,total) values (?,?)");
			ps.setString(1, nom);
			ps.setInt(2, total);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static ResultSet calculaResultSet() throws IOException, ClassNotFoundException, SQLException {

		Connection conexion = ConexBD.dameConexion();
		ResultSet rs = null;
		try {
			PreparedStatement ps = conexion
					.prepareStatement("select * from aster order by total desc limit 10");
			rs = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	public static void setCrono() {

		crono = new Cronometro(marcador);
		c = new Thread(crono);
		c.start();
	}
}
