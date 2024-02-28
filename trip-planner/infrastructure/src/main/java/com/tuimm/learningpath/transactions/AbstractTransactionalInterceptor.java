package com.tuimm.learningpath.transactions;

import com.tuimm.learningpath.mediator.NextRequestHandler;
import com.tuimm.learningpath.mediator.Request;
import com.tuimm.learningpath.mediator.RequestInterceptor;

public abstract class AbstractTransactionalInterceptor<T1 extends Request<T2>, T2> extends RequestInterceptor<T1, T2> {
    private final TransactionTemplateFactory transactionManagerFactory;
    protected AbstractTransactionalInterceptor(Class<T1> requestType, int order, TransactionTemplateFactory transactionManagerFactory) {
        super(requestType, order);
        this.transactionManagerFactory = transactionManagerFactory;
    }

    @Override
    public T2 intercept(T1 request, NextRequestHandler<T1, T2> next) {
        return transactionManagerFactory.createTransactionTemplate().execute(status -> next.handle(request));
    }
}
