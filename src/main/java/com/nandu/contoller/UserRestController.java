package com.nandu.contoller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nandu.binding.DashBoardCards;
import com.nandu.binding.LoginForm;
import com.nandu.binding.UserAccForm;
import com.nandu.service.AccountService;
import com.nandu.service.UserService;

/* ------------------------------------IMP THINGS------------------------------------
 * THIS CONTROLLER HAVE A THINGS :
 * -------------------------------
 * -> MAINLY 4 METHODS ARE REQ FOR USER REST CONTROLLER
 * -> LOGIN FUNCTIONALITY
 * -> FORGOOT PASSWORD FU
 * -> DASHBOARD FUNCTONALITY
 * -> PROFILE FUNCTIONALITY
 * 
 * ------------------------------------------------------------------------
 * STEPS TO REQ FOR TIS CONTROLLER AND BUILDING FUNCTIONS 
 * 
 * 1.first inject user service 
 * 2.create login Function with LoginForm Object and post mapping
 * 3.check and login " LOG_IN sucess " i need to send dashboard data rediect to dashbpard data (Load content req for Dashboard)
 * 4. redirecting dashboard with email in query paramter
 * 5. login is failure as it is error msg i need to send 
 * 
 * 
 * thory : (about login )
 * -
 * 
 */

@RestController
public class UserRestController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AccountService accService;
	
	@PostMapping("/login")
	public String logIn(@RequestBody LoginForm loginForm) {
		String status = userService.logIn(loginForm);
		if(status.equals("LOG_IN sucess")) {
			return "redirect:/dashboard?email="+loginForm.getEmail();
		} else {
			return status;
		}
	}
	
	/*write dashboard method for login sucess then we need dahboard data
	 * 1.get user detials based on the email 
	 * 2. create method and return reponseEntity object from  "DashBoardCards"
	 * 3. get dashbpard relaated data using "fetchDasgboardInfo(String email)" as request param
	 * 4. return obj of responce in the form of "DashBoardCards" objecta  send dashbaord data alson with status code
	 * 5. setting  to user data to dashboardcard object for sending response 
	 * 6. sending user data to the dashboard
	 */
	@GetMapping("/dashboard")
	public ResponseEntity<DashBoardCards> buildDashboard(@RequestParam("email") String email){
		UserAccForm user = userService.getUserByEmail(email);
		DashBoardCards dashboardCard  = userService.fetchDasgboardInfo();
		dashboardCard.setUser(user);
		return new ResponseEntity<>(dashboardCard , HttpStatus.OK);
	}
}
