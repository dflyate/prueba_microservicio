package com.microservicio.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservicio.dto.ClienteDTO;
import com.microservicio.entity.Cliente;
import com.microservicio.mapper.ClienteMapper;
import com.microservicio.services.IClienteService;

import java.util.*;

@RestController
@RequestMapping("/api")
public class ClienteRestController {

	@Autowired
	private IClienteService clienteService;
	
	private ClienteMapper clienteMapper;
	
	private final Logger log = LoggerFactory.getLogger(ClienteRestController.class);
	
	public ClienteRestController() {
		clienteMapper = new ClienteMapper();
	}
	
	@GetMapping("/clientes/{tipoDoc}/{numDoc}")
	public ResponseEntity<?> show(@PathVariable String tipoDoc, @PathVariable String numDoc) {
		Cliente cliente = null;
		String error = null;
		log.info("Inicio del flujo de la función");
		Map<String, Object> response = new HashMap<>();
		try {
			if(tipoDoc.length() > 1 || (!tipoDoc.equals("C") && !tipoDoc.equals("P"))) {
				error = "Error al ingresar los parámetros de consulta";
				log.error(error);
				response.put("mensaje", error);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
			}
			Cliente clienteConsulta = new Cliente(tipoDoc,numDoc);
			cliente = clienteService.findByTipoDocumentoAndNumeroDocumento(clienteConsulta);
			log.info("Se realiza la consulta del cliente por documento "+tipoDoc+":"+numDoc);
		} catch (Exception e) {
			error = "Error al consultar el registro";
			log.error(error);
			response.put("mensaje",error );
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (cliente == null) {
			error = "El cliente con documento: ".concat(tipoDoc.concat(" ").concat(numDoc).concat(" no existe"));
			log.error(error);
			response.put("mensaje", error);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		log.info("Consulta exitosa");
		ClienteDTO clienteDTO = clienteMapper.mapearClienteDTO(cliente);
		return new ResponseEntity<ClienteDTO>(clienteDTO, HttpStatus.OK);
	}
}
