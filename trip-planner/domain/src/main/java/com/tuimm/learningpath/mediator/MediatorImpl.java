package com.tuimm.learningpath.mediator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;

@Component
public class MediatorImpl implements Mediator {
    @Lazy
    @Autowired
    private Collection<RequestHandler<?,?>> requestHandlers;
    @Lazy
    @Autowired
    private Collection<EventHandler<?>> eventHandlers;
    @Lazy
    @Autowired
    private Collection<RequestInterceptor<?,?>> requestInterceptors;

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
        return sendInternal((RequestHandler<T1, T2>) candidate, request);
    }

    private <T1 extends Request<T2>, T2> T2 sendInternal(RequestHandler<T1, T2> handler, T1 request) {
        List<RequestInterceptor<T1,T2>> interceptors = requestInterceptors
                .stream()
                .filter(interceptor -> interceptor.getRequestType().equals(request.getClass()))
                .sorted(Comparator.comparing(RequestInterceptor::getOrder))
                .map(interceptor -> (RequestInterceptor<T1, T2>) interceptor)
                .toList();
        return joinHandlers(interceptors, handler).handle(request);
    }

    @Override
    public <T> void notify(T event) {
        eventHandlers.stream()
                .filter(handler -> handler.getEventType().equals(event.getClass()))
                .forEach(handler -> handler.handleInternal(event));
    }

    private static <T1 extends Request<T2>, T2> NextRequestHandler<T1, T2> joinHandlers(List<RequestInterceptor<T1, T2>> interceptors, RequestHandler<T1, T2> handler) {
        NextRequestHandler<T1, T2> last = createNextRequestHandlerFromRequestHandler(handler);
        for (int i = interceptors.size() - 1; i >= 0; i--) {
            last = createNextRequestHandlerFromInterceptor(interceptors.get(i), last);
        }
        return last;
    }

    private static <T1 extends Request<T2>, T2> NextRequestHandler<T1, T2> createNextRequestHandlerFromInterceptor(RequestInterceptor<T1, T2> interceptor, NextRequestHandler<T1, T2> next) {
        return request-> interceptor.intercept(request, next);
    }
    private static <T1 extends Request<T2>, T2> NextRequestHandler<T1, T2> createNextRequestHandlerFromRequestHandler(RequestHandler<T1, T2> handler) {
        return request -> (T2) handler.handleInternal(request);
    }
}
