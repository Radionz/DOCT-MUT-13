package fr.unice.polytech.doct13.processors;

import fr.unice.polytech.doct13.processors.BinaryProcessor;
import spoon.reflect.code.BinaryOperatorKind;

/**
 * MyProcessor for mutating plus into minus modification
 */
public class PlusProcessor extends BinaryProcessor {

    public PlusProcessor(){
        super(BinaryOperatorKind.PLUS,BinaryOperatorKind.MINUS);
    }
}