package com.krakedev.inventarios.bdd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.krakedev.inventarios3.entidades.Proveedor;
import com.krakedev.inventarios3.entidades.TipoDocumento;
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
			 ps = con.prepareStatement(
				        "SELECT prov.id, prov.id_tipo_documetos, td.descripcion, prov.nombre, prov.telefono, prov.correo, prov.direccion " +
				        "FROM proveedores prov " +
				        "JOIN tipo_documentos td " +
				        "ON prov.id_tipo_documetos = td.codigo_tipo_doc " +
				        "WHERE UPPER(prov.nombre) LIKE ?"
				    );
			
			ps.setString(1, "%"+subcadena.toUpperCase()+"%");
			
			rs=ps.executeQuery();
			
			while(rs.next()) {
				String id=rs.getString("id");
				String codigotipodocumetos=rs.getString("id_tipo_documetos");
				String descripcionTipoDocumento=rs.getString("descripcion");
				String nombre=rs.getString("nombre");
				String telefono=rs.getString("telefono");
				String correo=rs.getString("correo");
				String direccion=rs.getString("direccion");
				TipoDocumento td=new TipoDocumento(codigotipodocumetos,descripcionTipoDocumento);
				
				proveedor=new Proveedor(id,td,nombre,telefono,correo,direccion);
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
	
	
	public void insertar(Proveedor proveedor) throws KrakeDevException {
	    Connection con = null;
	    PreparedStatement ps = null;
	    
	    try {
	        con = conexionBDD.obtenerConexion();

	   
	        String sql = "INSERT INTO proveedores (id, id_tipo_documetos, nombre, telefono, correo, direccion) " +
	                     "VALUES (?, ?, ?, ?, ?, ?)";

	        ps = con.prepareStatement(sql);

	       
	        ps.setString(1, proveedor.getIdentificador()); 
	        ps.setString(2, proveedor.getTipoDocumento().getCodigoTipoDoc());
	        ps.setString(3, proveedor.getNombre()); 
	        ps.setString(4, proveedor.getTelefono()); 
	        ps.setString(5, proveedor.getCorreo()); 
	        ps.setString(6, proveedor.getDireccion());

	       
	        int rowsAffected = ps.executeUpdate();

	       
	        if (rowsAffected == 0) {
	            throw new KrakeDevException("No se pudo insertar el proveedor.");
	        }

	    } catch (SQLException e) {
	   
	        e.printStackTrace();
	        throw new KrakeDevException("Error al insertar proveedor: " + e.getMessage());
	    } finally {
	   
	        try {
	            if (ps != null) ps.close();
	            if (con != null) con.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}
	
	
	
	
	
	
	
	
	
	
	
}
