package fr.unice.polytech.doct13.processors.binary;

import fr.unice.polytech.doct13.processors.BinaryProcessor;
import spoon.reflect.code.BinaryOperatorKind;

/**
 * Processor for mutating plus into minus modification
 */
public class PlusProcessor extends BinaryProcessor {

    public PlusProcessor(){
        super(BinaryOperatorKind.PLUS,BinaryOperatorKind.MINUS);
    }
}