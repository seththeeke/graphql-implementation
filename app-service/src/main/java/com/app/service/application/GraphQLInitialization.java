package com.app.service.application;

import static graphql.schema.idl.TypeRuntimeWiring.newTypeWiring;

import java.io.IOException;
import java.net.URL;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.app.service.graphql.AppGraphQLDataFetchers;
import com.app.service.graphql.PersonGraphQLDataFetchers;
import com.app.service.graphql.PlaceGraphQLDataFetchers;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;

@Component
public class GraphQLInitialization {

    @Autowired
    PersonGraphQLDataFetchers personGraphQLDataFetchers;
    
    @Autowired
    PlaceGraphQLDataFetchers placeGraphQLDataFetchers;
    
    @Autowired
    AppGraphQLDataFetchers appGraphQLDataFetchers;

    private GraphQL graphQL;
    
    @Bean
    public GraphQL graphQL() {
        return graphQL;
    }

    @PostConstruct
    public void init() throws IOException {
        URL url = Resources.getResource("schema.graphqls");
        String sdl = Resources.toString(url, Charsets.UTF_8);
        GraphQLSchema graphQLSchema = buildSchema(sdl);
        this.graphQL = GraphQL.newGraphQL(graphQLSchema).build();
    }

    private GraphQLSchema buildSchema(String sdl) {
        TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(sdl);
        RuntimeWiring runtimeWiring = buildWiring();
        SchemaGenerator schemaGenerator = new SchemaGenerator();
        return schemaGenerator.makeExecutableSchema(typeRegistry, runtimeWiring);
    }

    private RuntimeWiring buildWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type(newTypeWiring("Query")
                		.dataFetcher("personById", this.personGraphQLDataFetchers.getPersonById())
                        .dataFetcher("person", this.personGraphQLDataFetchers.getPerson())
                        .dataFetcher("placeById", this.placeGraphQLDataFetchers.getPlaceById())
                        .dataFetcher("place", this.placeGraphQLDataFetchers.getPlace())
                        .dataFetcher("placeSummary", this.appGraphQLDataFetchers.getPlaceSummaryByPlaceId())
                        )
                .type(newTypeWiring("Mutation")
                		.dataFetcher("updatePerson", this.personGraphQLDataFetchers.updatePerson())
                		)
                .build();
    }

}
