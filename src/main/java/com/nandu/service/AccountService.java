package com.nandu.service;
import java.util.List;

import com.nandu.binding.UnlockAccForm;
//import com.nandu.binding.UnlockUserAccForm;
import com.nandu.binding.UserAccForm;
//import com.nandu.binding.UserAccountForm;

public interface AccountService {
	
	// will it create account and take data from 'UserAccountForm'
	// this "UserAccountForm" method is responsible for take foem data from the user
	// if we can doing any req and responce we need parameter and also return type
	public boolean createUserAccount(UserAccForm accForm);

	// this method is responsible for give existing account from data base 
	// taking coolection obhect why because we shuld get more then one object
	public List<UserAccForm> fetchUserAccounts();
	
	//edit user details based on ID and display editable m
	public UserAccForm getUserAccById(Integer accId);
	
	//change account status like activate and de-activate based on account ID and we need account preset Status
	public String changeAccountStatus(Integer accId , String status);
	
	// unlock user account using "UserAccountForm" object
	public String unlockUserAccount(UnlockAccForm unlockUserAccForm);
	

}
