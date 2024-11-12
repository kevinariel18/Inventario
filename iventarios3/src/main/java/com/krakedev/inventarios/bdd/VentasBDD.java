package com.krakedev.inventarios.bdd;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.krakedev.inventarios3.entidades.DetallePedido;
import com.krakedev.inventarios3.entidades.Pedido;
import com.krakedev.inventarios3.exepciones.KrakeDevException;
import com.krakedev.inventarios3.utils.conexionBDD;

public class VentasBDD {

    public void registrarVenta(Pedido pedido) throws KrakeDevException {
        Connection con = null;
        PreparedStatement psCabecera = null;
        PreparedStatement psDetalle = null;
        PreparedStatement psHistorial = null;
        ResultSet rsCabecera = null;

        String sqlCabecera = "INSERT INTO cabecera_ventas(fecha, total_sin_iva, iva, total) VALUES (CURRENT_TIMESTAMP, 0, 0, 0)";
        String sqlDetalle = "INSERT INTO detalle_ventas(id_cabecera_ventas, id_producto, cantidad, precio_venta, sub_total, sub_total_con_iva) VALUES (?, ?, ?, ?, ?, ?)";
        String sqlHistorial = "INSERT INTO historial_stock(fecha, referencia, id_producto, cantidad) VALUES (CURRENT_TIMESTAMP, ?, ?, ?)";
        String sqlUpdateCabecera = "UPDATE cabecera_ventas SET total_sin_iva = ?, iva = ?, total = ? WHERE codigo = ?";

        try {
            con = conexionBDD.obtenerConexion();
            con.setAutoCommit(false); // Iniciar transacción

            // 1. Insertar la cabecera de ventas
            psCabecera = con.prepareStatement(sqlCabecera, Statement.RETURN_GENERATED_KEYS);
            psCabecera.executeUpdate();
            rsCabecera = psCabecera.getGeneratedKeys();

            int codigoCabecera = 0;
            if (rsCabecera.next()) {
                codigoCabecera = rsCabecera.getInt(1);
            }

            BigDecimal totalSinIva = BigDecimal.ZERO;
            BigDecimal ivaTotal = BigDecimal.ZERO;
            BigDecimal totalConIva = BigDecimal.ZERO;

            // 2. Insertar en detalle_ventas y calcular totales
            for (DetallePedido detalle : pedido.getDetalles()) {
                int codigoProducto = detalle.getProducto().getCodigo();
                BigDecimal precioVenta = detalle.getProducto().getPrecioVenta();
                int cantidad = detalle.getCantidadSolicitada(); // Usar getCantidadSolicitada()
                BigDecimal subTotal = precioVenta.multiply(new BigDecimal(cantidad));

                // Verificar si tiene IVA
                boolean tieneIva = detalle.getProducto().isTieneIva(); // Usar isTieneIva()
                BigDecimal subTotalConIva = tieneIva ? subTotal.multiply(new BigDecimal("1.12")) : subTotal;

                if (tieneIva) {
                    ivaTotal = ivaTotal.add(subTotalConIva.subtract(subTotal));
                }

                totalSinIva = totalSinIva.add(subTotal);
                totalConIva = totalConIva.add(subTotalConIva);

                // Insertar cada detalle
                psDetalle = con.prepareStatement(sqlDetalle);
                psDetalle.setInt(1, codigoCabecera);
                psDetalle.setInt(2, codigoProducto);
                psDetalle.setInt(3, cantidad);
                psDetalle.setBigDecimal(4, precioVenta);
                psDetalle.setBigDecimal(5, subTotal);
                psDetalle.setBigDecimal(6, subTotalConIva);
                psDetalle.executeUpdate();

                // 4. Insertar en historial_stock
                psHistorial = con.prepareStatement(sqlHistorial);
                psHistorial.setString(1, "VENTA " + codigoCabecera);
                psHistorial.setInt(2, codigoProducto);
                psHistorial.setInt(3, cantidad * -1); // Valor negativo
                psHistorial.executeUpdate();
            }

            // 3. Actualizar la cabecera con los totales calculados
            psCabecera = con.prepareStatement(sqlUpdateCabecera);
            psCabecera.setBigDecimal(1, totalSinIva);
            psCabecera.setBigDecimal(2, ivaTotal);
            psCabecera.setBigDecimal(3, totalConIva);
            psCabecera.setInt(4, codigoCabecera);
            psCabecera.executeUpdate();

            con.commit(); // Confirmar transacción

        } catch (SQLException e) {
            if (con != null) {
                try {
                    con.rollback(); // Revertir en caso de error
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            throw new KrakeDevException("Error al registrar la venta: " + e.getMessage());
        } finally {
            conexionBDD.cerrar(con, psCabecera, psDetalle, psHistorial, rsCabecera);
        }
    }
}
