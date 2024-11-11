package com.krakedev.inventarios3.entidades;

import java.util.ArrayList;



public class Pedido {

    private int id;
    private Proveedor proveedor;
    private String fecha;
    private EstadoPedido estadoPedido;
    private ArrayList<DetallePedido> detalles;
 
    public Pedido() {
    }


    

 
    public Pedido(int id, Proveedor proveedor, String fecha, EstadoPedido estadoPedido,
			ArrayList<DetallePedido> detalles) {
		super();
		this.id = id;
		this.proveedor = proveedor;
		this.fecha = fecha;
		this.estadoPedido = estadoPedido;
		this.detalles = detalles;
	}





	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public EstadoPedido getEstadoPedido() {
        return estadoPedido;
    }

    public void setEstadoPedido(EstadoPedido estadoPedido) {
        this.estadoPedido = estadoPedido;
    }


	public ArrayList<DetallePedido> getDetalles() {
		return detalles;
	}


	public void setDetalles(ArrayList<DetallePedido> detalles) {
		this.detalles = detalles;
	}





	@Override
	public String toString() {
		return "Pedido [id=" + id + ", proveedor=" + proveedor + ", fecha=" + fecha + ", estadoPedido=" + estadoPedido
				+ ", detalles=" + detalles + "]";
	}

   
}
