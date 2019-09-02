package com.person.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

@Component
public class PersonServiceImpl implements PersonService{
	
	private static Map<String, Person> persons = new HashMap<String, Person>() {{
	    put("person-1", new Person("person-1", "Joanne", "Rowling", "place-1"));
	    put("person-2", new Person("person-2", "Joanne", "Melville", "place-2"));
	    put("person-3", new Person("person-3", "Anne", "Melville", "place-2"));
	}};
	
	@Override
	public Person getPersonById(String id) {
		return persons.get(id);
	}

	@Override
	public List<Person> getPersons(Person person) {
		return persons.entrySet()
				.stream()
				.filter(personEntry -> checkOptionalStringField(personEntry.getValue().getId(), person.getId())
						&& checkOptionalStringField(personEntry.getValue().getFirstName(), person.getFirstName())
						&& checkOptionalStringField(personEntry.getValue().getLastName(), person.getLastName())
						&& checkOptionalStringField(personEntry.getValue().getPlaceId(), person.getPlaceId()))
				.map(Entry::getValue)
				.collect(Collectors.toList());
	}
	
	@Override
	public Person updatePerson(Person person) {
		if (persons.containsKey(person.getId())) {
			persons.put(person.getId(), person);
			return persons.get(person.getId());
		}
		throw new RuntimeException("Person with id " + person.getId() + " does not exist");
	}
	
	private boolean checkOptionalStringField(final String dataValue, final String queryValue) {
    	if (queryValue != null) {
    		return queryValue.equalsIgnoreCase(dataValue);
    	}
    	return true;
    }

}
