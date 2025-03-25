package com.nandu.service;

import com.nandu.binding.DashBoardCards;
import com.nandu.binding.LoginForm;

// this is for (login /forgot / dashboard/profile operatiosn) 
public interface UserService {
	
	/*  implement login functionaliyy using username and password as a parameters 
	 *  here we can get FormData as input so we takne FormData as a paramter 
	 *   this function will say login "sucess" OR "invalid credentials" OR account is "locked"
	 */
	public String logIn(LoginForm loginForm);

	/*
	 * create method for ;forgotton pwd
	 * if the person given that emial it will check that email existing account their or not 
	 * it the person have eaccount with existing email it will send email to that particular email with msge
	 * "YPUR PSWRD SEND TO THAT PARTICULAR EMAIL" 
	 * if that person kdoesnot have account with that emial u have to dispaly that msgyour emia, id does not have account 
	 * with that emial
	 */
	public String recovaryPassword(String email);
	
	
	// lif login sucess then we should get dashboard data 
	public DashBoardCards fetchDasgboardInfo();
}
