package com.bankofapis.web.service;

import java.util.List;
import java.util.UUID;

import com.bankofapis.web.model.LoginPerson;

public interface LoginService {

	 public int persistNewPerson(String email, LoginPerson loginPerson);
  
	 public LoginPerson getPersonByEmail(String email);

	 public List<LoginPerson> getAllPersons();

	 public int updatePersonById(String email, LoginPerson studentUpdate);

	 public int deletePersonById(UUID id);
	 
	 public int authenticate(String email, String password);
	
	
	
}