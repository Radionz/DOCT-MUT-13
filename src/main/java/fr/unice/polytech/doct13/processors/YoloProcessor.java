package fr.unice.polytech.doct13.processors;

import com.google.gson.Gson;
import fr.unice.polytech.doct13.utils.Parser;
import fr.unice.polytech.doct13.utils.ProcessorParser;
import spoon.processing.AbstractProcessor;
import spoon.reflect.code.BinaryOperatorKind;
import spoon.reflect.code.CtBinaryOperator;
import spoon.reflect.code.CtUnaryOperator;
import spoon.reflect.code.UnaryOperatorKind;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtElement;
import spoon.reflect.declaration.CtMethod;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Yolo style (mutates each operator into an other operator of the same type)
 */
public class YoloProcessor extends AbstractProcessor<CtElement> {
    private static List<BinaryOperatorKind> binaryOperators = Arrays.asList(BinaryOperatorKind.values());
    private static List<UnaryOperatorKind> unaryOperators = Arrays.asList(UnaryOperatorKind.values());

    private String name;
    private String className;
    private String methodName;
    private static double mutation_probability;
    private static Random random = new Random();

    public YoloProcessor() {
        super();
        this.name = getClass().getSimpleName();
        parseConfig();
    }

    @Override
    public boolean isToBeProcessed(CtElement candidate) {
        if (candidate instanceof CtBinaryOperator || candidate instanceof CtUnaryOperator )
            return true;
        return false;
    }

    public void process(CtElement e) {

        CtMethod parentMethod = e.getParent(CtMethod.class);
        CtClass parentClass = e.getParent(CtClass.class);

        // mutation in the class selected
        if (parentClass==null || !parentClass.getSimpleName().equals(className))
            return;

        // mutation in the method selected
        if (parentMethod==null || !parentMethod.getSimpleName().equals(methodName))
            return;

        if (e instanceof CtBinaryOperator) {
            CtBinaryOperator op = (CtBinaryOperator) e;
            // random mutation
            if( random.nextFloat() < mutation_probability ) {
                if (binaryOperators.contains(op.getKind())) { // should always be true
                    // Replace the operation by another binary operator
                    op.setKind(binaryOperators.get(random.nextInt(binaryOperators.size())));
                }
            }
        }
        if (e instanceof CtUnaryOperator) {
            CtUnaryOperator op = (CtUnaryOperator) e;
            // random mutation
            if( random.nextFloat() < mutation_probability ) {
                if (unaryOperators.contains(op.getKind())) { // should always be true
                    // Replace the operation by another binary operator
                    op.setKind(unaryOperators.get(random.nextInt(unaryOperators.size())));
                }
            }
        }
    }

    private void parseConfig(){
        Gson gson = new Gson();
        Parser parser = new Parser();
        try{
            parser = gson.fromJson(Parser.readFile("mutations.json"), Parser.class);
            ProcessorParser theMutation = parser.getMyProcessor(name);
            className = theMutation.getClassName();
            methodName = theMutation.getMethodName();
            mutation_probability = theMutation.getMutation_probability();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
