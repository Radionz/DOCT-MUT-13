package fr.unice.polytech.doct13.plugin;

import fr.unice.polytech.doct13.processors.BinaryProcessor;
import fr.unice.polytech.doct13.processors.binary.EqualsProcessor;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import spoon.Launcher;
import spoon.reflect.code.CtBinaryOperator;
import spoon.reflect.code.CtExpression;
import spoon.reflect.code.CtStatement;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtElement;
import spoon.reflect.visitor.Filter;
import spoon.reflect.visitor.filter.TypeFilter;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



/**
 * A goal to mutate code.
 *
 * @goal mutator
 * @phase mutation
 */
public class Mutator extends AbstractMojo {

    // class path with name
    private final static String FILE_PATH = System.getProperty("user.dir").replace("\\",File.separator) +
            File.separator +
            Mutator.class.getCanonicalName().replace(".",File.separator) + ".java";


    /**
     * permet la r�cup�ration d'informations sur le project qui utilise notre plugin
     * la variable project est remplie automatiquement
     */
    @Parameter(defaultValue = "${project}", required = true, readonly = false)
    private MavenProject project;

    public void execute() throws MojoExecutionException, MojoFailureException {

        getLog().info("\n###########CLASSPATH###########\n Mutator.java classpath : \n" +
                project.getBasedir().toString() +
                "\n###########CLASSPATH###########\n");

        System.out.println("#####################  Mutator  #####################################\n");

        String destination = project.getBasedir().toString()+"/target/mutations";
        new File(destination).mkdir();
        cloneFolder(project.getBasedir().toString()+"/src",destination);

        System.out.println("\n#######################################################################\n");

        /**Spoon**/
        Launcher l = new Launcher();
        l.addInputResource(project.getBasedir().toString()+"/src/main/java/fr/unice/polytech/plugin/Mutator.java");
        //l.addInputResource("C:/Users/Serpe/Documents/Polytech/DevOps/DOCT-MUT-13/src/main/java/fr/unice/polytech/doct13/plugin/Mutator.java");
        System.out.println();
        l.buildModel();
        CtClass origClass = (CtClass) l.getFactory().Package().getRootPackage()
                .getElements(new TypeFilter(CtClass.class)).get(0);


        final BinaryProcessor mutator = new EqualsProcessor(); //on cree le binary mutator

        List<CtBinaryOperator> elementsToBeMutated = origClass.getElements(new Filter<CtBinaryOperator>() {
            public boolean matches(CtBinaryOperator arg0) {
                return mutator.isToBeProcessed(arg0);
            }
        });

        for (CtBinaryOperator e : elementsToBeMutated) {
            // cloning the AST element
            CtBinaryOperator op = l.getFactory().Core().clone(e);

            // mutate the element
            mutator.process(op);

            // temporarily replacing the original AST node with the mutated element
            replace(e,op);

            // creating a new class containing the mutating code
            CtClass klass = l.getFactory().Core()
                    .clone(op.getParent(CtClass.class));
            // setting the package
            klass.setParent(origClass.getParent());

            String pathMutant = project.getBasedir().toString()+"/src/main/java/fr/unice/polytech/plugin/Mutator.java";
            //String pathMutant ="C:/Users/Serpe/Documents/Polytech/DevOps/DOCT-MUT-13/src/main/java/fr/unice/polytech/doct13/plugin/Mutator.java";
            new File(pathMutant);
            try {
                PrintWriter writer = new PrintWriter(pathMutant,"UTF-8");
                writer.println("package "+klass.getPackage().toString()+";");
                writer.println(klass.toString());
                writer.close();
            }
            catch (FileNotFoundException e1) {e1.printStackTrace();}
            catch (UnsupportedEncodingException e1) {e1.printStackTrace();}

            /**Compilation phase**/
            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            List<String> optionList = new ArrayList<String>();

            StandardJavaFileManager fm = compiler.getStandardFileManager(null,null,null);
            File f = new File(pathMutant);//project.getBasedir().toString()+"/src/main/java/com/mycompany/app/App.java"
            Iterable<? extends JavaFileObject> compUnits = fm.getJavaFileObjects(f);
            optionList.addAll(Arrays.asList("-classpath", System.getProperty("java.class.path")));
            optionList.addAll(Arrays.asList("-d", project.getBasedir().toString() + "/target/mutations/main/java"));

            JavaCompiler.CompilationTask task = compiler.getTask(null,fm,null,optionList,null,compUnits);
            task.call();
            /**End Of Compilation phase**/

            /**TESTS PHASE**/

            File file = new File(project.getBasedir().toString()+"/target/test-classes");
            System.out.println(project.getBasedir().toString()+"/target/test-classes");
            try {
                ClassLoader  cl = new URLClassLoader(new URL[]{file.toURL()}, Thread.currentThread().getContextClassLoader());
                Class cls = cl.loadClass("com.mycompany.app.AppTest");

                System.out.println("####################");
                System.out.println("####################");
                System.out.println("#####MES TESTS######");
                System.out.println("####################");
                System.out.println("####################");
                JUnitCore junit = new JUnitCore();
                Result result = junit.runClasses(cls);

                result.wasSuccessful();
                System.out.println(result.wasSuccessful());
                for (Failure failure : result.getFailures()) {
                    System.out.println(failure.toString());
                }


                System.out.println("###########################");
                System.out.println("######END OF MES TESTS#####");
                System.out.println("###########################");
            } catch (MalformedURLException e1) {e1.printStackTrace();}
            catch (ClassNotFoundException e1) {e1.printStackTrace();}
            /**End Of TESTS PHASE**/
        }

        //  buildHelper(); //Change le sourceCodeDirectory.

    }

    private void replace(CtElement e, CtElement op) {
        if (e instanceof CtStatement && op instanceof CtStatement) {
            ((CtStatement)e).replace((CtStatement) op);
            return;
        }
        if (e instanceof CtExpression && op instanceof CtExpression) {
            ((CtExpression)e).replace((CtExpression) op);
            return;
        }
        throw new IllegalArgumentException(e.getClass()+" "+op.getClass());
    }

    private  void buildHelper() {
        //  project.getCompileSourceRoots().remove(0);
        //project.addCompileSourceRoot(project.getBasedir().toString()+"/target/mutations/main/java");
    }

    /**
     * @param source
     * @param target
     */
    public static void cloneFolder(String source, String target) {
        File targetFile = new File(target);
        if (!targetFile.exists()) {
            targetFile.mkdir();
        }
        for (File f : new File(source).listFiles()) {
            if (f.isDirectory()) {
                String append = "/" + f.getName();
                System.out.println("Creating '" + target + append + "': "
                        + new File(target + append).mkdir());
                cloneFolder(source + append, target + append);
            }
        }
    }
}
