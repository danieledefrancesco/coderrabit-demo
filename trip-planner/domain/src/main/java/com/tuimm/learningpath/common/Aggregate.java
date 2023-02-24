package com.tuimm.learningpath.common;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.Collection;
import java.util.LinkedList;

@SuperBuilder
public abstract class Aggregate<T extends Aggregate<T>> {
    private final Collection<Event> events = new LinkedList<>();
    @Getter(AccessLevel.PROTECTED)
    private final AggregateManager<T> aggregateManager;

    public Collection<Event> getEvents() {
        return events.stream().toList();
    }

    public void addEvent(Event event) {
        events.add(event);
    }

    public void clearEvents() {
        events.clear();
    }

    public void save() {
        getAggregateManager().getDomainEventsProcessor().processDomainEvents(this);
        getAggregateManager().save(asT());
    }

    public void delete() {
        getAggregateManager().getDomainEventsProcessor().processDomainEvents(this);
        getAggregateManager().delete(asT());
    }

    private T asT() {
        return (T) this;
    }
}
