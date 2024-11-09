package com.krakedev.inventarios3.servicios;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.krakedev.inventarios.bdd.ProductosBDD;
import com.krakedev.inventarios3.entidades.Producto;
import com.krakedev.inventarios3.exepciones.KrakeDevException;


import javax.ws.rs.Consumes;

import javax.ws.rs.core.MediaType;




@Path("productos")
public class ServicioProductos {

	@Path("buscar/{subcadena}")
	@GET
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public Response buscar(@PathParam("subcadena") String subcadena) {

		ProductosBDD prodBDD = new ProductosBDD();
		ArrayList<Producto> productos = new ArrayList<Producto>();

		try {
			productos = prodBDD.buscar(subcadena);

			return Response.ok(productos).build();

		} catch (KrakeDevException e) {

			System.out.println("error: " + e.getMessage());
			return Response.serverError().entity("error al consultar : " + e.getMessage()).build();

		}

	}

	
	
    @Path("crear")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response crearProducto(Producto producto) {
        ProductosBDD prodBDD = new ProductosBDD();

        try {
            prodBDD.insertar(producto); 
            return Response.status(Response.Status.CREATED).entity(producto).build();
        } catch (KrakeDevException e) {
            System.out.println("Error al crear producto: " + e.getMessage());
            return Response.serverError().entity("Error al crear producto: " + e.getMessage()).build();
        }
    }
	
	
	
	
	
	
	
	
	
}
