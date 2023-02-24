package com.tuimm.learningpath.common;

import com.tuimm.learningpath.mediator.Mediator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DomainEventsProcessor {
    private final Mediator mediator;
    public void processDomainEvents(Aggregate<?> aggregate) {
        aggregate.getEvents().forEach(mediator::notify);
        aggregate.clearEvents();
    }
}
