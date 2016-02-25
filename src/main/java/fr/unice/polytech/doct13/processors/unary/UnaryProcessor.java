package fr.unice.polytech.doct13.processors.unary;

import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtUnaryOperator;
import spoon.reflect.code.UnaryOperatorKind;

/**
 * Unary processor superclass for unary operators
 */
public class UnaryProcessor extends AbstractProcessor<CtUnaryOperator> {

    private UnaryOperatorKind target;
    private UnaryOperatorKind mutation;

    public UnaryProcessor( UnaryOperatorKind t, UnaryOperatorKind m) {
        super();
        target = t;
        mutation = m;
    }

    @Override
    public boolean isToBeProcessed(CtUnaryOperator candidate) {
        return true;
    }

    public void process(CtUnaryOperator op) {
        if (op.getKind() == target)
            op.setKind(mutation);
    }
}