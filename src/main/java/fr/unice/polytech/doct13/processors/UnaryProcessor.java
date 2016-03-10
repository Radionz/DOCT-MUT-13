package fr.unice.polytech.doct13.processors;

import com.google.gson.Gson;
import fr.unice.polytech.doct13.utils.Parser;
import fr.unice.polytech.doct13.utils.ProcessorParser;
import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtBinaryOperator;
import spoon.reflect.code.CtUnaryOperator;
import spoon.reflect.code.UnaryOperatorKind;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtMethod;

import java.util.Random;

/**
 * Unary processor superclass for unary operators
 */
public class UnaryProcessor extends AbstractProcessor<CtUnaryOperator> {

    private UnaryOperatorKind target;
    private UnaryOperatorKind mutation;

    private String name;
    private String className;
    private String methodName;
    private static double mutation_probability;
    private static Random random = new Random();

    public UnaryProcessor( UnaryOperatorKind t, UnaryOperatorKind m) {
        super();
        this.name = getClass().getSimpleName();
        target = t;
        mutation = m;
        parseConfig();
    }

    @Override
    public boolean isToBeProcessed(CtUnaryOperator candidate) {
        return true;
    }

    public void process(CtUnaryOperator op) {

        // mutation on the target only
        if (!op.getKind().equals(target))
            return;

        CtMethod parentMethod = op.getParent(CtMethod.class);
        CtClass parentClass = op.getParent(CtClass.class);


        // mutation in the class selected
        if (parentClass==null || !parentClass.getSimpleName().equals(className))
            return;

        // mutation in the method selected
        if (parentMethod==null || !parentMethod.getSimpleName().equals(methodName))
            return;

        // random mutation
        if( random.nextFloat() < mutation_probability ) {
            op.setKind(mutation);
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