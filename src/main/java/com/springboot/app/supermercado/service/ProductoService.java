package com.springboot.app.supermercado.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.springboot.app.supermercado.dto.CartDto;
import com.springboot.app.supermercado.dto.ItemDto;
import com.springboot.app.supermercado.entity.Producto;


public interface ProductoService {
	public List<Producto> getAllProductos();
	public String addToCart(int x_id_usuario, ItemDto item);
	public HashMap<Integer, ArrayList<CartDto>> checkCart(int x_id_usuario);
	public String updateCart(int x_id_usuario, ItemDto item);
	public String delete(int id);
	public Object getPosts();
}
