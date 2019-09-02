package com.app.service.graphql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.app.service.AppService;
import com.person.service.Person;

import graphql.schema.DataFetcher;

@Component
public class AppGraphQLDataFetchers {

	@Autowired
	AppService appService;
	
	public DataFetcher getPlaceSummaryByPlaceId() {
    	return dataFetchingEnvironment -> {
    		String placeId = (String) dataFetchingEnvironment.getArguments().get("placeId");
    		return this.appService.getPlaceSummary(placeId);
    	};
    }
}
