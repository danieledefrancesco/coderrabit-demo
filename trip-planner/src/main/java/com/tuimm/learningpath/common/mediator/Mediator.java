package com.tuimm.learningpath.common.mediator;

public interface Mediator {
    <T1 extends Request<T2>, T2> T2 send(T1 request);
}
