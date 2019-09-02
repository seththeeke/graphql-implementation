package com.person.service;

import java.util.List;

public interface PersonService {

	/**
	 * Returns the first person found with the specified id or null
	 * 
	 * @param id {@link String} id
	 * @return {@link Person} person with the id
	 */
	Person getPersonById(final String id);
	
	/**
	 * Returns the list of persons that meet the input person description
	 * @param person {@link Person} describing what to find
	 * @return {@link List} of {@link Person} list of persons meeting the query
	 */
	List<Person> getPersons(Person person);
	
}
