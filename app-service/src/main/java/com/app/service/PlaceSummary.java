package com.app.service;

import java.util.List;

import com.person.service.Person;

public class PlaceSummary {

	List<Person> persons;
	String placeName;
	
	public PlaceSummary(List<Person> persons, String placeName) {
		super();
		this.persons = persons;
		this.placeName = placeName;
	}

	public PlaceSummary() {}

	public List<Person> getPersons() {
		return persons;
	}

	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}

	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((persons == null) ? 0 : persons.hashCode());
		result = prime * result + ((placeName == null) ? 0 : placeName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PlaceSummary other = (PlaceSummary) obj;
		if (persons == null) {
			if (other.persons != null)
				return false;
		} else if (!persons.equals(other.persons))
			return false;
		if (placeName == null) {
			if (other.placeName != null)
				return false;
		} else if (!placeName.equals(other.placeName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PlaceSummary [persons=" + persons + ", placeName=" + placeName + "]";
	}
	
	
	
}
