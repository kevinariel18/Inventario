package com.krakedev.inventarios.bdd;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import com.krakedev.inventarios3.entidades.DetallePedido;
import com.krakedev.inventarios3.entidades.Pedido;
import com.krakedev.inventarios3.exepciones.KrakeDevException;
import com.krakedev.inventarios3.utils.conexionBDD;

public class PedidosBDD {

public void insertar(Pedido pedido) throws KrakeDevException{
	Connection con=null;
	PreparedStatement ps=null;
    PreparedStatement psDet=null;
	ResultSet rsClave=null;
	int codigoCabezera=0;
	Date fechaActual=new Date();
	java.sql.Date fechaSQL=new java.sql.Date(fechaActual.getTime());
	
	try {
		con=conexionBDD.obtenerConexion();
		ps=con.prepareStatement("insert into cabecera_pedido(id_proveedor, fecha, id_estado_pedido)"
				+ "values (?,?,?);",Statement.RETURN_GENERATED_KEYS);
		
		   ps.setString(1, pedido.getProveedor().getIdentificador());
           ps.setDate(2, fechaSQL);
           ps.setString(3, "S");
		
		ps.executeUpdate();
		rsClave=ps.getGeneratedKeys();
		
		if(rsClave.next()) {
			codigoCabezera=rsClave.getInt(1);
		}
		
		ArrayList<DetallePedido>detallesPedido=pedido.getDetalles();
		DetallePedido det;
		
		for(int i=0;i<detallesPedido.size();i++) {
			det = detallesPedido.get(i);
			psDet=con.prepareStatement("insert into detalle_pedido(id_cabecera_pedido,id_producto,cantidad_solicitada,subtotal,cantidad_recibida)"
					+ "values (?,?,?,?,?);");
					psDet.setInt(1, codigoCabezera);
					psDet.setInt(2,det.getProducto().getCodigo() );
					psDet.setInt(3, det.getCantidadSolicitada());
			        psDet.setInt(4, 0);
			          BigDecimal pv=det.getProducto().getPrecioVenta();
			          BigDecimal cantidad=new BigDecimal(det.getCantidadSolicitada());
			          BigDecimal subtotal=pv.multiply(cantidad);
			          psDet.setBigDecimal(5, subtotal);
			          
			          psDet.executeUpdate();
			        
		}
		
	} catch (KrakeDevException e) {
		e.printStackTrace();
		throw e;
		
	} catch (SQLException e) {
		e.printStackTrace();
		throw new KrakeDevException("ERROR AL INSERTAR PRODUCTO. DETALLE: "+e.getMessage());
	}
	
	
	
	
	
	
	
	
	
	
}

}
