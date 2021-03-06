package fr.unice.polytech.doct13.processors;

import fr.unice.polytech.doct13.processors.UnaryProcessor;
import spoon.reflect.code.UnaryOperatorKind;

/**
 * MyProcessor for positive into negative modification
 */
public class PosProcessor extends UnaryProcessor {

    public PosProcessor() {
        super(UnaryOperatorKind.POS,UnaryOperatorKind.NEG);
    }
}
