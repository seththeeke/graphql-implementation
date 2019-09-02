package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.person.service.Person;
import com.person.service.PersonService;
import com.place.service.Place;
import com.place.service.PlaceService;

@Component
public class AppServiceImpl implements AppService{
	
	@Autowired
	PersonService personService;
	
	@Autowired
	PlaceService placeService;

	@Override
	public PlaceSummary getPlaceSummary(String placeId) {
		Place place = this.placeService.getPlaceById(placeId);
		List<Person> persons = this.personService.getPersons(new Person(null, null, null, placeId));
		return new PlaceSummary(persons, place.getName());
	}

}
