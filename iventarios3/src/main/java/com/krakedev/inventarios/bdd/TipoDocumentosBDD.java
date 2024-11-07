package com.krakedev.inventarios.bdd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.krakedev.inventarios3.entidades.TipoDocumento;
import com.krakedev.inventarios3.exepciones.KrakeDevException;
import com.krakedev.inventarios3.utils.conexionBDD;

public class TipoDocumentosBDD {

   
    public ArrayList<TipoDocumento> recuperarTodos() throws KrakeDevException {
        ArrayList<TipoDocumento> tiposDocumentos = new ArrayList<TipoDocumento>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        TipoDocumento tipoDocumento = null;

        try {
            
            con = conexionBDD.obtenerConexion();
            
            
            ps = con.prepareStatement("SELECT codigo_tipo_doc, descripcion FROM tipo_documentos");
            rs = ps.executeQuery();

       
            while (rs.next()) {
                String codigoTipoDoc = rs.getString("codigo_tipo_doc");
                String descripcion = rs.getString("descripcion");
                
              
                tipoDocumento = new TipoDocumento(codigoTipoDoc, descripcion);
                tiposDocumentos.add(tipoDocumento);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new KrakeDevException("Error al consultar los tipos de documentos. Detalle: " + e.getMessage());
        } catch (KrakeDevException e) {
            e.printStackTrace();
            throw e;
        } finally {
           
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return tiposDocumentos;
    }
}
