package com.springboot.app.supermercado.config;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.springboot.app.supermercado.dto.ItemDto;


@Configuration
public class AppConfig {	
	@Bean("clienteRest")
	public RestTemplate registrarRestTemplate() {
		return new RestTemplate();
	}
	
	@Bean
	public HashMap<Integer, ArrayList<ItemDto>> clientes() {		
		HashMap<Integer, ArrayList<ItemDto> > clientes = new HashMap<Integer, ArrayList<ItemDto>>();		
		ArrayList<ItemDto> cart_1 = new ArrayList<ItemDto>();
		ArrayList<ItemDto> cart_2 = new ArrayList<ItemDto>();
		ArrayList<ItemDto> cart_3 = new ArrayList<ItemDto>();
		cart_1.add(new ItemDto(1,19));
		cart_2.add(new ItemDto(2,2));
		cart_3.add(new ItemDto(4,5));
		
		clientes.put(1, cart_1);
		clientes.put(2, cart_2);
		clientes.put(3, cart_3);
		
		return clientes;
	}
}
