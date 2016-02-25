package fr.unice.polytech.doct13.processors;

import spoon.processing.AbstractProcessor;
import spoon.reflect.code.BinaryOperatorKind;
import spoon.reflect.code.CtBinaryOperator;
import spoon.reflect.declaration.CtElement;

/**
 * Created by ca309567 on 22/02/16.
 */
public class BinaryOperatorProcessor extends AbstractProcessor<CtElement> {
    @Override
    public boolean isToBeProcessed(CtElement candidate) {
        return candidate instanceof CtBinaryOperator;
    }


    public void process(CtElement candidate) {
        if (!(candidate instanceof CtBinaryOperator)) {
            return;
        }
        CtBinaryOperator op = (CtBinaryOperator)candidate;
        op.setKind(BinaryOperatorKind.MINUS);
    }
}