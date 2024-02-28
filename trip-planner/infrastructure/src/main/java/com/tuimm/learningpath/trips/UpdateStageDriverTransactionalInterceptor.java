package com.tuimm.learningpath.trips;

import com.tuimm.learningpath.transactions.AbstractTransactionalInterceptor;
import com.tuimm.learningpath.transactions.TransactionTemplateFactory;
import com.tuimm.learningpath.trips.commands.UpdateStageDriverRequest;
import org.springframework.stereotype.Component;

@Component
public class UpdateStageDriverTransactionalInterceptor extends AbstractTransactionalInterceptor<UpdateStageDriverRequest, Void> {

    protected UpdateStageDriverTransactionalInterceptor(TransactionTemplateFactory transactionManagerFactory) {
        super(UpdateStageDriverRequest.class, 0, transactionManagerFactory);
    }
}
