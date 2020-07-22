package com.bankofapis.web.model;


import com.fasterxml.jackson.annotation.JsonProperty;



public class PersonRequest {
	
	  
	  private final String email;
	  private final String password;
	 

	  public PersonRequest(
	   
	      @JsonProperty("email") String email,
	      @JsonProperty("password") String password) {
	   
	    
	    this.email = email;
	    this.password = password;
	    
	  }
	  
	  public String getEmail() {
		    return email;
		  }

		  public String getPassword() {
		    return password;
		  }

}