package fr.unice.polytech.doct13.processors;

import fr.unice.polytech.doct13.processors.BinaryProcessor;
import spoon.reflect.code.BinaryOperatorKind;

/**
 * MyProcessor for mutating divide into multiplication modification
 */
public class DivProcessor extends BinaryProcessor {

    public DivProcessor(){
        super(BinaryOperatorKind.DIV,BinaryOperatorKind.MUL);
    }
}