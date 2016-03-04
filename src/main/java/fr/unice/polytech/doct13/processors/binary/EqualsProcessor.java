package fr.unice.polytech.doct13.processors.binary;

import fr.unice.polytech.doct13.processors.BinaryProcessor;
import spoon.reflect.code.BinaryOperatorKind;

/**
 * Processor for mutating equals than into not equals modification
 */
public class EqualsProcessor extends BinaryProcessor {

    public EqualsProcessor() { super(BinaryOperatorKind.EQ,BinaryOperatorKind.NE); }
}