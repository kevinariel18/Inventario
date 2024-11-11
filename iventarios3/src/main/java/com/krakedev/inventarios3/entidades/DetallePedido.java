package com.krakedev.inventarios3.entidades;

import java.math.BigDecimal;

public class DetallePedido {

	
	private int codigo;
	private Pedido cabecera;
	private Producto producto;
	private int cantidadSolicitada;
	private BigDecimal subtotal;
	private int cantidadResibida;
	
	
	
	
	
	
	public DetallePedido(int codigo, Pedido cabecera, Producto producto, int cantidadSolicitada, BigDecimal subtotal,
			int cantidadResibida) {
		super();
		this.codigo = codigo;
		this.cabecera = cabecera;
		this.producto = producto;
		this.cantidadSolicitada = cantidadSolicitada;
		this.subtotal = subtotal;
		this.cantidadResibida = cantidadResibida;
	}
	public DetallePedido() {
		super();
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public Pedido getCabecera() {
		return cabecera;
	}
	public void setCabecera(Pedido cabecera) {
		this.cabecera = cabecera;
	}
	public Producto getProducto() {
		return producto;
	}
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	public int getCantidadSolicitada() {
		return cantidadSolicitada;
	}
	public void setCantidadSolicitada(int cantidadSolicitada) {
		this.cantidadSolicitada = cantidadSolicitada;
	}
	public BigDecimal getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}
	public int getCantidadResibida() {
		return cantidadResibida;
	}
	public void setCantidadResibida(int cantidadResibida) {
		this.cantidadResibida = cantidadResibida;
	}
	@Override
	public String toString() {
		return "DetallePedido [codigo=" + codigo + ", cabecera=" + cabecera + ", producto=" + producto
				+ ", cantidadSolicitada=" + cantidadSolicitada + ", subtotal=" + subtotal + ", cantidadResibida="
				+ cantidadResibida + "]";
	}
	
	
	
}
