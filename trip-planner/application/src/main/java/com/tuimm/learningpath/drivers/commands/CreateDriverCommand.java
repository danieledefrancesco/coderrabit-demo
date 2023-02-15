package com.tuimm.learningpath.drivers.commands;

import com.tuimm.learningpath.drivers.*;
import com.tuimm.learningpath.mediator.RequestHandler;

public class CreateDriverCommand extends RequestHandler<CreateDriverRequest, Driver> {
    private final DriversRepository repository;
    private final DriverFactory factory;

    public CreateDriverCommand(DriversRepository repository, DriverFactory factory) {
        super(CreateDriverRequest.class);
        this.repository = repository;
        this.factory = factory;
    }

    @Override
    public Driver handle(CreateDriverRequest request) {
        Driver driver = factory.create(builder ->
                builder.citizenship(Citizenship.from(request.getCitizenship()))
                        .dateOfBirth(request.getDateOfBirth())
                        .firstName(FirstName.from(request.getFirstName()))
                        .lastName(LastName.from(request.getLastName()))
                        .drivingLicense(DrivingLicense.builder()
                                .code(DrivingLicenseCode.from(request.getDrivingLicenseRequest().getCode()))
                                .expiryDate(request.getDrivingLicenseRequest().getExpiryDate())
                                .build()));
        repository.add(driver);
        return driver;
    }
}
