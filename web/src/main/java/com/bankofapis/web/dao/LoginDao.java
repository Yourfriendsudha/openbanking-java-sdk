package com.bankofapis.web.dao;

import java.util.List;
import java.util.UUID;

import com.bankofapis.web.model.LoginPerson;

public interface LoginDao {

	
	public int insertNewPerson(String email, LoginPerson loginPerson);

	  LoginPerson selectPersonByEmail(String email);

	  List<LoginPerson> selectAllPersons();

	  public int updatePersonById(String email, LoginPerson loginPerson);

	  int deletePersonById(UUID studentId);
	  
	  
	
}
