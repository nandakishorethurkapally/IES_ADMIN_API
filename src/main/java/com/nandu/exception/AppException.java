package com.nandu.exception;

import java.time.LocalDateTime;

/* CREATING CLASS WHICH IS USED TO REPRESENT EXXCEPTION INFORMATION 
 * 1.class name = "AppException"
 * 2.declare varible names : exCode , exDesc and exDate
 * 
 */
public class AppException {
	
	private String exCode;
	
	public String getExCode() {
		return exCode;
	}

	public void setExCode(String exCode) {
		this.exCode = exCode;
	}

	public String getExDesc() {
		return exDesc;
	}

	public void setExDesc(String exDesc) {
		this.exDesc = exDesc;
	}

	public LocalDateTime getExDate() {
		return exDate;
	}

	public void setExDate(LocalDateTime exDate) {
		this.exDate = exDate;
	}

	private String exDesc;
	
	private LocalDateTime exDate;

}
