package com.bankofapis.web.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.bankofapis.web.dao.LoginDao;
import com.bankofapis.web.model.LoginPerson;

@Service
public class LoginServiceImp  implements LoginService {
	
	
	private LoginDao loginDao;
	
	
	@Autowired
	public void setPersonelRepository(@Qualifier("personelRepository")LoginDao personelRepository) {
		this.loginDao = personelRepository;
	}

		
	 public int persistNewPerson(String email, LoginPerson loginPerson) {
		   
		    return loginDao.insertNewPerson(email, loginPerson);
		  }
     
	 public LoginPerson getPersonByEmail(String email) {
		    return loginDao.selectPersonByEmail(email);
		  }

	 public List<LoginPerson> getAllPersons() {
		    return loginDao.selectAllPersons();
		  }

	 public int updatePersonById(String email, LoginPerson studentUpdate) {
		    return loginDao.updatePersonById(email, studentUpdate);
		  }

	 public int deletePersonById(UUID id) {
		    return loginDao.deletePersonById(id);
		  }

	 public int authenticate(String email, String password) {
		 
		
		 LoginPerson loginPerson ;
		 try {
		       loginPerson = loginDao.selectPersonByEmail(email); }
		 catch (Exception e) {
			 return 1;
		 }
		 
		
		 if( (loginPerson.getEmail().equals(email)) && (loginPerson.getPassword().equals(password))) {
			 return 0; //successful
		 }
		 else {
			 return 1;
		 }	 
	 }

}


