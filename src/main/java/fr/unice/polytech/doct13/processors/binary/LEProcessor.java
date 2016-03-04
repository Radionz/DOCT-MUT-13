package fr.unice.polytech.doct13.processors.binary;

import fr.unice.polytech.doct13.processors.BinaryProcessor;
import spoon.reflect.code.BinaryOperatorKind;

/**
 * Processor for mutating lower or equals into greater than modification
 */
public class LEProcessor extends BinaryProcessor {

    public LEProcessor(){
        super(BinaryOperatorKind.LE,BinaryOperatorKind.GT);
    }
}