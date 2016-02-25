package fr.unice.polytech.doct13.processors.binary;

import spoon.processing.AbstractProcessor;
import spoon.reflect.code.BinaryOperatorKind;
import spoon.reflect.code.CtBinaryOperator;
import spoon.reflect.declaration.CtElement;

/**
 * Processor for mutating equals than into not equals modification
 */
public class EqualsProcessor extends BinaryProcessor {

    public EqualsProcessor() { super(BinaryOperatorKind.EQ,BinaryOperatorKind.NE); }
}