package com.tuimm.learningpath.mediator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class MediatorImpl implements Mediator {
    @Lazy
    @Autowired
    private Collection<RequestHandler<?,?>> requestHandlers;
    @Lazy
    @Autowired
    private Collection<EventHandler<?>> eventHandlers;

    public MediatorImpl() {

    }

    public MediatorImpl(Collection<RequestHandler<?, ?>> requestHandlers, Collection<EventHandler<?>> eventHandlers) {
        this.requestHandlers = requestHandlers;
        this.eventHandlers = eventHandlers;
    }


    @Override
    public <T1 extends Request<T2>, T2> T2 send(T1 request) {
        RequestHandler<?,?> candidate = requestHandlers.stream()
                .filter(handler -> handler.getRequestType().equals(request.getClass()))
                .findFirst()
                .orElseThrow(() -> {
                    throw new UnsupportedOperationException(String.format("No handler found for request of type %s.",
                            request.getClass().getSimpleName()));
                });
        return (T2) candidate.handleInternal(request);
    }

    @Override
    public <T> void notify(T event) {
        eventHandlers.stream()
                .filter(handler -> handler.getEventType().equals(event.getClass()))
                .forEach(handler -> handler.handleInternal(event));
    }
}
