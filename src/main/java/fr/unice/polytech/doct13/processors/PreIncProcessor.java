package fr.unice.polytech.doct13.processors;

import fr.unice.polytech.doct13.processors.UnaryProcessor;
import spoon.reflect.code.UnaryOperatorKind;

/**
 * MyProcessor for pre-incrementation into pre-decrement modification
 */
public class PreIncProcessor extends UnaryProcessor {

    public PreIncProcessor() {
        super(UnaryOperatorKind.PREINC,UnaryOperatorKind.POSTINC);
    }
}
