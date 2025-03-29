package com.nandu.binding;

import lombok.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LoginForm {
	// login form required email and password
	 	private String email;
	    private String pwd;

}
