package com.tuimm.learningpath.mediator;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public abstract class EventHandler<T> {
    private final Class<T> eventType;

    void handleInternal(Object event) {
        if (!event.getClass().equals(eventType)) {
            throw new UnsupportedOperationException();
        }
        handle((T) event);
    }

    public abstract void handle(T event);
}
