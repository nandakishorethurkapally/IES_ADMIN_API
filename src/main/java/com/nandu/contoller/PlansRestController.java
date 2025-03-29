package com.nandu.contoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nandu.binding.PlanForm;
import com.nandu.service.PlanService;

@RestController
public class PlansRestController {
	
	@Autowired
	private PlanService planService;
	// CREATING PLAN
	@PostMapping("/plan")
	public ResponseEntity<String> createPlan(@RequestBody PlanForm planForm){
		
		boolean status = planService.createPlan(planForm);
		if(status) {
			return new ResponseEntity<>("Plan Created" , HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>("Plan NOT Created" , HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	// RETIVING PLANS EXISTING INSIDE THE DATABASE
	@GetMapping("/plans")
	public ResponseEntity<List<PlanForm>> getPlans(){
		List<PlanForm> PlanForms = planService.fetchPlans();
		return new ResponseEntity<>(PlanForms , HttpStatus.OK);
	}
	
	// EDIT PLANE BASED ON USERID
	@GetMapping("/plan/{planId}")
	public ResponseEntity<PlanForm> getPlan(@PathVariable("planId") Integer planId){
		PlanForm planById = planService.getPlanById(planId);
		return new ResponseEntity<>(planById , HttpStatus.OK);
	} 
	
	// change plan based on the planId and update plan status take two parameters PlanId and planStatus
	@PutMapping("/plan/{planId}/{status}")
	public ResponseEntity<List<PlanForm>> updatePlan(@PathVariable("planId") Integer planId ,
													 @PathVariable("status") String status)
	{
		 planService.changePlanStatus(planId, status);
		 // after updating this we should fetch updated records
		 List<PlanForm> PlanForms = planService.fetchPlans();
		return new ResponseEntity<>(PlanForms , HttpStatus.OK);
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
