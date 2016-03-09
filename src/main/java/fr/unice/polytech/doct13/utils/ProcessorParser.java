package fr.unice.polytech.doct13.utils;

/**
 * Created by Serpe on 09/03/2016.
 */

public class ProcessorParser{

    public String name;
    public String packageName;
    public String className;
    public String methodName;
    public double mutation_probability;

    public ProcessorParser(String name, String packageName, String className, String methodName, double mutation_probability) {
        this.name = name;
        this.packageName = packageName;
        this.className = className;
        this.methodName = methodName;
        this.mutation_probability = mutation_probability;
    }

    public String getName() {
        return name;
    }

    public String getPackageName() {
        return packageName;
    }

    public String getClassName() {
        return className;
    }

    public String getMethodName() {
        return methodName;
    }

    public double getMutation_probability() {
        return mutation_probability;
    }

}