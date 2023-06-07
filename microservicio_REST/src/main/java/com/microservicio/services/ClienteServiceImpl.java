package com.microservicio.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservicio.dao.IClienteDao;
import com.microservicio.entity.Cliente;

@Service
public class ClienteServiceImpl implements IClienteService{
	
	@Autowired
	private IClienteDao clienteDao;

	@Override
	public Cliente findByTipoDocumentoAndNumeroDocumento(Cliente cliente) {
		return clienteDao.findByTipoDocumentoAndNumeroDocumento(cliente);
	}

}
