package com.nandu.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nandu.binding.UserAccountForm;

// represnt this class as a spring bean so represnt as a @service annotation
@Service
public class PlanServiceImpl implements PlanService{

	@Override
	public String createUserRole(UserAccountForm accId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserAccountForm> retrivePlans() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserAccountForm getUserAccById(Integer accId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String changeAccStatus(Integer accId, String ststus) {
		// TODO Auto-generated method stub
		return null;
	}

}
