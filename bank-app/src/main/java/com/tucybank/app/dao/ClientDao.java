package com.tucybank.app.dao;

import java.util.List;

import com.tucybank.app.model.Client;

public interface ClientDao {
	public Client saveClient(Client client);
	public Client findClientById(Integer id);
	public List<Client> findAllClients();
	public Client deleteClient(Integer idClient);
	public Client updateClient(Client client);
}
