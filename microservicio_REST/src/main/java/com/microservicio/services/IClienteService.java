package com.microservicio.services;

import com.microservicio.entity.Cliente;

public interface IClienteService {
	
	public Cliente findByTipoDocumentoAndNumeroDocumento(Cliente cliente);

}
