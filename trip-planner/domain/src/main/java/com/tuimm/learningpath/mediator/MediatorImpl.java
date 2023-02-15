package com.tuimm.learningpath.mediator;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
@RequiredArgsConstructor
public class MediatorImpl implements Mediator {
    @NonNull
    private final Collection<RequestHandler<?,?>> requestHandlers;


    @Override
    public <T1 extends Request<T2>, T2> T2 send(T1 request) {
        RequestHandler<?,?> candidate = requestHandlers.stream()
                .filter(handler -> handler.getRequestType().equals(request.getClass()))
                .findFirst()
                .orElseThrow(UnsupportedOperationException::new);
        return (T2) candidate.handleInternal(request);
    }
}
