package com.springboot.app.supermercado.dto;

import java.io.Serializable;

public class ItemDto implements Serializable {
	private int idProducto;
	private int cantidad;
	private static final long serialVersionUID = 1L;
	
	public ItemDto() {}

	public ItemDto(int idProducto, int cantidad) {
		this.idProducto = idProducto;
		this.cantidad = cantidad;
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

	
}