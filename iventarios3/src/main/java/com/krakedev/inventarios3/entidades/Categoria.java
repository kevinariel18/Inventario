package com.krakedev.inventarios3.entidades;

public class Categoria {
private int codigo;
private String nombre;
private Categoria categoriaPadree;





public Categoria() {
	super();
}


public Categoria(int codigo, String nombre, Categoria categoriaPadree) {
	super();
	this.codigo = codigo;
	this.nombre = nombre;
	this.categoriaPadree = categoriaPadree;
}


public int getCodigo() {
	return codigo;
}
public void setCodigo(int codigo) {
	this.codigo = codigo;
}
public String getNombre() {
	return nombre;
}
public void setNombre(String nombre) {
	this.nombre = nombre;
}
public Categoria getCategoriaPadree() {
	return categoriaPadree;
}
public void setCategoriaPadree(Categoria categoriaPadree) {
	this.categoriaPadree = categoriaPadree;
}


@Override
public String toString() {
	return "Categoria [codigo=" + codigo + ", nombre=" + nombre + ", categoriaPadree=" + categoriaPadree + "]";
}



}
