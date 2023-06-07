package com.microservicio.dao;

import com.microservicio.entity.Cliente;

public interface IClienteDao {
	
	public Cliente findByTipoDocumentoAndNumeroDocumento(Cliente cliente);

}
