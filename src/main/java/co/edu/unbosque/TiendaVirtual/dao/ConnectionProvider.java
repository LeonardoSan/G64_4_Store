package co.edu.unbosque.TiendaVirtual.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionProvider {

	private static Connection con = null;
	
	static{  
		try { 
			Class.forName("com.mysql.cj.jdbc.Driver");  
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tienda","root","");  
		} catch(Exception e){
			
		}  
	}  
	  
	public static Connection getConnection(){  
	    return con;  
	}  
}
