package com.krakedev.inventarios3.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.krakedev.inventarios3.exepciones.KrakeDevException;



public class conexionBDD {

	
	public static Connection obtenerConexion() throws KrakeDevException{
		Context ctx=null;
		DataSource ds=null;
		Connection con=null;
		
			try {
				ctx = new InitialContext();
				//JNDI
				ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/ConexionInventario");
				con = ds.getConnection();
			} catch (NamingException | SQLException e) {
			
				e.printStackTrace();
				throw new KrakeDevException("ERROR DE CONEXION");
			}
			
			return con;
	}
	
	
	
	 // Método para cerrar la conexión
    public static void cerrar(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Método para cerrar PreparedStatement
    public static void cerrar(PreparedStatement ps) {
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Método para cerrar ResultSet
    public static void cerrar(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Método sobrecargado para cerrar múltiples recursos a la vez
    public static void cerrar(Connection con, PreparedStatement ps, ResultSet rs) {
        cerrar(rs);
        cerrar(ps);
        cerrar(con);
    }

    // Método sobrecargado para cerrar múltiples PreparedStatements y ResultSet
    public static void cerrar(Connection con, PreparedStatement ps1, PreparedStatement ps2, PreparedStatement ps3, ResultSet rs) {
        cerrar(rs);
        cerrar(ps1);
        cerrar(ps2);
        cerrar(ps3);
        cerrar(con);
    }
	
	
	
	
}
