# Advanced Java Challenge

## Usage

Docker and docker-compose are required to run the app.

### Build

to build the containers run the following:
```
docker-compose build
```

### Test
to run the test run the following:
```
docker-compose run sdk mvn clean test
```
you can visualize the coverage report at `trip-planner/target/site/jacoco/index.html`

### Run the app

to run the app run the following

(linux / mac)
```
API_KEY=<your-api-key> docker-compose up -d
```
(powershell)
```
$env:API_KEY="<your-api-key>"
docker-compose up -d
```
you can find instructions on how to get an API Key on  https://openrouteservice.org/

The application will run on http://localhost:8000

---

# Microservice Challenge

Know we know how create microservices and how use spring security, so we're going to migrate the Spring Challenge to a microservice.

First of all we need to define the openapi specification to be sure that we covered all the operations needed. You can use https://editor.swagger.io/

At least we need the following operations:
- Get all trips
- Get a specific trip by id
- Get all drivers associated to a trip
- Get the vehicle associated to a trip
- Add, Update or Delete a trip
- Add or Delete a driver to a trip
- Get all vehicles
- Get a specific vehicle by id
- Add, Update or Delete a vehicle
- Get all drivers
- Get a specific driver by id
- Add, Update or Delete a driver

We want to include some security in the operations that we exposed in the rest api:
- We'll have two different roles:
    - OPERATOR
    - MANAGER
- **Get** operations are public, you can use it without any token.
- **Update** operations can only be performed by authorized users, **except** update a trip that only can be performed by user with role **MANAGER**
- **Delete** operations can only be performed by users with role **MANAGER**

At least the following sets of features must be covered:
- A trips always need a vehicle and at least on driver
- If the trip has already started, we can't make any changes
- A vehicle can't be assigned to more than one trip at the same time
- A driver can't be assigned to more than one trip at the same time
- We can't make any changes in a vehicle it's associated trip that has already started
- We can create a driver using the operation create a driver or through the trip's operations
- The JWT token must be valid and signed

Must-have:
- Create an openapi spec
- Rest api documented. You can use springdoc-openapi (included by default in the dependencies) or similar.
- Use spring security with a JWT token

The project should have a good test coverage that allows people to change pieces of code without being afraid to introduce/reintroduce bugs. Use the Mockito library where possible