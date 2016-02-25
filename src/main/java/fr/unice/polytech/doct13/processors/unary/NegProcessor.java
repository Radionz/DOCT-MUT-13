package fr.unice.polytech.doct13.processors.unary;

import spoon.reflect.code.UnaryOperatorKind;

/**
 * Processor for negative into positive modification
 */
public class NegProcessor extends UnaryProcessor {

    public NegProcessor() { super(UnaryOperatorKind.POS,UnaryOperatorKind.NEG); }
}
