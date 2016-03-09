package fr.unice.polytech.doct13.processors.unary;

import fr.unice.polytech.doct13.processors.UnaryProcessor;
import spoon.reflect.code.UnaryOperatorKind;

/**
 * MyProcessor for pre-decrement into pre-incrementation modification
 */
public class PreDecProcessor extends UnaryProcessor {

    public PreDecProcessor() {
        super(UnaryOperatorKind.PREDEC,UnaryOperatorKind.PREINC);
    }
}
