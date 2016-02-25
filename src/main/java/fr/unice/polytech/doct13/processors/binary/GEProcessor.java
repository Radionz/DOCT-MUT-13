package fr.unice.polytech.doct13.processors.binary;

import spoon.reflect.code.BinaryOperatorKind;

/**
 * Processor for mutating greater or equals into lower than modification
 */
public class GEProcessor extends BinaryProcessor {

    public GEProcessor(){
        super(BinaryOperatorKind.GE,BinaryOperatorKind.LT);
    }
}