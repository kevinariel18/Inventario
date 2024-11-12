package com.krakedev.inventarios3.entidades;

import java.sql.Timestamp;

public class HistorialStock {
    private int codigo;
    private Timestamp fecha;
    private String referencia;
    private int idProducto;
    private int cantidad;

    // Constructor
    public HistorialStock(int codigo, Timestamp fecha, String referencia, int idProducto, int cantidad) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.referencia = referencia;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
    }

    // Getters y Setters
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "HistorialStock{" +
                "codigo=" + codigo +
                ", fecha=" + fecha +
                ", referencia='" + referencia + '\'' +
                ", idProducto=" + idProducto +
                ", cantidad=" + cantidad +
                '}';
    }
}