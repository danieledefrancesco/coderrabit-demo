package com.tuimm.learningpath.drivers.commands;

import com.tuimm.learningpath.drivers.Driver;
import com.tuimm.learningpath.drivers.DriversRepository;
import com.tuimm.learningpath.drivers.DrivingLicense;
import com.tuimm.learningpath.drivers.DrivingLicenseCode;
import com.tuimm.learningpath.mediator.RequestHandler;
import org.springframework.stereotype.Component;

@Component
public class UpdateDriverCommand extends RequestHandler<UpdateDriverRequest, Void> {
    private final DriversRepository driversRepository;
    public UpdateDriverCommand(DriversRepository driversRepository) {
        super(UpdateDriverRequest.class);
        this.driversRepository = driversRepository;
    }

    @Override
    public Void handle(UpdateDriverRequest request) {
        Driver driver = driversRepository.findById(request.getDriverId());
        request.getFirstName().ifPresent(driver::setFirstName);
        request.getLastName().ifPresent(driver::setLastName);
        request.getCitizenship().ifPresent(driver::setCitizenship);
        request.getDateOfBirth().ifPresent(driver::setDateOfBirth);
        request.getDrivingLicense().map(drivingLicenseRequest -> DrivingLicense.builder()
                        .code(DrivingLicenseCode.from(drivingLicenseRequest.getCode()))
                        .expiryDate(drivingLicenseRequest.getExpiryDate())
                        .build())
                .ifPresent(driver::setDrivingLicense);
        driver.save();
        return null;
    }
}
