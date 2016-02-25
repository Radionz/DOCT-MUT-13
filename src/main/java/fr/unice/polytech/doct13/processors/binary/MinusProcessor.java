package fr.unice.polytech.doct13.processors.binary;

import spoon.reflect.code.BinaryOperatorKind;

/**
 * Processor for mutating minus into plus modification
 */
public class MinusProcessor extends BinaryProcessor {

    public MinusProcessor(){
        super(BinaryOperatorKind.MINUS,BinaryOperatorKind.PLUS);
    }
}