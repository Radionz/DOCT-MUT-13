package fr.unice.polytech.doct13.processors.binary;

import spoon.reflect.code.BinaryOperatorKind;

/**
 * Processor for mutating lower than or equals into greater or equals than modification
 */
public class LTProcessor extends BinaryProcessor {

    public LTProcessor(){
        super(BinaryOperatorKind.LT,BinaryOperatorKind.GE);
    }
}