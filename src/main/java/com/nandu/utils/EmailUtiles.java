package com.nandu.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.internet.MimeMessage;


// USED TO SEND EMAIL FROM OUR APPLICATIONS
@Component
public class EmailUtiles {
	
	@Autowired
	// to send email we can use one java class that is javaMAilSender
	private JavaMailSender mailSender;
	
	//	-> send emails to the application we are using emial utill class
	public boolean sendEmail(String subject , String body , String to) {
		
//		-> here we need build logic to send emial
		boolean isSent = false;
		try {
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(body, true);
			mailSender.send(mimeMessage);
			isSent = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isSent;
	}

}

