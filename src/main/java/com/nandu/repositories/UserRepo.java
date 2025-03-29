package com.nandu.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nandu.entities.UserEntity;

public interface UserRepo extends JpaRepository<UserEntity , Integer>{
	
	
	// write custom query for update account status
	@Query("select UserEntity set accStatus=:status where userId=:userId")
	public Integer updateAccStatus(Integer userId , String status);

	// get data based on email
//	public static UserEntity findByEmail(String email) {
//		// TODO Auto-generated method stub
//		return null;
//	}
	
	
	// implement login given USERNAME AND PASSWORD CORRECT OR NOT 
	// return data from userEntity table so we given as the "UserEntity" object

	public UserEntity findEmailAndPwd(String email, String pwd);

//	public UserEntity findByEmail(String email);

	public UserEntity findByEmail(String email);

}
