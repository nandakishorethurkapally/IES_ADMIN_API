package com.nandu.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nandu.binding.PlanForm;
import com.nandu.entities.PlanEntity;
import com.nandu.repositories.PlanRepo;

// represnt this class as a spring bean so represnt as a @service annotation
@Service
public class PlanServiceImpl implements PlanService{
	
	@Autowired
	private PlanRepo planRepo;

	@Override
	public boolean createPlan(PlanForm planForm) {
		
		PlanEntity planEntity = new PlanEntity();
		BeanUtils.copyProperties(planEntity, planForm);
		planEntity.setActiveSw("Y");
		planRepo.save(planEntity);
		
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<PlanForm> fetchPlans() {
		List<PlanEntity> planEntities =  planRepo.findAll();
		List<PlanForm> plans = new ArrayList<>();
		for(PlanEntity planEntity : planEntities) {
			PlanForm planObj = new PlanForm();
			BeanUtils.copyProperties(planEntity,planObj);
			plans.add(planObj);	
		}
		return plans;
	}

	@Override
	public PlanForm getPlanById(Integer planId) {
		Optional<PlanEntity> optional = planRepo.findById(planId);
		if(optional.isPresent()) {
			PlanEntity planEntity = optional.get();
			PlanForm planData = new PlanForm();
			BeanUtils.copyProperties(planEntity, planData);
			return planData;
		}
		return null;
	}

	@Override
	public String changePlanStatus(Integer planId, String status) {
			 
		// update operation will sucess one row will be update then in cnt varibale store "1"
			 int cnt  = planRepo.updatePlanStatus(planId, status);
			 
			 if(cnt > 0) {
				 return "status changed in plan";
			 }
		
		
		return "Failed to change status in plan";
	}
}
