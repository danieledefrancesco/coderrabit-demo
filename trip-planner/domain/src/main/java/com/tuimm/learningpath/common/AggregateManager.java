package com.tuimm.learningpath.common;

public interface AggregateManager<T extends Aggregate<T>> {
    void save(T aggregate);
    void delete(T aggregate);
    DomainEventsProcessor getDomainEventsProcessor();
}
