package com.springboot.app.supermercado.dao;

import org.springframework.stereotype.Service;

import com.springboot.app.supermercado.entity.Mensaje;
import com.springboot.app.supermercado.service.MensajeService;

@Service
public class MensajeDao implements MensajeService {

	@Override
	public void notificacion(String msg) {
		Mensaje m = new Mensaje();
		m.setMensaje(msg);
		
		try {
			Thread.sleep(8000);
			m.run();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
