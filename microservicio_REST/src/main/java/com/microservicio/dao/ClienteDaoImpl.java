package com.microservicio.dao;

import java.util.*;

import org.springframework.stereotype.Repository;

import com.microservicio.entity.Cliente;

@Repository
public class ClienteDaoImpl implements IClienteDao{
	
	private List<Cliente> clientes = null;
	
	public ClienteDaoImpl() {
		clientes = new ArrayList<>();
		clientes.add(registrarUnicoCliente());
	}
	
	public Cliente registrarUnicoCliente() {
		return new Cliente("C","23445322","Santiago","David","Romero","Sanabria","7612970","CRA 3 ESTE # 95 63 SUR","Bogotá");
	}
	

	@Override
	public Cliente findByTipoDocumentoAndNumeroDocumento(Cliente cliente) {
		Cliente clienteConsultado = clientes.get(0);
		if(clienteConsultado.getTipoDocumento().equals(cliente.getTipoDocumento()) && clienteConsultado.getNumeroDocumento().equals(cliente.getNumeroDocumento())) {
			return clientes.get(0);
		}
		return null;
	}

}
