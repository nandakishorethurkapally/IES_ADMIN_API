package com.nandu.service;

import org.springframework.stereotype.Service;

import com.nandu.binding.DashBoardCards;
import com.nandu.binding.LoginForm;


// represent this class as a a spring bean use @Serviceannotation
@Service
public class UserServiceImpl  implements UserService{

	@Override
	public String logIn(LoginForm loginForm) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String recovaryPassword(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DashBoardCards fetchDasgboardInfo() {
		// TODO Auto-generated method stub
		return null;
	}

}
