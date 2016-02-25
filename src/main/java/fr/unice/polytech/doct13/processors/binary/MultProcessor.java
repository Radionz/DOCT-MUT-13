package fr.unice.polytech.doct13.processors.binary;

import spoon.reflect.code.BinaryOperatorKind;

/**
 * Processor for multiplication into modulo modification
 */
public class MultProcessor extends BinaryProcessor {

    public MultProcessor(){
        super(BinaryOperatorKind.MUL,BinaryOperatorKind.MOD);
    }
}