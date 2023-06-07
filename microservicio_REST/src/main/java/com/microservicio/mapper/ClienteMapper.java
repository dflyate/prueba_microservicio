package com.microservicio.mapper;

import com.microservicio.dto.ClienteDTO;
import com.microservicio.entity.Cliente;

public class ClienteMapper {

	public ClienteDTO mapearClienteDTO(Cliente cliente) {
		return new ClienteDTO(cliente.getPrimerNombre(), cliente.getSegundoNombre(), cliente.getPrimerApellido(), cliente.getSegundoApellido(), cliente.getTelefono(), cliente.getDireccion(), cliente.getCiudadResidencia());
	}
}
