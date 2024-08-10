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

import com.tucybank.app.model.Account;
import com.tucybank.app.model.Client;

@Repository
public class AccountJdbcDao implements AccountDao {
	@Autowired
	private JdbcDataSource dataSource;
	@Autowired
	ClientJdbcDao clientJdbcDao;

	@Override
	public Account saveAccount(Account account) {
		System.out.println("jdbc save Account");
		Client clientExist = clientJdbcDao.findClientById(account.getIdClient());
		if (clientExist != null) {
			try (Connection connection = dataSource.getConnection()) {
				PreparedStatement ps = connection.prepareStatement(
						"INSERT INTO ACCOUNT(id_account, id_cilent, total_balance) " + "VALUES(?,?,?)");
				ps.setInt(1, account.getIdAcount());
				ps.setInt(2, account.getIdClient());
				ps.setFloat(3, account.getTotalBalance());
				int i = ps.executeUpdate();
				if (i == 1) {
					// ps.getGeneratedKeys()
					System.out.println("jdbc saved account info to database");
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public Account findAccountById(Integer id) {
		System.out.println("jdbc find Account by id " + id);

		try (Connection connection = dataSource.getConnection()) {
			PreparedStatement ps = connection
					.prepareStatement("SELECT id_account, id_cilent, total_balance FROM ACCOUNT WHERE id_account=?");
			ps.setInt(1, id);
			Account account = new Account();
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					account.setIdAcount(rs.getInt("id_account"));
					account.setIdClient(rs.getInt("id_cilent"));
					account.setTotalBalance(rs.getFloat("total_balance"));
					System.out.println(account.toString());
				}
				return account;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Account> findAllAccounts() {
		System.out.println("findAllAccounts");
		List<Account> accounts = new ArrayList<Account>();
		try (Connection connection = dataSource.getConnection()) {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("Select id_account, id_cilent, total_balance FROM ACCOUNT");
			while (rs.next()) {
				Account account = new Account();
				account.setIdAcount(rs.getInt("id_account"));
				account.setIdClient(rs.getInt("id_cilent"));
				account.setTotalBalance(rs.getFloat("total_balance"));
				account.setIdAcount(rs.getInt("id_account"));
				accounts.add(account);
			}
			System.out.println(accounts);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Account deleteAccount(Integer idAccount) {
		System.out.println("jdbc delete Account");
		Account target = findAccountById(idAccount);

		if (target != null) {
			try (Connection connection = dataSource.getConnection()) {
				PreparedStatement ps = connection.prepareStatement("DELETE FROM ACCOUNT WHERE id_account= ?");
				ps.setInt(1, idAccount);
				int i = ps.executeUpdate();
				if (i == 1) {
					System.out.println("jdbc delete Account " + target.toString());
					return target;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public Account updateAccount(Account account) {
		System.out.println("jdbc update Account");
		Account target = findAccountById(account.getIdAcount());
		if (target != null) {
			try (Connection connection = dataSource.getConnection()) {
				PreparedStatement ps = connection
						.prepareStatement("UPDATE ACCOUNT SET total_balance=? WHERE id_account=?");
				ps.setFloat(1, account.getTotalBalance());
				ps.setInt(2, account.getIdAcount());
				int i = ps.executeUpdate();
				if (i == 1) {
					System.out.println("jdbc update account " + target.toString());
					return target;
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return null;
	}

	public Account saveAccount(Integer idClient, Account account) {
		System.out.println("jdbc update Account");
		// find if client exist
		int clientId = account.getIdClient();
		if (clientId == idClient) {

			Client clientExist = clientJdbcDao.findClientById(account.getIdClient());
			if (clientExist != null) {
				try (Connection connection = dataSource.getConnection()) {
					PreparedStatement ps = connection.prepareStatement(
							"INSERT INTO ACCOUNT(id_account, id_cilent, total_balance) " + "VALUES(?,?,?)");
					ps.setInt(1, account.getIdAcount());
					ps.setInt(2, account.getIdClient());
					ps.setFloat(3, account.getTotalBalance());
					int i = ps.executeUpdate();
					if (i == 1) {
						// ps.getGeneratedKeys()
						System.out.println("jdbc saved account info to database");
					}
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		} // if no client exist, we don't save the new account since every account needs
			// to have a client
		return null;
	}
}
