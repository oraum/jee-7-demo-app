package com.example.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.*;

@Data
@Entity
@NamedQuery(name = "Person.getAll", query = "select p from Person p")
public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Setter(AccessLevel.NONE)
	Long id;

	String surname;

	String forename;
}
