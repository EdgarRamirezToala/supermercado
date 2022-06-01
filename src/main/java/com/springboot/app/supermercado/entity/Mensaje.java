package com.springboot.app.supermercado.entity;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Mensaje implements Runnable {
	 private static final Logger LOGGER = Logger.getLogger(
			    Thread.currentThread().getStackTrace()[0].getClassName());
	private String mensaje;
	
	public Mensaje() {}
	
	public Mensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	@Override
	public void run() {
		LOGGER.log(Level.INFO, mensaje);
	}

}
