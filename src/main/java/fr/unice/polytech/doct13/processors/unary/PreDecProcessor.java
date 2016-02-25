package fr.unice.polytech.doct13.processors.unary;

import spoon.reflect.code.UnaryOperatorKind;

/**
 * Processor for pre-decrement into pre-incrementation modification
 */
public class PreDecProcessor extends UnaryProcessor {

    public PreDecProcessor() {
        super(UnaryOperatorKind.PREDEC,UnaryOperatorKind.PREINC);
    }
}
