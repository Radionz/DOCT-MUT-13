package fr.unice.polytech.doct13.processors;

import spoon.processing.AbstractProcessor;
import spoon.reflect.code.BinaryOperatorKind;
import spoon.reflect.code.CtBinaryOperator;

/**
 * Binary processor superclass for binary operators
 */
public class BinaryProcessor extends AbstractProcessor<CtBinaryOperator> {

    private BinaryOperatorKind target;
    private BinaryOperatorKind mutation;

    public BinaryProcessor( BinaryOperatorKind t, BinaryOperatorKind m){
        super();
        target = t;
        mutation = m;
    }

    @Override
    public boolean isToBeProcessed(CtBinaryOperator candidate) {
        return true;
    }

    public void process(CtBinaryOperator op) {
        if (op.getKind() == target)
            op.setKind(mutation);
    }
}