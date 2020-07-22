package com.bankofapis.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bankofapis.web.model.LoginPerson;
import com.bankofapis.web.model.PersonRequest;
import com.bankofapis.web.service.LoginService;

@RestController
@RequestMapping("/open-banking/v3/cheques")
public class LoginController {

	@Autowired
	private LoginService loginService;
	

	@GetMapping("/allpersons")
	public ResponseEntity<List<LoginPerson>> getPersonels(){
		List<LoginPerson> personels=loginService.getAllPersons();
		return ResponseEntity.ok(personels);
	}
	

	@RequestMapping(method=RequestMethod.POST, value="/personel/email")
	public ResponseEntity<LoginPerson> getPersonelemail(@RequestBody PersonRequest personRequest){
		try {
			LoginPerson personels=loginService.getPersonByEmail(personRequest.getEmail());
			return ResponseEntity.ok(personels);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	
	@RequestMapping(method=RequestMethod.POST ,value="/authenticate")
	public ResponseEntity<Integer> getPersonel(@RequestBody PersonRequest personRequest){
		
		if (personRequest.getEmail().equals(null)||personRequest.getPassword().equals(null)) {
			 return ResponseEntity.ok(1);
		 }
		try {
		    int personels=loginService.authenticate(personRequest.getEmail(),personRequest.getPassword() );
		    return ResponseEntity.ok(personels);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	

	
	
	

}

