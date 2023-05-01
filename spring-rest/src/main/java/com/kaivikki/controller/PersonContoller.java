package com.kaivikki.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kaivikki.model.Name;
import com.kaivikki.model.PersonV1;
import com.kaivikki.model.PersonV2;

@RestController
public class PersonContoller {

	// http://localhost:8080/api/v1/person
	@GetMapping("/api/v1/person")
	public PersonV1 getPerson() {
		return new PersonV1("Vikram Arora");
	}
	
	// http://localhost:8080/api/v2/person
	@GetMapping("/api/v2/person")
	public PersonV2 getPersonV2() {
		Name name = new Name();
		name.setFirstName("Vikram");
		name.setLastName("Arora");
		return new PersonV2(name);
	}

	// http://localhost:8080/person?version=1
	@GetMapping(path = "person", params = "version=1")
	public PersonV1 getPersonWithVersionInRequestParam() {
		return getPerson();
	}

	// http://localhost:8080/person?version=2
	@GetMapping(path = "person", params = "version=2")
	public PersonV2 getPersonV2WithVersionInRequestParam() {
		return getPersonV2();
	}

	// http://localhost:8080/person
	@GetMapping(path = "person", headers = "version=1")
	public PersonV1 getPersonWithVersionInRequestHeader() {
		return getPerson();
	}

	// http://localhost:8080/person
	@GetMapping(path = "/person", headers = "version=2")
	public PersonV2 getPersonV2WithVersionInHeader() {
		return getPersonV2();
	}
}
