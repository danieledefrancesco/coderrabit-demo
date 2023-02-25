package com.tuimm.learningpath.drivers.commands;

import com.tuimm.learningpath.drivers.*;
import com.tuimm.learningpath.mediator.RequestHandler;
import org.springframework.stereotype.Service;

@Service
public class CreateDriverCommand extends RequestHandler<CreateDriverRequest, Driver> {
    private final DriverFactory factory;

    public CreateDriverCommand(DriverFactory factory) {
        super(CreateDriverRequest.class);
        this.factory = factory;
    }

    @Override
    public Driver handle(CreateDriverRequest request) {
        Driver driver = factory.create(builder ->
                builder.citizenship(Citizenship.from(request.getCitizenship()))
                        .dateOfBirth(request.getDateOfBirth())
                        .firstName(FirstName.from(request.getFirstName()))
                        .lastName(LastName.from(request.getLastName()))
                        .drivingLicense(createDrivingLicense(request.getDrivingLicense())));
        driver.save();
        return driver;
    }

    private static DrivingLicense createDrivingLicense(CreateDrivingLicenseRequest request) {
        if (request == null) {
            return null;
        }
        return DrivingLicense.builder()
                .code(DrivingLicenseCode.from(request.getCode()))
                .expiryDate(request.getExpiryDate())
                .build();
    }
}
