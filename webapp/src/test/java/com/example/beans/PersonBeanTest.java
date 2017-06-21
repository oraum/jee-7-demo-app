package com.example.beans;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PersonBeanTest {

	private static PersonBean personBean;

	@BeforeClass
	public static void initTest() {
		personBean = new PersonBean();
	}

	@Test
	public void GettersAndSetters() throws Exception {
		personBean.setForename("TestForename");
		assertEquals("TestForename", personBean.getForename());

		personBean.setSurname("TestSurname");
		assertEquals("TestSurname", personBean.getSurname());
	}

}