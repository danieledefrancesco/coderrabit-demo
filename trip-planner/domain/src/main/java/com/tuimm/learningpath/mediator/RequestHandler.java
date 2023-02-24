package com.tuimm.learningpath.mediator;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public abstract class RequestHandler<T1 extends Request<T2>, T2> {
    private final Class<T1> requestType;
    Object handleInternal(Request<?> request) {
        if (!request.getClass().equals(requestType)) {
            throw new UnsupportedOperationException();
        }
        return handle((T1) request);
    }
    public abstract T2 handle(T1 request);
}
