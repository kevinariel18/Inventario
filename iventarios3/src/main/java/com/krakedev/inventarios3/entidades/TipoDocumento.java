package com.krakedev.inventarios3.entidades;

public class TipoDocumento {
	private String codigoTipoDoc;
    private String descripcion;

    
    
    
    
   
    public TipoDocumento() {
		super();
	}

	public TipoDocumento(String codigoTipoDoc, String descripcion) {
		super();
		this.codigoTipoDoc = codigoTipoDoc;
		this.descripcion = descripcion;
	}

	public String getCodigoTipoDoc() {
        return codigoTipoDoc;
    }

    public void setCodigoTipoDoc(String codigoTipoDoc) {
        this.codigoTipoDoc = codigoTipoDoc;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

	@Override
	public String toString() {
		return "TipoDocumento [codigoTipoDoc=" + codigoTipoDoc + ", descripcion=" + descripcion + "]";
	}



}
