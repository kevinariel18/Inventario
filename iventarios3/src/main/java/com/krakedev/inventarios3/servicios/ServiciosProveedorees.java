package com.krakedev.inventarios3.servicios;


import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.krakedev.inventarios.bdd.ProveedoresBDD;
import com.krakedev.inventarios3.entidades.Proveedor;
import com.krakedev.inventarios3.exepciones.KrakeDevException;

@Path("proveedores")
public class ServiciosProveedorees{
	
	@Path("buscar/{subcadena}")
	@GET
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public Response buscar(@PathParam("subcadena") String subcadena) {

		ProveedoresBDD prov = new ProveedoresBDD();
		ArrayList<Proveedor> proveedores = new ArrayList<Proveedor>();

		try {
			proveedores = prov.buscar(subcadena);
			return Response.ok(proveedores).build();

		} catch (KrakeDevException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			return Response.serverError().entity("Error en la busqueda" + e.getMessage()).build();
		}

	}
	
	
	
	@Path("crear")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response crear(Proveedor proveedor) {
        ProveedoresBDD provBDD = new ProveedoresBDD();

        try {
           
            provBDD.insertar(proveedor);
         
            return Response.status(Response.Status.CREATED).entity(proveedor).build();
        } catch (KrakeDevException e) {
            e.printStackTrace();
         
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error al crear proveedor: " + e.getMessage())
                    .build();
        }
    }
	
	
	
	
	
	
	
	
	
	
	

}
