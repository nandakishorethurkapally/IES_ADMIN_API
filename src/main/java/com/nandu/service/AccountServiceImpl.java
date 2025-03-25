package com.nandu.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nandu.binding.UnlockUserAccForm;
import com.nandu.binding.UserAccountForm;


// represent tis calss as a spting bean so use "@Service " annotation
@Service
public class AccountServiceImpl implements AccountService {

	@Override
	public boolean createUserAccount(UserAccountForm accForm) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<UserAccountForm> fetchUserAccounts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserAccountForm getUserAccById(Integer accId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String changeAccountStatus(Integer accId, String status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String unlockUserAccount(UnlockUserAccForm unlockUserAccForm) {
		// TODO Auto-generated method stub
		return null;
	}

}
