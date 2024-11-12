package com.krakedev.inventarios3.entidades;

import java.util.ArrayList;
import java.util.Date;



public class Pedido {

	private int codigo;
	private Proveedor proveedor;
	private Date fecha;
	private EstadoPedido estadoPedido;
	private ArrayList<DetallePedido> detalles;
	

	@Override
	public String toString() {
		return "Pedido [codigo=" + codigo + ", proveedor=" + proveedor + ", fecha=" + fecha + ", estadoPedido="
				+ estadoPedido + "]";
	}

	
    public Pedido() {}

	public Pedido(int codigo, Proveedor proveedor, Date fecha, EstadoPedido estadoPedido) {
		super();
		this.codigo = codigo;
		this.proveedor = proveedor;
		this.fecha = fecha;
		this.estadoPedido = estadoPedido;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
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


	public Pedido(int codigo, Proveedor proveedor, Date fecha, EstadoPedido estadoPedido,
			ArrayList<DetallePedido> detalles) {
		super();
		this.codigo = codigo;
		this.proveedor = proveedor;
		this.fecha = fecha;
		this.estadoPedido = estadoPedido;
		this.detalles = detalles;
	}

	
	
}
