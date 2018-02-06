package aster;

import java.sql.DriverManager;
import java.sql.Connection;

public class ConexBD {

	private static Connection conexion;
	
	public static Connection dameConexion(){
	
		try{
			Class.forName("com.mysql.jdbc.Driver");
			conexion = DriverManager.getConnection("jdbc:mysql://localhost/asteroides?characterEncoding=UTF-8&useSSL=false","root" ,"");
		}catch (Exception e){
			e.printStackTrace();
		}
		return conexion;
	}
}
