package com.krakedev.inventarios.bdd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.krakedev.inventarios3.entidades.Proveedor;
import com.krakedev.inventarios3.exepciones.KrakeDevException;
import com.krakedev.inventarios3.utils.conexionBDD;



public class ProveedoresBDD {

	
	public ArrayList<Proveedor>buscar(String subcadena)throws KrakeDevException{
		ArrayList<Proveedor> proveedores=new ArrayList<Proveedor>();
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		Proveedor proveedor=null;
		try {
			con=conexionBDD.obtenerConexion();
			ps=con.prepareStatement("select id,id_tipo_documetos,nombre,telefono,correo,direccion "
					+ "from proveedores "
					+ "where upper(nombre) like ? ");
			
			ps.setString(1, "%"+subcadena.toUpperCase()+"%");
			
			rs=ps.executeQuery();
			
			while(rs.next()) {
				String id=rs.getString("id");
				String tipodocumetos=rs.getString("id_tipo_documetos");
				String nombre=rs.getString("nombre");
				String telefono=rs.getString("telefono");
				String correo=rs.getString("correo");
				String direccion=rs.getString("direccion");
				
				proveedor=new Proveedor(id,tipodocumetos,nombre,telefono,correo,direccion);
				proveedores.add(proveedor);
			}
			
			
		} catch (KrakeDevException e) {
		
			e.printStackTrace();
			
			throw e;
		} catch (SQLException e) {
			
			e.printStackTrace();
			throw new KrakeDevException("error al consultar.Detalle: "+ e.getMessage());
		}
		
		return proveedores;
		
		
	}
	
	
	
}
