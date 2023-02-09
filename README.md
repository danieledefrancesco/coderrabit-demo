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

### Run the app

to run the app run the following

(linux / mac)
```
API_KEY=<your-api-key> docker-compose run trip-planner
```
(powershell)
```
$env:API_KEY="<your-api-key>"
docker-compose run trip-planner
```
you can find instructions on how to get an API Key on  https://openrouteservice.org/

---
Extend the complete BASIC challenge with the following features:

- The trip can have multiple Stages and each one must be done using a specific Vehicle. The user must specify the starting and the destination city for each Stage (assuming the destination of one stage is the starting point of the next one) and,
  using the APIs provided by https://openrouteservice.org/, the system must automatically obtain the actual distance in km for each one.
  - The endpoint https://openrouteservice.org/dev/#/api-docs/geocode/search/get can be used to determine the cities coordinates
  - The endpoint https://openrouteservice.org/dev/#/api-docs/v2/directions/{profile}/get can be used to obtain the actual distance between two coordinates. Note that you have to define a profile as path parameter for this call. Each profile will return a different path and distance (e.g: if you plan to use a bike you will avoid highways). Use `driving-car` for `Car` and `Scooter`, `driving-hgv` for `Pullman` and `cycling-regular` for `Bike`.
- All the people must use the same vehicle for a stage. If in the garage there are more than one compatible vehicles of the same type (e.g: 3 cars), choose one of them randomly
- The system must show the weather forecast for each Stage at the arriving date. Use the API https://weather-forecast.herokuapp.com/random to simply get a random weather condition (no parameters required).

Must-have:
- Use maven
- Dockerize the app
- Use lombok in the model and services (refactor also the already existing code)
- Use CQEngine to query information from collections (refactor also the already existing code)
- Use Mockito to inject mocked services in the tests
- Use lambdas where possible (refactor also the already existing code)

Nice-to-have:
- If the weather is too bad for a vehicle (e.g: Rainy for Bike), show an alert on the relative Stage. The possible returned values for the weather conditions are: `Sunny`, `Partly sunny`, `Partly cloudy`, `Rainy`, `Snowy`, `Cloudy` and `Windy`.
- When the user create a Stage and more than one compatible vehicles are available in the Garage, ask him which one he want to use, specifying the cheaper, the fastest and the least polluting

The project should have a good test coverage that allows people to change pieces of code without being afraid to introduce/reintroduce bugs. Use the Mockito library where possible