package com.bankofapis.web.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.UUID;



public class LoginPerson {
	
	  private UUID id;
	  private final String email;
	  private final String password;
	  private final String accountno;

	  public LoginPerson(
	      @JsonProperty("id") UUID id,
	      @JsonProperty("email") String email,
	      @JsonProperty("password") String password,
	      @JsonProperty("accountno") String accountno) {
	    this.id = id;
	    this.email = email;
	    this.password = password;
	    this.accountno = accountno;
	  }

	  public UUID getId() {
	    return id;
	  }

	  public void setId(UUID id) {
	    this.id = id;
	  }


	  public String getEmail() {
	    return email;
	  }

	  public String getPassword() {
	    return password;
	  }

	  public String getAccountno() {
	    return accountno;
	  }

}
