package com.contact.service;

import java.util.List;

import com.contact.entity.UserAccount;
import com.contact.repository.UserAccountRepository;

public class UserAccountImpl implements UserAccountService {
	
	UserAccountRepository useraccounrreporitory;

	@Override
	public List<UserAccount> findUsersByid(long id) {
	
		return null;
	}
	
	

}
