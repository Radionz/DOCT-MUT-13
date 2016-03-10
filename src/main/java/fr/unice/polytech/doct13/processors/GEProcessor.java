package fr.unice.polytech.doct13.processors;

import fr.unice.polytech.doct13.processors.BinaryProcessor;
import spoon.reflect.code.BinaryOperatorKind;

/**
 * MyProcessor for mutating greater or equals into lower than modification
 */
public class GEProcessor extends BinaryProcessor {

    public GEProcessor(){
        super(BinaryOperatorKind.GE,BinaryOperatorKind.LT);
    }
}