package com.bankofapis.web.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.bankofapis.web.model.LoginPerson;


@Repository("personelRepository")
public class LoginDaoImpl implements LoginDao {

	
	  private final Map<String, LoginPerson> database;
	
	  public LoginDaoImpl() {
		    database = new HashMap<>();
		    mockdata();
		  }
	
	  @Override
	  public int insertNewPerson(String email, LoginPerson loginPerson) {
	    database.put(email, loginPerson);
	    return 1;
	  }
	
	  @Override
	  public LoginPerson selectPersonByEmail(String email) {
	    return database.get(email);
	  }

	  @Override
	  public List<LoginPerson> selectAllPersons() {
	    return new ArrayList<>(database.values());
	  }

	  @Override
	  public int updatePersonById(String email, LoginPerson loginPerson) {
	    database.put(email, loginPerson);
	    return 1;
	  }

	  @Override
	  public int deletePersonById(UUID id) {
	    database.remove(id);
	    return 1;
	  }

	
	  
	  private void mockdata(){
		    UUID id = UUID.randomUUID();
		    database.put(
		        "Sudha@gmail.com",
		        new LoginPerson(id, "Sudha@gmail.com", "Developer", "123456")
		    );
		    id = UUID.randomUUID();
		    database.put(
			        "Naveen@gmail.com",
			        new LoginPerson(id, "Naveen@gmail.com", "Developer", "7891011")
			    );
	  }

}
