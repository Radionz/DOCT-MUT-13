package fr.unice.polytech.doct13.processors;

import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtUnaryOperator;
import spoon.reflect.code.UnaryOperatorKind;


/**
 * Created by user on 20/02/2016.
 */
public class UnaryProcessor extends AbstractProcessor<CtUnaryOperator> {


    @Override
    public boolean isToBeProcessed(CtUnaryOperator candidate) {
        return candidate instanceof CtUnaryOperator;
    }

    public void process(CtUnaryOperator ctUnaryOperator) {
        if (!(ctUnaryOperator instanceof CtUnaryOperator)) {
            return;
        }
        CtUnaryOperator op = ctUnaryOperator;
        if (op.getKind() == UnaryOperatorKind.PREDEC)
            op.setKind(UnaryOperatorKind.PREINC);
        if (op.getKind() == UnaryOperatorKind.PREINC)
            op.setKind(UnaryOperatorKind.PREDEC);
    }
}