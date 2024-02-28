package com.tuimm.learningpath.mediator;

import lombok.Getter;

@Getter
public abstract class RequestInterceptor<TRequest extends Request<TResponse>, TResponse> {
    private final Class<TRequest> requestType;
    private final int order;

    protected RequestInterceptor(Class<TRequest> requestType, int order) {
        this.requestType = requestType;
        this.order = order;
    }

    public abstract TResponse intercept(TRequest request, NextRequestHandler<TRequest, TResponse> next);

}
