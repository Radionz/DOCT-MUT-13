package fr.unice.polytech.doct13.processors.binary;

import fr.unice.polytech.doct13.processors.BinaryProcessor;
import spoon.reflect.code.BinaryOperatorKind;

/**
 * MyProcessor for mutating minus into plus modification
 */
public class MinusProcessor extends BinaryProcessor {

    public MinusProcessor(){
        super(BinaryOperatorKind.MINUS,BinaryOperatorKind.PLUS, "MinusProcessor");
    }
}