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

    public BinaryProcessor( BinaryOperatorKind t, BinaryOperatorKind m, String name){
        super();
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

        //System.out.println("Helloooooo Je suis "+name+" je veux " + className+" "+methodName + " "+mutation_probability);
        if (!op.getKind().equals(target))
            return;

        CtMethod parentMethod = op.getParent(CtMethod.class);
        CtClass parentClass = op.getParent(CtClass.class);

        //System.out.println("Classe " + parentClass.getSimpleName()+" vs "+ className +" = "+!parentClass.getSimpleName().equals(className));
        if (parentClass==null || !parentClass.getSimpleName().equals(className))
            return;
        //System.out.println("Bonne Classe");
        if (parentMethod==null || !parentMethod.getSimpleName().equals(methodName))
            return;

        //System.out.println("Bonne Methode");

        if( random.nextFloat() < mutation_probability ) {
            //System.out.println("Mutation ! lolilol");
            op.setKind(mutation);
        }
    }

    private void parseConfig(){
        Gson gson = new Gson();
        Parser parser=new Parser(1);
        try{
            parser = gson.fromJson(Parser.readFile("mutations.json"), Parser.class);
            ProcessorParser theMutation = parser.getMyProcessor(name);
            className = theMutation.className;
            methodName = theMutation.getMethodName();
            mutation_probability = theMutation.getMutation_probability();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}