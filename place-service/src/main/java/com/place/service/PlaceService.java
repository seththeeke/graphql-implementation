package com.place.service;

import java.util.List;

public interface PlaceService {

	public Place getPlaceById(final String id);
	
	public List<Place> getPlaces(final Place place);
	
}
