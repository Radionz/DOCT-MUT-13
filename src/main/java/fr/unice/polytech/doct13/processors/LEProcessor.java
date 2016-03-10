package fr.unice.polytech.doct13.processors;

import fr.unice.polytech.doct13.processors.BinaryProcessor;
import spoon.reflect.code.BinaryOperatorKind;

/**
 * MyProcessor for mutating lower or equals into greater than modification
 */
public class LEProcessor extends BinaryProcessor {

    public LEProcessor(){
        super(BinaryOperatorKind.LE,BinaryOperatorKind.GT);
    }
}