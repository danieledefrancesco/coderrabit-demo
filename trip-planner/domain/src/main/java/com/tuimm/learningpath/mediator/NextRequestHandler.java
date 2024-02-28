package com.tuimm.learningpath.mediator;

public interface NextRequestHandler<TRequest extends Request<TResponse>, TResponse> {
    TResponse handle(TRequest request);
}
