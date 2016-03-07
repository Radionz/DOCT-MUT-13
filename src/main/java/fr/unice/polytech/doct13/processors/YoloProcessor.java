package fr.unice.polytech.doct13.processors;

import spoon.processing.AbstractProcessor;
import spoon.reflect.code.BinaryOperatorKind;
import spoon.reflect.code.CtBinaryOperator;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Mutates randomly some parts of code
 * Yolo style (each binary operator into an other binary operator)
 * Probability : 30%
 */
public class YoloProcessor extends AbstractProcessor<CtBinaryOperator> {
    private static final double MUTATION_PROBABILITY = 0.30;
    private static Random random = new Random();
    private static List<BinaryOperatorKind> binaryOperators = Arrays.asList(BinaryOperatorKind.values());

    @Override
    public void process(CtBinaryOperator op) {
        // Mutate with a certain probability
        if (random.nextFloat() < MUTATION_PROBABILITY) {
            // Yolo mutation (should break everything)
            if (binaryOperators.contains(op.getKind())) { // should always be true
                // Replace the operation by another binary operator
                op.setKind(binaryOperators.get(random.nextInt(binaryOperators.size())));
            }
        }
    }
}
