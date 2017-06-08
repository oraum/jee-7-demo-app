package com.example.beans;

import com.example.PersonDao;
import com.example.model.Person;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.logging.Logger;

@ViewScoped
@ManagedBean
public class PersonBean {

	private final Logger logger = Logger.getLogger("PersonBean");

	@Inject
	PersonDao personDao;

	@Getter
	@Setter
	private String forename;
	@Getter
	@Setter
	private String surname;

	@Getter
	private List<Person> people;

	public void persistPerson() {
		Person person = new Person();
		person.setForename(forename);
		person.setSurname(surname);

		personDao.persist(person);
		logger.info(String.format("persisted Person: %s", person));
		people.add(person);
	}

	@PostConstruct
	public void init() {
		people = personDao.getAll();
	}

	public void remove(Person person) {
		personDao.remove(person);
		people.remove(person);
	}
}
