package com.nandu.service;

import java.util.List;

import com.nandu.binding.PlanForm;

// witing all fucntonalities related to plans 
public interface PlanService {
	
	// this function we can use to create paln for the user\
	
	public boolean createPlan(PlanForm planForm);
	
	// this second function to view page for getting data from the data base
	public List<PlanForm> fetchPlans( );
	
	// edit funcionlity using based on Id
	public PlanForm getPlanById(Integer planId);
	
	// changr account status 
	public String changePlanStatus(Integer planId, String status);

}

