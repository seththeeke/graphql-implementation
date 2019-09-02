package com.app.service;

public interface AppService {

	/**
	 * Returns a summary of all people in a particular place described by the placeId
	 * @param placeId {@link String} id of a place
	 * @return {@link PlaceSummary} a place name and a list of {@link Person} persons
	 */
	PlaceSummary getPlaceSummary(final String placeId);
	
}
