package com.place.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

@Component
public class PlaceServiceImpl implements PlaceService{
	
	private static List<Place> places = Arrays.asList(
			new Place("place-1", "Home"),
			new Place("place-2", "Work"),
			new Place("place-3", "Vacation")
		);

	@Override
	public Place getPlaceById(String id) {
		return places.stream()
				.filter(place -> place.getId().equals(id))
				.findFirst()
				.orElse(null);
	}

	@Override
	public List<Place> getPlaces(Place place) {
		return places.stream()
				.filter(placeObj -> checkOptionalStringField(placeObj.getId(), place.getId())
						&& checkOptionalStringField(placeObj.getName(), place.getName()))
				.collect(Collectors.toList());
	}
	
	private boolean checkOptionalStringField(final String dataValue, final String queryValue) {
    	if (queryValue != null) {
    		return queryValue.equalsIgnoreCase(dataValue);
    	}
    	return true;
    }

}
