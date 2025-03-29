package com.nandu.binding;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class UserAccForm {
	
		// UserAccForm
	 	private String fullName;
	    private String email;
	    private Long mobileNo;
	    private String gender;
	    private LocalDate dob;
	    private String password;
	    private Long ssn;
	    private String activeSw;
	    private Integer roleId;
}
