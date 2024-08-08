package com.tucybank.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tucybank.app.model.Client;

@Repository
public class ClientJdbcDao implements ClientDao {
	
	@Autowired
	private JdbcDataSource dataSource;

	public ClientJdbcDao() {}
	
	@Override
	public Client saveClient(Client client) {
		System.out.println("jdbc save Client");
		Client clientExist = findClientById(client.getIdCilent());
		if (clientExist != null) {
			try (Connection connection = dataSource.getConnection()) {
				PreparedStatement ps = connection.prepareStatement(
						"INSERT INTO Client(id_cilent, name, last_name, id_account) " + "VALUES(?,?,?,?)");
				ps.setInt(1, client.getIdCilent());
				ps.setString(2, client.getName());
				ps.setString(3, client.getLastName());
				ps.setInt(4, client.getIdAccount());
				int i = ps.executeUpdate();
				if (i == 1) {
					// ps.getGeneratedKeys()
					System.out.println("jdbc saved Client info to database");
					System.out.println(client.toString());
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public Client findClientById(Integer id) {
		System.out.println("jdbc find Client by id " + id);

		try (Connection connection = dataSource.getConnection()) {
			PreparedStatement ps = connection
					.prepareStatement("SELECT id_cilent, name, last_name, id_account FROM Client WHERE id_cilent=?");
			ps.setInt(1, id);
			Client client = new Client();
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					client.setIdCilent(rs.getInt("id_cilent"));
					client.setName(rs.getString("name"));
					client.setLastName(rs.getString("last_name"));
					client.setIdAccount(rs.getInt("id_account"));
					System.out.println(client.toString());
				}
				return client;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Client> findAllClients() {
		System.out.println("findAllAccounts");
		List<Client> clients = new ArrayList<Client>();
		try (Connection connection = dataSource.getConnection()) {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt
					.executeQuery("Select id_cilent, name, last_name, id_account FROM Client");
			while (rs.next()) {
				Client client = new Client();
				client.setIdCilent(rs.getInt("id_cilent"));
				client.setName(rs.getString("name"));
				client.setLastName(rs.getString("last_name"));
				client.setIdAccount(rs.getInt("id_account"));
				clients.add(client);
			}
			System.out.println(clients);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Client deleteClient(Integer idClient) {
		System.out.println("jdbc delete Client");
		Client target = findClientById(idClient);

		if (target != null) {
			try (Connection connection = dataSource.getConnection()) {
				PreparedStatement ps = connection.prepareStatement("DELETE FROM Client WHERE id_cilent= ?");
				ps.setInt(1, idClient);
				int i = ps.executeUpdate();
				if (i == 1) {
					System.out.println("jdbc delete Client " + target.toString());
					return target;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public Client updateClient(Client client) {
		Client target = findClientById(client.getIdCilent());
		if (target != null) {
			try (Connection connection = dataSource.getConnection()) {
				PreparedStatement ps = connection
						.prepareStatement("UPDATE Client SET name=? last_name=? id_account=? WHERE id_cilent=?");
				ps.setString(1, client.getName());
				ps.setString(2, client.getLastName());
				ps.setInt(3, client.getIdAccount());
				int i = ps.executeUpdate();
				if (i == 1) {
					System.out.println("jdbc update Client " + target.toString());
					return target;
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return null;
	}
}
