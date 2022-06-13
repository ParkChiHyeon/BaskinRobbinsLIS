package com.br.lis.model.member.service;

import java.util.HashMap;

import javax.mail.Address;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

	@Service("emailService")
	public class EmailService{
		
		@Autowired
		private JavaMailSender mailSender;
		
		public boolean sendMail(String email,String numStr) throws Exception{
			try {
				MimeMessage msg = mailSender.createMimeMessage();
				InternetAddress addr = new InternetAddress("clgusv_test@naver.com");
				
				msg.setFrom(addr);
				msg.setSubject("인증 메일입니다");
				msg.setText(numStr);
//				msg.setRecipient(RecipientType.TO, email);
				
				msg.addRecipient(RecipientType.TO, new InternetAddress(email));
				 
				
				mailSender.send(msg);
				return true;
			}catch(Exception ex) {
				ex.printStackTrace();
			}
			return false;
		}

		
	}

		
	




