package fr.unice.polytech.doct13.processors;

import com.google.gson.Gson;
import fr.unice.polytech.doct13.utils.Parser;
import fr.unice.polytech.doct13.utils.ProcessorParser;
import spoon.processing.AbstractProcessor;
import spoon.reflect.code.BinaryOperatorKind;
import spoon.reflect.code.CtBinaryOperator;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtMethod;

import java.util.Random;

/**
 * Binary processor superclass for binary operators
 */
public class BinaryProcessor extends AbstractProcessor<CtBinaryOperator>{

    private BinaryOperatorKind target;
    private BinaryOperatorKind mutation;

    private String name;
    private String className;
    private String methodName;
    private static double mutation_probability = 0.30;
    private static Random random = new Random();

    public BinaryProcessor( BinaryOperatorKind t, BinaryOperatorKind m){
        super();
        this.name = getClass().getSimpleName();
        this.target = t;
        this.mutation = m;
        this.name = name;
        parseConfig();
    }

    @Override
    public boolean isToBeProcessed(CtBinaryOperator candidate) {
        return true;
    }

    public void process(CtBinaryOperator op) {

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
        Parser parser=new Parser();
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