package com.example.beans;

import com.example.PersonDao;
import com.example.model.Person;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(Arquillian.class)
public class PersonDaoTest {
    @Deployment
    public static Archive<?> createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addPackage(Person.class.getPackage())
                .addClass(PersonDao.class)
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }


    @Inject
    PersonDao personDao;

    @Test
    public void testPersist(){
        assertNotNull(personDao);
        Person person = new Person();

        assertEquals(0, personDao.getAll().size());
        personDao.persist(person);
        assertEquals(1, personDao.getAll().size());
        assertEquals(person, personDao.getAll().get(0));
    }

}
