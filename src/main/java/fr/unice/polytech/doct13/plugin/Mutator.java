package fr.unice.polytech.doct13.plugin;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

import java.io.File;




/**
 * Created by Serpe on 25/02/2016.
 */

@Mojo( name = "yayo")
public class Mutator extends AbstractMojo {

    public Mutator(){

    }

    /**
     * permet la récupération d'informations sur le project qui utilise notre plugin
     * la variable project est remplie automatiquement
     */
    @Parameter(defaultValue = "${project}", required = true, readonly = false)
    private MavenProject project;

    public void execute() throws MojoExecutionException, MojoFailureException {

        System.out.println("#####################  Mutator  #####################################\n");

        String destination = project.getBasedir().toString()+"/target/mutations";
        new File(destination).mkdir();
        cloneFolder(project.getBasedir().toString()+"/src",destination);

        System.out.println("\n#######################################################################\n");
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
