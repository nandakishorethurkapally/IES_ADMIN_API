package com.nandu.service;

import java.nio.file.Files;

import java.nio.file.Paths;
import java.util.List;
//import java.util.stream.Stream;
import java.util.stream.Stream;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nandu.binding.DashBoardCards;
import com.nandu.binding.LoginForm;
import com.nandu.binding.UserAccForm;
import com.nandu.constants.AppConstants;
import com.nandu.entities.EligEntity;
import com.nandu.entities.UserEntity;
import com.nandu.repositories.EligRepo;
import com.nandu.repositories.PlanRepo;
import com.nandu.repositories.UserRepo;
import com.nandu.utils.EmailUtiles;

// represent this class as a a spring bean use @Serviceannotation
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;
	
	// send email we should need EmailUtils class is avilabe
	@Autowired
	private EmailUtiles emailUtils;
	
	@Autowired
	// using this for getting plans from plans tablee to display on the dashboatd 
	private PlanRepo planRepo;
	
	@Autowired
	private EligRepo eligRepo;
	
	

	/*
	 * 1. write function in "userRepo" check given email and password correct or not
	 * 2. store that into entity 3. if entity is null retunr invalid creedentials
	 * 4.check account status should be  is unloacked and accSwitch should be  "Y" then onky retunr sucess then only allowing user to login 
	 * 5. else accountStatus is locked
	 * 
	 */

	@Override
	public String logIn(LoginForm loginForm) {

		UserEntity entity = userRepo.findEmailAndPwd(loginForm.getEmail(), loginForm.getPwd());

		if (entity == null) {
			
			return AppConstants.INVALID_CRED;
			 
		}

		if (AppConstants.Y_STR.equals(entity.getActiveSw()) && AppConstants.UNLOCKED .equals(entity.getAccStatus())) {
			
			// here we are getting roleId for who loggedin admin or case worker
			
			//return "LOG_IN sucess@RoleId : " + entity.getRoleId() ;
			
			return AppConstants.SUCCESS;
			
		} else {
			
			return AppConstants.ACC_LOCKED;
			
		}
	}

	@Override
	/* main aim :given email we should fetch data from table and i table we have password so we fetch password send that password to user in the form of email
	 * send true or false true : email send sucess and false : email send failed 
	 *  1.retrive record based on email id 
	 * 2.check it that email existing record we have in database or not 
	 * 3. it will null return false valuee 
	 * 4. in else we should recovary password from data base and u should send that password to user in body 
	 *  u have to send that recovery password 
	 * -> we should pass 3 paramters email and fullName along with body content should be present side the text file
	 * 5. body variable it should get that file with txt extention and fullname and password and email 
	 *  6. send subject and body these are are comming from properties file
	 *  7. send email using EmailUtils class 
	 *  8. paramaters what is subject , body and what is user email
	 *  9. return what im getting from the function
	 */
	public boolean recovaryPassword(String email) {
		UserEntity userEntity = userRepo.findByEmail(email);
		
		if(null == userEntity) {
				return false;
		}	else {
			
			//"RECOVER PASSWORD"
			String subject = AppConstants.RECOVER_SUB;
			String body = readEmailBody(AppConstants.PWD_BODY_FILE ,userEntity);
			return emailUtils.sendEmail(subject, body, email);	
		}
	}

	@Override
	/* main aim : card will represent take data that data we need to send for dashboard disply 
	 * 1. get eligiblityList from Eligiblity table
	 * 2.should i filtered plan approved status count 
	 * 3.get deniied paln count 
	 * -> get befinit ammount alsi using streams
	 * 4. inject planRepo for getting planes from plans table
	 * 5. table we should get the count how many planes are avaleble inside the plans tablw
	 * 6. fetch how many planes avilabel inside the plan table
	 * 7. set that planCount to Dashboard object 
	 * 8. as of now we dont have private Long approvedCnt , DeniedCnt , benifitAmtGiven setting this manually  
	 * 9. once set is done we return card object
	 */
	public DashBoardCards fetchDasgboardInfo() {
		
		long plansCount = planRepo.count();
		
		// in this list we can get how many approved count , denied count and benifitAmount given
		List<EligEntity> eligList = eligRepo.findAll();
		
		Long approvedCount = eligList.stream().filter(	
								ed -> ed.getPlanStatus()
								.equals(AppConstants.AP))
								.count();
		Long deniedCount = eligList.stream()
											.filter(
											ed -> ed.getPlanStatus()
											.equals(AppConstants.DN))
											.count();
		
		// we are getting sum of benifit amount directly using steams
		Double totalBenifitAmnt = eligList.stream().mapToDouble(ed -> ed.getBeniFitAmt()).sum();
		
		DashBoardCards card = new DashBoardCards();
		card.setPlansCnt(plansCount);
		card.setApprovedCnt(approvedCount);
		card.setDeniedCnt(deniedCount);
		card.setBenifitAmtGiven(totalBenifitAmnt);
		return card;
	}

	@Override
	public UserAccForm getUserByEmail(String emial) {
		
		UserEntity userEntity = userRepo.findByEmail(emial);
		
		// copy this obj into UserAccForm object
		UserAccForm user = new UserAccForm();
		BeanUtils.copyProperties(userEntity, user);
		return user;
	}
	
	/*TO SEND EMAIL WE NEED READ DATA FROM EMAIL BODY (read email by body)
	 * Reads a file line by line and Replaces placeholders with user data and 
		Returns the final email content.
	 * 1. create function name with private only it should acess with in the same calss
	 * 2. give me two paramters 1 is give  file name and 2nd one UserEntity objct we should get data from user obj only
	 * 3.  create StringBuilder object to store final content
	 * 4. get file name along with each line from the file use File object
	 * 5. use forEACH FOR GETTING EACH LINE OF CONTENT FROM THE EMAIL BODY
	 * 6.reading data from each line and replacinng to that varible
	 * 7. storing that all lines of code inside stringBuilder
	 * 8. file path is not exist then display error 
	 * 9. return string Builder as a toString()
	 */
		
	private String readEmailBody(String filename , UserEntity user) {
		
		StringBuilder sb = new StringBuilder();
		
		try(Stream<String> lines = Files.lines(Paths.get(filename))){
			
			lines.forEach(line -> {
				// AppConstants.FNAME : get constant litttrals
				line = line.replace(AppConstants.FNAME, user.getFullName());
				line = line.replace(AppConstants.PASSWORD, user.getPassword()); 
				line = line.replace(AppConstants.EMAIL, user.getEmail());
				sb.append(line);
			});
		}
		catch(Exception e) {
			e.printStackTrace();
		} 

		return sb.toString();
	}
}