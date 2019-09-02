package com.app.service.graphql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.place.service.Place;
import com.place.service.PlaceService;

import graphql.schema.DataFetcher;

@Component
public class PlaceGraphQLDataFetchers {

	@Autowired
	PlaceService placeService;
    
    public DataFetcher getPlace() {
    	return dataFetchingEnvironment -> {
    		String id = (String) dataFetchingEnvironment.getArguments().get("id");
    		String name = (String) dataFetchingEnvironment.getArguments().get("name");
    		return this.placeService.getPlaces(new Place(id, name));
    	};
    }
    
    public DataFetcher getPlaceById() {
    	return dataFetchingEnvironment -> {
    		String id = (String) dataFetchingEnvironment.getArguments().get("id");
    		return this.placeService.getPlaceById(id);
    	};
    }
}
