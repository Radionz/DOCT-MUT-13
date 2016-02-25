package fr.unice.polytech.doct13.processors.binary;

import spoon.reflect.code.BinaryOperatorKind;

/**
 * Processor for mutating greater than into lower or equals modification
 */
public class GTProcessor extends BinaryProcessor {

    public GTProcessor(){
        super(BinaryOperatorKind.GT,BinaryOperatorKind.LE);
    }
}