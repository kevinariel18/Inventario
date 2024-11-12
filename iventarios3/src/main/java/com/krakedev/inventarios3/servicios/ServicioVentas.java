package com.krakedev.inventarios3.servicios;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.krakedev.inventarios.bdd.VentasBDD;
import com.krakedev.inventarios3.entidades.Pedido;
import com.krakedev.inventarios3.exepciones.KrakeDevException;

@Path("ventas")
public class ServicioVentas {

    @POST
    @Path("guardar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response registrarVenta(Pedido pedido) {
        VentasBDD ventasBDD = new VentasBDD();
        try {
            ventasBDD.registrarVenta(pedido);
            return Response.status(Response.Status.CREATED).entity("Venta registrada exitosamente").build();
        } catch (KrakeDevException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }
}
