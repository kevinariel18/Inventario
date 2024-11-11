package com.krakedev.inventarios3.servicios;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.krakedev.inventarios.bdd.PedidosBDD;
import com.krakedev.inventarios3.entidades.Pedido;
import com.krakedev.inventarios3.exepciones.KrakeDevException;

@Path("pedidos")
public class ServiciosPedidos {

    @Path("registrar")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response crear(Pedido pedido) {
        PedidosBDD pedidosBDD = new PedidosBDD();
        
        try {
            pedidosBDD.insertar(pedido);
            return Response.ok("Pedido registrado exitosamente.").build();
        } catch (KrakeDevException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("Error al registrar el pedido: " + e.getMessage())
                           .build();
        }
    }
}
