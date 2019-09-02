package com.app.service.graphql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.person.service.Person;
import com.person.service.PersonService;

import graphql.schema.DataFetcher;

@Component
public class PersonGraphQLDataFetchers {
	
	@Autowired
	PersonService personService;
    
    public DataFetcher getPerson() {
    	return dataFetchingEnvironment -> {
    		String personId = (String) dataFetchingEnvironment.getArguments().get("id");
    		String firstName = (String) dataFetchingEnvironment.getArguments().get("firstName");
    		String lastName = (String) dataFetchingEnvironment.getArguments().get("lastName");
    		String placeId = (String) dataFetchingEnvironment.getArguments().get("placeId");
    		return this.personService.getPersons(new Person(personId, firstName, lastName, placeId));
    	};
    }
    
    public DataFetcher getPersonById() {
    	return dataFetchingEnvironment -> {
    		String personId = (String) dataFetchingEnvironment.getArguments().get("id");
    		return this.personService.getPersonById(personId);
    	};
    }
    
}
