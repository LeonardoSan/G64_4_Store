package co.edu.unbosque.TiendaVirtual.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import co.edu.unbosque.TiendaVirtual.modelo.Usuarios;

public class LoginDao {
	
	public static boolean validar(Usuarios usuario) {
		boolean status = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			con = ConnectionProvider.getConnection();
			
			ps = con.prepareStatement(
					"SELECT cedula_usuario, usuario, password FROM `usuarios` WHERE usuario=? AND password=?");
			
			ps.setString(1, usuario.getUsuario());
			ps.setString(2, usuario.getPassword());
			
			rs = ps.executeQuery();
			
			while (rs.next()) {
				
			}
			
			status = rs.next();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				
				if (ps != null) {
					ps.close();
				}
				
				if (con != null) {
					con.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
				
		return status;
		
	}
}
