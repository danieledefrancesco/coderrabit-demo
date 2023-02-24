package com.tuimm.learningpath.drivers;

import java.util.function.Consumer;

public interface DriverFactory {
    Driver create(Consumer<Driver.DriverBuilder<?,?>> builderDirector);
}
