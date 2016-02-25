package fr.unice.polytech.doct13.processors.unary;

import spoon.reflect.code.UnaryOperatorKind;

/**
 * Processor for post-decrement into post-incrementation modification
 */
public class PostDecProcessor extends UnaryProcessor {

    public PostDecProcessor() {
        super(UnaryOperatorKind.POSTDEC,UnaryOperatorKind.POSTINC);
    }
}
