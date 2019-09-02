# GraphQL Implementation

This project is composed of 3 "services", app-service, person-service, and place-service. The app-service is a SpringBootApplication while the person-service and place-service have only java api implementations

# Setup

Clone this project and build using the gradle wrapper

```
$ ./gradlew clean eclipse build
```

Open the project in a new eclipse workspace, you can find the SpringBootApplication at app-service/src/main/java/com/app/service/application/AppServiceApplication.java

You can start the service by running the main

# Demo

Download the GraphQL Playground application and open the app and use the following:

The schema can be found at http://localhost:8080/app-service/graphql and you can see the schema definition in app-service/src/main/resources/schema.graphqls

Sample GraphQL Queries:
#### Get person by id

```
{
  person(id: "person-1"){
    firstName
    lastName
    placeId
  }
}
```

#### Get place by id

```
{
  place(id: "place-1"){
    name
  }
}
```

You can also get persons and places by filtering on any property

```
{
  person(firstName: "Joanne"){
    id
    lastName
  }
}
```

#### Get Place Summary by PlaceId

There is a single "aggregate" query that will reach into both person and place services and generate the aggregate object

```
{
  placeSummary(placeId: "place-2"){
    placeName
    persons {
      firstName
      lastName
    }
  }
}
```
