package com.dreamaker.service.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dreamaker.dao.index.PersonDao;
import com.dreamaker.domain.person.Person;

@Service
public class PersonServiceImpl implements PersonService{

	@Autowired
	private PersonDao personDao;
	
	public Person getPerson() {
		return personDao.getPerson();
	}
	
}
