package com.nandu.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nandu.entities.PlanEntity;

public interface PlanRepo extends JpaRepository<PlanEntity , Integer>{
	
	@Query("select PlanEntity set status=:status where planId=:planId")
	public Integer updatePlanStatus(Integer planId, String status);

}
