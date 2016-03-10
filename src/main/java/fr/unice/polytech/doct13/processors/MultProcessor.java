package fr.unice.polytech.doct13.processors;

import fr.unice.polytech.doct13.processors.BinaryProcessor;
import spoon.reflect.code.BinaryOperatorKind;

/**
 * MyProcessor for multiplication into modulo modification
 */
public class MultProcessor extends BinaryProcessor {

    public MultProcessor(){
        super(BinaryOperatorKind.MUL,BinaryOperatorKind.MOD);
    }
}