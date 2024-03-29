package com.tuimm.learningpath.mediator;

public interface Mediator {
    <T1 extends Request<T2>, T2> T2 send(T1 request);
    <T> void notify(T event);
}
