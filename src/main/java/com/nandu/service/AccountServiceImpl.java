package com.nandu.service;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nandu.binding.UnlockAccForm;
import com.nandu.binding.UserAccForm;
import com.nandu.entities.UserEntity;
import com.nandu.repositories.UserRepo;
import com.nandu.utils.EmailUtiles;


// represent tis calss as a spting bean so use "@Service " annotation
@Service
public class AccountServiceImpl implements AccountService {
	 
	// inject repository layer for creating user account details isnide the database
	@Autowired
	private UserRepo userRepo;
	
	@Autowired  // (
	//inject emial utill class for send email)
	private EmailUtiles emailUtiles;
	
	

	@Override
	public boolean createUserAccount(UserAccForm accForm) {
		
		// create user entity object 
		UserEntity entity = new UserEntity();
		
		//convert form data into binding object because create record inisde the table
		BeanUtils.copyProperties(accForm, entity);
		
		// set random password 
		entity.setPassword(genarateRandomPassword());
		
		// set account status
		entity.setAccStatus("LOCKED"); // by default keep account stattus as locked
		
		// bydefault account switch will be inactive represented with  "Y"
			
		entity.setActiveSw("Y");
		
		// SAVE 
		userRepo.save(entity );
		
		/*after saving data isnide the table then send email to user unlock account
		 * send email based on (body , subject , to);
		 */
		
		String subject = "USER_REGISTRATION";
		String body = readEmailBody("REGISTRATION_EMAIL_BODY.txt" , entity);
		return emailUtiles.sendEmail(subject, body , accForm.getEmail());

	}

	private String genarateRandomPassword() {
		String randomString = RandomStringUtils.randomAlphanumeric(10);
        System.out.println(randomString); // Generates a 10-character random string
        return randomString;
	}
			

	/* fecth all records from db and convert ino binding object and saved into one userCollection object
	 * this method is rsponsible click on view fetch data from table and send back to the UI
	 * 1.get all records from table
	 * 2.creating collection of binding object we should keep inside List
	 * 3. using for each to get one by one obj and keeping into form binding object
	 * 4.keep entityb object into binding object
	 * 5. keep each user data add into the users list object
	 */
	@Override
	public List<UserAccForm> fetchUserAccounts() {

		List<UserEntity> userEntities = userRepo.findAll();

		List<UserAccForm> users = new ArrayList<>();
		
		for(UserEntity userEntity : userEntities) {

			UserAccForm user = new UserAccForm();
			
			BeanUtils.copyProperties(userEntity, user);
			
			users.add(user);
		}
		
		return users; // return one collection and it will contain list of users 
	}

	@Override
	/* 1.edit functionality use a OPtional object for edit mode 
	 * 2.check if user data present or not if present then get value from that  object
	 * 3. create userAccount obj becoz store entity obj into form binding obj
	 * 4. value present then copy properties into binding object
	 * 5. if given id with  record not avilable return nulll value
	 */
	public UserAccForm getUserAccById(Integer accId) {
		
		Optional<UserEntity> optional = userRepo.findById(accId);
		
		if(optional.isEmpty()) {
			UserEntity userEntity = optional.get();
			UserAccForm user = new UserAccForm();
			BeanUtils.copyProperties(userEntity , user);
			return user;
		}
		return null;
	}

	/*  MAIN AIM OF THIS FUNCTION ACTIVATE OR DEEACTIVATE ACCOUNT IF ACCOUNT ACTIVE WE HAVE TO DEACTIVATE
	 * should we write custom query inside the UserRepo Interfcae
	 * 1. what ever status we are getting we have to update status 
	 * 2. whatever status we are getting update status inside the data base
	 * 3. update status based on userId and status 
	 * 4.after updation store in count variable count how many records got effected
	 * 
	 */
	@Override
	public String changeAccountStatus(Integer userId, String status) {
		int count = userRepo.updateAccStatus(userId, status);
		// check if one record gor effected or not (count>0) thne say record updated sucessfully
		if(count>0) {
			return "Status changed";
 		}
		return "status fail to change";
	}

	@Override
	/* 1. unlock user based on thier email
	 * 2. email come from unlockAccform 
	 * 3. get user record based on the email
	 * 4. write function inside entniy class 
	 * 5. get new password from  "unlockAccForm" save into table (new password saved inside the table)
	 * 6. set status "UNLOCKED" (save accStatus is "unlocked " save inside the table )
	 * 7. return account unlocked
	 */
	public String unlockUserAccount(UnlockAccForm unlockAccForm) {
			
		UserEntity entity = userRepo.findByEmail(unlockAccForm.getEmail());
		
		// these 2 lines of code to add new data inside coloumn and update data inside the table
		// get new pass and setting status to unlock save innto the table
		entity.setPassword(unlockAccForm.getNewPwd());
		entity.setAccStatus("UN-LOCKED");
		userRepo.save(entity);
		
		return "account unlocked"; // this is hard coaded now later we should define these inside the properties file 
	} 
	
	
	
	/* THIS IS FOR SEND AN EMIAL WITH SUB AND BDY AND TO
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

	private String readEmailBody(String filename, UserEntity entity) {
		
		StringBuilder sb = new StringBuilder();
		
		try (Stream<String> lines = Files.lines(Paths.get(filename))){
			
			lines.forEach(
						line -> {
							line = line.replace("${FNAME}", entity.getFullName());
							line = line.replace("${TEMP_PWD}", entity.getPassword());
							line = line.replace("${EMAIL}", entity.getEmail());
							sb.append(line);
						}
					);
		}	catch(Exception e) {
				e.printStackTrace();
		}
		
		return sb.toString();
	}
}




