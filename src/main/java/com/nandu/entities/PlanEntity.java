package com.nandu.entities;

import java.time.LocalDate;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="IES_PLANS")
public class PlanEntity  {
	
	private Integer planId;
	private String planCategory;
    private String planName;
    private LocalDate planStartDate;
    private LocalDate planEndDate;
    private String activeSw;
    
    

	public Integer getPlanId() {
		return planId;
	}



	public void setPlanId(Integer planId) {
		this.planId = planId;
	}



	public String getPlanCategory() {
		return planCategory;
	}



	public void setPlanCategory(String planCategory) {
		this.planCategory = planCategory;
	}



	public String getPlanName() {
		return planName;
	}



	public void setPlanName(String planName) {
		this.planName = planName;
	}



	public LocalDate getPlanStartDate() {
		return planStartDate;
	}



	public void setPlanStartDate(LocalDate planStartDate) {
		this.planStartDate = planStartDate;
	}



	public LocalDate getPlanEndDate() {
		return planEndDate;
	}



	public void setPlanEndDate(LocalDate planEndDate) {
		this.planEndDate = planEndDate;
	}



	public String getActiveSw() {
		return activeSw;
	}



	public void setActiveSw(String activeSw) {
		this.activeSw = activeSw;
	}



	public UserEntity getUser() {
		return user;
	}



	public void setUser(UserEntity user) {
		this.user = user;
	}



	/*make a relationship with ussers mutilple plans one user can have do me make a relathionship
     * is "many to one"
     * -> specify the column that will store the foreign key reference to the UserEntity in the Plan table.
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="user_id") // jpin this colum inside plan table
    private UserEntity user; // declares a field inside the Plan entity that represents a many-to-one relationship with the UserEntity.
    
	
	

}
