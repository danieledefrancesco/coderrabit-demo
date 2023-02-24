package com.tuimm.learningpath.drivers;

import com.tuimm.learningpath.RandomIdGenerator;
import com.tuimm.learningpath.TodayDateProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.function.Consumer;

@Component
@RequiredArgsConstructor
public class DriverFactoryImpl implements DriverFactory {
    private final RandomIdGenerator randomIdGenerator;
    private final TodayDateProvider todayDateProvider;
    @Override
    public Driver create(Consumer<Driver.DriverBuilder> builderDirector) {
        Driver.DriverBuilder builder = Driver.builder();
        builderDirector.accept(builder);
        builder.id(randomIdGenerator.generateRandomId())
                .todayDateProvider(todayDateProvider)
                .reservedSlots(Collections.emptyList());
        return builder.build();
    }
}
