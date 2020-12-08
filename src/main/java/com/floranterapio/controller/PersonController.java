package com.floranterapio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.floranterapio.model.Person;
import com.floranterapio.model.repository.PersonRepository;

@RestController
@RequestMapping("/api/v1/persons")
public class PersonController {

	@Autowired
	private PersonRepository personRepository;
	
	@GetMapping
	public List<Person> getPersons() {
		return personRepository.findAll();
	}
	
	@GetMapping("{id}")
	public Person getPerson(@PathVariable long id) {
		return personRepository.findById(id);
	}
	@PostMapping
	public ResponseEntity<Person> createPerson(@RequestBody Person person)
	{
		Person result = personRepository.save(person);
		return ResponseEntity.ok().body(result);
	}
	
	@PutMapping
	public ResponseEntity<Person> updatePerson(@RequestBody Person person)
	{
		if (personRepository.findById(person.getId()) != null) {
			Person result = personRepository.save(person);
			return ResponseEntity.ok().body(result);
		}
		return ResponseEntity.badRequest().body(null);
	}
	
	@DeleteMapping
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Void> deletePerson(@RequestBody Person person)
	{
		if (personRepository.findById(person.getId()) != null) {
			personRepository.delete(person);
		}
		return ResponseEntity.noContent().build();
	}
}
