package com.tucybank.app.dao;

import java.util.List;

import com.tucybank.app.model.Account;

public interface AccountDao {
	public Account saveAccount(Account account);
	public Account findAccountById(Integer id);
	public List<Account> findAllAccounts();
	public Account deleteAccount(Integer idAccount);
	public Account updateAccount(Account account);
}
