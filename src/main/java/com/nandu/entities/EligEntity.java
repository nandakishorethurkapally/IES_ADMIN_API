package com.nandu.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="EILG_DTLS")
public class EligEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer edgeTranceId;
	
	public Integer getEdgeTranceId() {
		return edgeTranceId;
	}

	public void setEdgeTranceId(Integer edgeTranceId) {
		this.edgeTranceId = edgeTranceId;
	}

	public Double getBenifitAmt() {
		return benifitAmt;
	}

	public void setBenifitAmt(Double benifitAmt) {
		this.benifitAmt = benifitAmt;
	}

	private String planStatus;
	
	
	private Double benifitAmt;
	

	public Double getBeniFitAmt() {
		return benifitAmt;
	}

	public void setBeniFitAmt(Double beniFitAmt) {
		this.benifitAmt = beniFitAmt;
	}

	public String getPlanStatus() {
		return planStatus;
	}

	public void setPlanStatus(String planStatus) {
		this.planStatus = planStatus;
	}

}
