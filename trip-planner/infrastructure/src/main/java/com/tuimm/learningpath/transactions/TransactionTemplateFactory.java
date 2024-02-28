package com.tuimm.learningpath.transactions;


import org.springframework.transaction.support.TransactionTemplate;

public interface TransactionTemplateFactory {
    TransactionTemplate createTransactionTemplate();
}
