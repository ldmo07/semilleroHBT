package com.hbt.semillero.exceptions;

/*@descripcion clase que extiende de la super clase Exception se encargara de manejar las excepciones del comic*/

public class ComicException extends Exception {

	private static final long serialVersionUID = 1L;
	private String codigo;
	private String mensaje;
	
	
	
	
	public ComicException(String codigo, String mensaje) {
		super();
		this.codigo = codigo;
		this.mensaje = mensaje;
	}

	public ComicException(String codigo,String mensaje,Throwable causa) {
		super(mensaje,causa);
		this.codigo=codigo;
		this.mensaje=mensaje;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
	
}
