package com.tuimm.learningpath.drivers.queries;

import com.tuimm.learningpath.drivers.Driver;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Getter
@Setter
public class GetAllDriversResponse {
    private Collection<Driver> drivers;
}
