package fr.unice.polytech.doct13.plugin;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

/**
 * A goal to mutate code.
 *
 * @goal mutate
 * @phase mutate-sources
 */
public class CodeGeneratorMojo extends AbstractMojo {
    public void execute() throws MojoExecutionException, MojoFailureException {
        getLog().info("Hello World!");
    }
}