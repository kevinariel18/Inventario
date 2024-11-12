package com.krakedev.inventarios.bdd;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

import com.krakedev.inventarios3.entidades.DetallePedido;
import com.krakedev.inventarios3.entidades.HistorialStock;
import com.krakedev.inventarios3.entidades.Pedido;
import com.krakedev.inventarios3.exepciones.KrakeDevException;
import com.krakedev.inventarios3.utils.conexionBDD;


public class PedidosBDD {

	public void insertar(Pedido pedido) throws KrakeDevException {

		Connection con = null;
		PreparedStatement ps = null;
		PreparedStatement psDet = null;
		ResultSet rsClave = null;
		int CodigoCabecera = 0;

		String sql = "insert into cabecera_pedido(id_proveedor, fecha, id_estado_pedido) values(?,?,?)";
		Date fechaActual = new Date();
		java.sql.Date fechaSQL = new java.sql.Date(fechaActual.getTime());

		try {
			con = conexionBDD.obtenerConexion();
			ps = con.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);

			ps.setString(1, pedido.getProveedor().getIdentificador());
			ps.setDate(2, fechaSQL);
			ps.setString(3, "S");

			ps.executeUpdate();

			rsClave = ps.getGeneratedKeys();

			if (rsClave.next()) {
				CodigoCabecera = rsClave.getInt(1);
			}

			ArrayList<DetallePedido> detallesPedido = pedido.getDetalles();
			DetallePedido det;
			String sqlDet = "insert into detalle_pedido(id_cabecera_pedido,id_producto,cantidad_solicitada,subtotal,cantidad_recibida) values (?,?,?,?,?)";

			for (int i = 0; i < detallesPedido.size(); i++) {
				det = detallesPedido.get(i);

				psDet = con.prepareStatement(sqlDet);
				psDet.setInt(1, CodigoCabecera);
				psDet.setInt(2, det.getProducto().getCodigo());
				psDet.setInt(3, det.getCantidadSolicitada());
				psDet.setInt(5, 0);

				BigDecimal pv = det.getProducto().getPrecioVenta();
				BigDecimal cantidad = new BigDecimal(det.getCantidadSolicitada());
				BigDecimal subtotal = pv.multiply(cantidad);

				psDet.setBigDecimal(4, subtotal);

				psDet.executeUpdate();

			}

			System.out.println("Codigo Generado: " + CodigoCabecera);

		} catch (KrakeDevException e) {

			e.printStackTrace();

			throw new KrakeDevException("Error en la conexion de base de datos: " + e.getMessage());

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new KrakeDevException("Error en crear un nuevo registro: " + e.getMessage());

		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public void Entregar(Pedido pedidoentregado) throws KrakeDevException {

	    Connection con = null;
	    PreparedStatement ps = null;
	    PreparedStatement psDet = null;
	    PreparedStatement psHistorial = null;
	    String sql = "UPDATE cabecera_pedido SET id_estado_pedido = 'R' WHERE numero = ?";

	    try {
	        con = conexionBDD.obtenerConexion();
	        ps = con.prepareStatement(sql);
	        ps.setInt(1, pedidoentregado.getCodigo());
	        ps.executeUpdate();

	        ArrayList<DetallePedido> detallesPedidosentregado = pedidoentregado.getDetalles();
	        String sqlDetalles = "UPDATE detalle_pedido SET cantidad_recibida = ?, subtotal = ? WHERE codigo = ?";

	        DetallePedido detallesPedidosEntregados = null;

	        for (int i = 0; i < detallesPedidosentregado.size(); i++) {

	            detallesPedidosEntregados = detallesPedidosentregado.get(i);
	            psDet = con.prepareStatement(sqlDetalles);

	            psDet.setInt(1, detallesPedidosEntregados.getCantidadRecibida());

	            // Calcular el subtotal
	            BigDecimal pv = detallesPedidosEntregados.getProducto().getPrecioVenta();
	            BigDecimal cantidad = new BigDecimal(detallesPedidosEntregados.getCantidadRecibida());
	            BigDecimal subtotal = pv.multiply(cantidad);

	            psDet.setBigDecimal(2, subtotal);
	            psDet.setInt(3, detallesPedidosEntregados.getCodigo());
	            psDet.executeUpdate();

	            // Insertar en el historial de stock
	            HistorialStock historial = new HistorialStock(
	                    0, // El código se genera automáticamente por la base de datos
	                    Timestamp.valueOf(LocalDateTime.now()), // Fecha y hora actuales
	                    "Pedido " + pedidoentregado.getCodigo(), // Referencia del pedido
	                    detallesPedidosEntregados.getProducto().getCodigo(),
	                    detallesPedidosEntregados.getCantidadRecibida()
	            );

	            // SQL para insertar el historial de stock
	            String sqlHistorial = "INSERT INTO historial_stock(fecha, referencia, id_producto, cantidad) VALUES (?, ?, ?, ?)";
	            psHistorial = con.prepareStatement(sqlHistorial);
	            psHistorial.setTimestamp(1, historial.getFecha());
	            psHistorial.setString(2, historial.getReferencia());
	            psHistorial.setInt(3, historial.getIdProducto());
	            psHistorial.setInt(4, historial.getCantidad());
	            psHistorial.executeUpdate();
	        }

	    } catch (KrakeDevException e) {
	        e.printStackTrace();
	        throw new KrakeDevException("Error en la conexión de base de datos: " + e.getMessage());

	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw new KrakeDevException("Error en registro de pedido: " + e.getMessage());

	    } finally {
	        try {
	            if (ps != null) ps.close();
	            if (psDet != null) psDet.close();
	            if (psHistorial != null) psHistorial.close();
	            if (con != null) con.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}


}