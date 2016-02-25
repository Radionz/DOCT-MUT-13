package fr.unice.polytech.doct13.processors.unary;

import spoon.reflect.code.UnaryOperatorKind;

import java.util.function.UnaryOperator;

/**
 * Processor for post-incrementation into pre-decrement modification
 */
public class PostIncProcessor extends UnaryProcessor {

    public PostIncProcessor() {
        super(UnaryOperatorKind.POSTINC,UnaryOperatorKind.POSTDEC);
    }
}
