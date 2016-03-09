package fr.unice.polytech.doct13.processors.binary;

import fr.unice.polytech.doct13.processors.BinaryProcessor;
import spoon.reflect.code.BinaryOperatorKind;

/**
 * MyProcessor for mutating greater than into lower or equals modification
 */
public class GTProcessor extends BinaryProcessor {

    public GTProcessor(){
        super(BinaryOperatorKind.GT,BinaryOperatorKind.LE, "GTProcessor");
    }
}