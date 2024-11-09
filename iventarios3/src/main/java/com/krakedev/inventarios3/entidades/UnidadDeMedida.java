package com.krakedev.inventarios3.entidades;

public class UnidadDeMedida {
	
	private String nombre;
	private String descripcion;
	private CategoriaUDM CategoriaUnidadMedia;
	
	
	
	
	
	
	
	
	public UnidadDeMedida() {
		super();
	}


	public UnidadDeMedida(String nombre, String descripcion, CategoriaUDM categoriaUnidadMedia) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		CategoriaUnidadMedia = categoriaUnidadMedia;
	}
	
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public CategoriaUDM getCategoriaUnidadMedia() {
		return CategoriaUnidadMedia;
	}
	public void setCategoriaUnidadMedia(CategoriaUDM categoriaUnidadMedia) {
		CategoriaUnidadMedia = categoriaUnidadMedia;
	}
	@Override
	public String toString() {
		return "UnidadDeMedida [nombre=" + nombre + ", descripcion=" + descripcion + ", CategoriaUnidadMedia="
				+ CategoriaUnidadMedia + "]";
	}
	
	
	

}
