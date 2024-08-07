package com.tucybank.app.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.tucybank.app.model.Client;

public class ClientJdbcDao implements ClientDao{
	@Autowired
	private JdbcDataSource dataSource;
	
	@Override
	public Client saveClient(Client client) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Client findClientById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Client> findAllClients() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Client deleteClient(Integer idClient) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Client updateClient(Client client) {
		// TODO Auto-generated method stub
		return null;
	}

}
