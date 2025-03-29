
/*
 * testing 
 * logging
 * exception handling 
 * above things we have to impelement
 */


package com.nandu.binding;

public class DashBoardCards {
	
	
	private Long plansCnt;
	
	private Long approvedCnt;
	
	private Long DeniedCnt;
	
	private Double benifitAmtGiven;
	
	// using this varibale we can send user details also in the respose 
	private UserAccForm user;

	public UserAccForm getUser() {
		return user;
	}

	public void setUser(UserAccForm user) {
		this.user = user;
	}

	public Long getPlansCnt() {
		return plansCnt;
	}

	public void setPlansCnt(Long plansCnt) {
		this.plansCnt = plansCnt;
	}

	public Long getApprovedCnt() {
		return approvedCnt;
	}

	public void setApprovedCnt(Long approvedCnt) {
		this.approvedCnt = approvedCnt;
	}

	public Long getDeniedCnt() {
		return DeniedCnt;
	}

	public void setDeniedCnt(Long deniedCnt) {
		DeniedCnt = deniedCnt;
	}

	public Double getBenifitAmtGiven() {
		return benifitAmtGiven;
	}

	public void setBenifitAmtGiven(Double benifitAmtGiven) {
		this.benifitAmtGiven = benifitAmtGiven;
	}

}
