package com.nandu.service;

import java.util.List;

import com.nandu.binding.UserAccountForm;


// witing all fucntonalities related to plans 
public interface PlanService {
	
	// this function we can use to create paln for the user\
	
	public String createUserRole(UserAccountForm accId);
	
	// this second function to view page for getting data from the data base
	public List<UserAccountForm> retrivePlans();
	
	// edit funcionlity using based on Id
	public UserAccountForm getUserAccById(Integer accId);
	
	// changr account status 
	public String changeAccStatus(Integer accId , String ststus);

}
