package com.springboot.app.supermercado.dto;

import java.io.Serializable;

import com.springboot.app.supermercado.entity.Producto;

public class CartDto implements Serializable {
	private Producto producto;
	private int cantidad;
	private static final long serialVersionUID = 1L;
	
	public CartDto() {}

	public CartDto(Producto producto, int cantidad) {
		this.producto = producto;
		this.cantidad = cantidad;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	@Override
	public String toString() {
		return "CartDto [producto=" + producto + ", cantidad=" + cantidad + "]";
	}	
}
