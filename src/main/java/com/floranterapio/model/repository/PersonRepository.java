package com.floranterapio.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.floranterapio.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {

	Person findById(long id);
}
