package com.krakedev.inventarios3.entidades;



public class EstadoPedido {

  
    private char codigo;
    private String descripcion;

    
    public EstadoPedido() {
    }

  
    public EstadoPedido(char codigo, String descripcion) {
        this.codigo = codigo;
        this.descripcion = descripcion;
    }


    public char getCodigo() {
        return codigo;
    }

    public void setCodigo(char codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

  
    @Override
    public String toString() {
        return "EstadoPedido{" +
                "codigo=" + codigo +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
