package fr.unice.polytech.doct13.processors;

import fr.unice.polytech.doct13.processors.UnaryProcessor;
import spoon.reflect.code.UnaryOperatorKind;

/**
 * MyProcessor for post-decrement into post-incrementation modification
 */
public class PostDecProcessor extends UnaryProcessor {

    public PostDecProcessor() {
        super(UnaryOperatorKind.POSTDEC,UnaryOperatorKind.POSTINC);
    }
}
