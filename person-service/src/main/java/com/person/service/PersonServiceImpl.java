package com.person.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class PersonServiceImpl implements PersonService{
	
	private static List<Person> persons = Arrays.asList(
				new Person("person-1", "Joanne", "Rowling", "place-1"),
				new Person("person-2", "Joanne", "Melville", "place-2"),
				new Person("person-3", "Anne", "Melville", "place-2")
			);

	@Override
	public Person getPersonById(String id) {
		return persons.stream()
				.filter(person -> person.getId().equals(id))
				.findFirst()
				.orElse(null);
	}

	@Override
	public List<Person> getPersons(Person person) {
		return persons.stream()
				.filter(personObj -> checkOptionalStringField(personObj.getId(), person.getId())
						&& checkOptionalStringField(personObj.getFirstName(), person.getFirstName())
						&& checkOptionalStringField(personObj.getLastName(), person.getLastName())
						&& checkOptionalStringField(personObj.getPlaceId(), person.getPlaceId()))
				.collect(Collectors.toList());
	}
	
	private boolean checkOptionalStringField(final String dataValue, final String queryValue) {
    	if (queryValue != null) {
    		return queryValue.equalsIgnoreCase(dataValue);
    	}
    	return true;
    }

}
