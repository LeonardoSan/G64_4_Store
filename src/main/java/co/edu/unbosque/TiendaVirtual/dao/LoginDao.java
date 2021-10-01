package co.edu.unbosque.TiendaVirtual.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import co.edu.unbosque.TiendaVirtual.modelo.Usuarios;

public class LoginDao {
	
	public static boolean validar(Usuarios usuario) {
		boolean status = false;
		
		try {
			Connection con = ConnectionProvider.getConnection();
			
			PreparedStatement ps = con.prepareStatement(
					"SELECT * FROM `usuarios` WHERE nombre_usuarios=? AND password=?");
			
			ps.setString(1, usuario.getNombre_usuario());
			ps.setString(2, usuario.getPassword());
			
			ResultSet rs = ps.executeQuery();
			
			status = rs.next();
		} catch (Exception e) {}
		
		
		return status;
		
	}
}
