package com.tuimm.learningpath.common.mediator;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class RequestHandler<T1 extends Request<T2>, T2> {
    private final Class<T1> requestType;
    Class<T1> getRequestType() {
        return requestType;
    }
    Object handleInternal(Request<?> request) {
        if (!request.getClass().equals(requestType)) {
            throw new UnsupportedOperationException();
        }
        return handle((T1) request);
    }
    public abstract T2 handle(T1 request);
}
