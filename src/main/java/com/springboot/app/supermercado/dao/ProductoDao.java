package com.springboot.app.supermercado.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.app.supermercado.dto.CartDto;
import com.springboot.app.supermercado.dto.ItemDto;
import com.springboot.app.supermercado.entity.Producto;
import com.springboot.app.supermercado.service.MensajeService;
import com.springboot.app.supermercado.service.ProductoService;

@Service
public class ProductoDao implements ProductoService {
	int pid = 0;

	@Value("${jsonplaceholder.url}")
	private String url;

	File file = new File(
			"C:\\SpringCloud\\workspace\\springboot-service-supermercado\\src\\main\\resources\\static\\db\\productos.json");
	HashMap<Integer, Producto> productos = new HashMap<Integer, Producto>();
	ObjectMapper mapper = new ObjectMapper();

	@Autowired
	private RestTemplate clienteRest;

	@Autowired
	HashMap<Integer, ArrayList<ItemDto>> clientes;

	@Autowired
	MensajeService mensaje;

	@Override
	public List<Producto> getAllProductos() {
		List<Producto> productos = new ArrayList<Producto>();
		this.getProductos().keySet().stream().forEach(index -> productos.add(this.getProductos().get(index)));

		return productos;
	}

	@Override
	public String addToCart(int x_id_usuario, ItemDto item) {
		ArrayList<ItemDto> cart = clientes.get(x_id_usuario);
		Optional<Producto> productos = Optional.ofNullable(getProductoById(item.getIdProducto()));

		if (productos.isPresent()) {
			if (item.getCantidad() > 0) {
				cart.add(item);
				clientes.put(x_id_usuario, cart);
				mensaje.notificacion("El producto ha sido agregado exitosamente.");
				
				return "Producto agregado al carrito!";
			} else {
				return "La cantidad debe ser mayor a 0";
			}
		} else {
			return "El producto no existe!";
		}
	}

	@Override
	public HashMap<Integer, ArrayList<CartDto>> checkCart(int x_id_usuario) {
		ArrayList<ItemDto> cart = clientes.get(x_id_usuario);
		HashMap<Integer, ArrayList<CartDto>> productos = new HashMap<Integer, ArrayList<CartDto>>();
		ArrayList<CartDto> list = new ArrayList<CartDto>();

		cart.stream().forEach(p -> { 
			CartDto producto = new CartDto(getProductoById(p.getIdProducto()), p.getCantidad());
			list.add(producto);
		});
		productos.put(x_id_usuario, list);

		return productos;
	}

	@Override
	public String updateCart(int x_id_usuario, ItemDto item) {
		ArrayList<ItemDto> cart = clientes.get(x_id_usuario);
		Optional<Producto> producto = Optional.ofNullable(getProductoById(item.getIdProducto()));

		if (producto.isPresent()) {
			if (item.getCantidad() > 0) {
				cart.stream().forEach(p -> p.setCantidad(p.getCantidad() + item.getCantidad()));
			} else {
				return "La cantidad debe ser mayor a 0";
			}
		} else {
			return "El producto no existe!";
		}

		return "Producto actualizado en el carrito!";
	}

	@Override
	public String delete(int id) {
		HashMap<Integer, Producto> db = this.getProductos();
		db.remove(id);
		this.persistence(file, db);

		return "Producto " + id + " ha sido eliminado!";
	}

	@Override
	public Object getPosts() {
		return clienteRest.getForObject(url, Object.class);
	}

	public Producto getProductoById(int id) {
		return this.getProductos().get(id);
	}

	public HashMap<Integer, Producto> getProductos() {
		HashMap<Integer, Producto> productos = new HashMap<Integer, Producto>();

		try {
			ObjectMapper mapper = new ObjectMapper();
			InputStream is = new FileInputStream(file);
			TypeReference<HashMap<Integer, Producto>> typeReference = new TypeReference<HashMap<Integer, Producto>>() {
			};

			productos = mapper.readValue(is, typeReference);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return productos;
	}

	public void persistence(File file, HashMap<Integer, Producto> hash) {
		try {
			mapper.writeValue(file, hash);
		} catch (JsonGenerationException e) {
			System.out.println(e.getMessage());
		} catch (JsonMappingException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}
