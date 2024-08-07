package com.tucybank.app.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.tucybank.app.model.Account;

public class AccountJdbcDao implements AccountDao{
	@Autowired
	private JdbcDataSource dataSource;
	
	@Override
	public Account saveAccount(Account account) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account findAccountById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Account> findAllAccounts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account deleteAccount(Integer idAccount) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account updateAccount(Account account) {
		// TODO Auto-generated method stub
		return null;
	}

}
