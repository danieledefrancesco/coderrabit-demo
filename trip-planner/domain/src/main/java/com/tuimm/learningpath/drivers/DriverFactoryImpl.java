package com.tuimm.learningpath.drivers;

import com.tuimm.learningpath.RandomIdGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
@RequiredArgsConstructor
public class DriverFactoryImpl implements DriverFactory {
    private final RandomIdGenerator randomIdGenerator;
    @Override
    public Driver create(Consumer<Driver.DriverBuilder> builderDirector) {
        Driver.DriverBuilder builder = Driver.builder();
        builderDirector.accept(builder);
        builder.id(randomIdGenerator.generateRandomId());
        return builder.build();
    }
}
