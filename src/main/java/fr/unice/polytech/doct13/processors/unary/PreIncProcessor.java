package fr.unice.polytech.doct13.processors.unary;

import spoon.reflect.code.UnaryOperatorKind;

/**
 * Processor for pre-incrementation into pre-decrement modification
 */
public class PreIncProcessor extends UnaryProcessor {

    public PreIncProcessor() {
        super(UnaryOperatorKind.PREINC,UnaryOperatorKind.POSTINC);
    }
}
