package fr.unice.polytech.doct13.processors.unary;

import fr.unice.polytech.doct13.processors.UnaryProcessor;
import spoon.reflect.code.UnaryOperatorKind;

/**
 * Processor for positive into negative modification
 */
public class PosProcessor extends UnaryProcessor {

    public PosProcessor() {
        super(UnaryOperatorKind.POS,UnaryOperatorKind.NEG);
    }
}
