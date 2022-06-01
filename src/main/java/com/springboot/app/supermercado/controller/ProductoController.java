package com.springboot.app.supermercado.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.app.supermercado.dto.CartDto;
import com.springboot.app.supermercado.dto.ItemDto;
import com.springboot.app.supermercado.entity.Producto;
import com.springboot.app.supermercado.service.ProductoService;

@RestController
public class ProductoController {
	@Autowired
	private ProductoService service;
	
	@GetMapping("/productos")
	public List<Producto> getAllProductos() {
		return service.getAllProductos();
	}
	
	@PostMapping("/cart/add")
	public ResponseEntity<Object> addToCart(@RequestHeader("x-id-usuario") int x_id_usuario, @RequestBody  ItemDto producto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message", service.addToCart(x_id_usuario, producto)));
	}
	
	@GetMapping("/cart")
	public HashMap<Integer, ArrayList<CartDto>> checkCart(@RequestHeader("x-id-usuario") int x_id_usuario){
		return service.checkCart(x_id_usuario);
	}
	
	@PutMapping("/cart")
	public ResponseEntity<Object> updateCart(@RequestHeader("x-id-usuario") int x_id_usuario, @RequestBody  ItemDto producto) {
		return ResponseEntity.status(HttpStatus.OK).body(Map.of("message",service.updateCart(x_id_usuario, producto)));
	}
	
	@GetMapping("posts")
	public Object getPosts() {
		return service.getPosts();
	}
	
	@DeleteMapping("/productos/{id}")
	public ResponseEntity<Object> eliminar(@PathVariable int id) {
		return ResponseEntity.status(HttpStatus.OK).body(Map.of("message", service.delete(id)));
	}
}
