package it.altran.springmvc.myApp.crypt;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncodeGenerator {
	
	public static void main(String[] args){
		String password = "123456";
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String hashedPassword = encoder.encode(password);
		
		System.out.println("hashedPassword:"+hashedPassword);
	}

}
