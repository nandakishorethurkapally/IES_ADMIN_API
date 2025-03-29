package com.nandu.contoller;

import java.util.List;

import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nandu.binding.UserAccForm;
import com.nandu.service.AccountService;

@RestController
public class AccountRestController {
	
	/* creatng logger obj :  which is used to genarate log messages 
	 */
	
	private Logger logger = (Logger) LoggerFactory.getLogger(AccountRestController.class);
	
	/* 1. inject accServcie class becoz we wrote account relatedfunctions service layer
	 * 2. mapping req to post mapping create account 
	 * 3. give paramaters 2 that send form data in "REQUESTBODY" & sending data from "UserAccForm" object 
	 * 4. create account method return true or false values 
	 * 5. if create() return true then record created false means record not created
	 * 6. status true create response entity objt send ResponseEntity objet status and http status code also we need to send
	 * 7. true then send response body along with status codes
	 */
	
	@Autowired
	private AccountService accService;
	
	// create user account
	@PostMapping("/user")
	public ResponseEntity<String> createAccount(@RequestBody UserAccForm userAccForm) {
		
		logger.debug("Account creation process start");
		
		boolean status = accService.createUserAccount(userAccForm);
		
		logger.debug("Account creation process completed");
		
		if(status) {
				
				logger.info("Account create susessfully");
				
				return new ResponseEntity<>("Account created" , HttpStatus.CREATED);// 201 : recordd crated 
		} else {
			logger.info("Account creattion failed");
			return new ResponseEntity<>("Account not created" , HttpStatus.INTERNAL_SERVER_ERROR);// 500 : internal server errror
		}
	}
	
	/* view account
	 * 1. create get mapping
	 * 2. get user data from service layer
	 * 3. store it into LIst UserAccForm Object
	 * 4. create ResponseEntity objet and send data and status code 
	 */
	@GetMapping("/users")
	// multiple user account will send so we taken as a  "<List<UserAccForm>>"
	public ResponseEntity<List<UserAccForm>> getUsers(){

		 logger.debug(" fetching user account process started ");
		 List<UserAccForm> userAccForms  = accService.fetchUserAccounts();
		 logger.debug(" fetching user account process ended ");
		 logger.info("User Account fetch sucess");
		 return new ResponseEntity<>(userAccForms , HttpStatus.OK);
	}
	
	/* edit functionality based on userId
	 * 1.mapping with the get Request alsong with pathVariable
	 * 2. get user details based on the id 
	 * 3. sending to UI to send response with responseEntity object then show that edited record in UI 
	 */
	@GetMapping("/user/{userId}")
	public ResponseEntity<UserAccForm> getUser(@PathVariable("userId") Integer userId) {
		UserAccForm userAccById = accService.getUserAccById(userId);
		logger.info("User Account fetched sucess");
		return new ResponseEntity<>(userAccById , HttpStatus.OK);
	}
	
	/*change user status  nothing but updating user (activate or deactive account)
	 * will update record and dispaly updated record
	 * based on user id we need to update and what is status we want to update
	 * userId and status we should get in path varibale
	 * we have to set two path varibles 
	 * are u want to activate or deactivate
	 * ====================================
	 * 1.put mapping 
	 * 			ex: user/12/activate
	 * 
	 * 2. changeaccount status with userId and status  will retur true or false 
	 * 		wether record updated or not
	 * 3. after updation of status we need to display leatest record and page will reload and display all updated records in the table  
	 * (below three lines of code will work like this)
	 *  IMP : change account status fetch leatest records return leatest records from table
	 */
	@PutMapping("/user/{userId}/{status}")
	public ResponseEntity<List<UserAccForm>> updateUserAcc(	
															@PathVariable("userId") Integer userId , 
															@PathVariable("status") String status)
	{
		
		logger.debug("  user account update process started ");
		accService.changeAccountStatus(userId, status);
		logger.debug("user account update process ended ");
		logger.info("User Account updated sucessfully");
		List<UserAccForm> userAccForms  = accService.fetchUserAccounts();
		
		return new ResponseEntity<>(userAccForms , HttpStatus.OK);	
	}	
	
}
