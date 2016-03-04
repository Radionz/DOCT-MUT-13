package fr.unice.polytech.doct13.processors.unary;

import fr.unice.polytech.doct13.processors.UnaryProcessor;
import spoon.reflect.code.UnaryOperatorKind;

/**
 * Processor for post-incrementation into pre-decrement modification
 */
public class PostIncProcessor extends UnaryProcessor {

    public PostIncProcessor() {
        super(UnaryOperatorKind.POSTINC,UnaryOperatorKind.POSTDEC);
    }
}
