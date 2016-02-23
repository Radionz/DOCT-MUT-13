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
    /**
     * @parameter default-value="plus"
     * @optional
     */
    private String mutation;

    public void execute() throws MojoExecutionException, MojoFailureException {
        getLog().info("Hello World! \nMutation : " + mutation);
    }
}