package fr.unice.polytech.doct13.processors.binary;

import fr.unice.polytech.doct13.processors.BinaryProcessor;
import spoon.reflect.code.BinaryOperatorKind;

/**
 * Processor for not equals into equals modification
 */
public class NotEqualsProcessor extends BinaryProcessor {

    public NotEqualsProcessor(){
        super(BinaryOperatorKind.NE,BinaryOperatorKind.EQ);
    }
}