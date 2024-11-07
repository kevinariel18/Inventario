package com.krakedev.inventarios3.servicios;

import javax.ws.rs.core.MediaType;
import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.krakedev.inventarios.bdd.ProveedoresBDD;
import com.krakedev.inventarios3.entidades.Proveedor;
import com.krakedev.inventarios3.exepciones.KrakeDevException;

@Path("proveedores")
public class ServiciosProveedorees {

	
	@Path("buscar/{sub}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response buscar(@PathParam("sub")String subcadena){
	
	    ProveedoresBDD provBDD=new ProveedoresBDD();
		ArrayList<Proveedor> proveedores=null;
		try {
			proveedores = provBDD.buscar(subcadena);
			return Response.ok(proveedores).build();
		} catch (KrakeDevException e) {
			
			e.printStackTrace();
			return Response.serverError().build();
		}
		
		
		
}
}
