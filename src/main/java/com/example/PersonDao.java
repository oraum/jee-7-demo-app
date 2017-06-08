package com.example;

import com.example.model.Person;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class PersonDao {

	@PersistenceContext
	private	EntityManager entityManager;

	@Transactional
	public void persist(Person person) {
		entityManager.persist(person);
	}

	public List<Person> getAll(){
		return entityManager.createNamedQuery("Person.getAll",Person.class).getResultList();
	}

	@Transactional
	public void remove(Person person) {
		final Person mergedPerson = entityManager.merge(person);
		entityManager.remove(mergedPerson);
	}
}
