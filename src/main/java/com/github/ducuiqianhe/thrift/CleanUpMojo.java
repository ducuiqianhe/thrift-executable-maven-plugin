package com.github.ducuiqianhe.thrift;

import org.apache.commons.io.IOUtils;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;


@Mojo(name="cleanUp")
public class CleanUpMojo extends AbstractMojo {

    private Log log = getLog();

    @Parameter(property="keepExecutable")
    private String keepExecutable;

    @Parameter(defaultValue = "${project}", required = true)
    private MavenProject project;

    @Parameter(defaultValue = "${os.detected.classifier}", required = true)
    private String osVersion;

    @Parameter(defaultValue = "${project.build.directory}", required = true)
    private String buildDirectory;

    @Parameter(property="outputDirectory")
    private String outputDirectory = buildDirectory;

    public void execute() throws MojoExecutionException, MojoFailureException {
        log.info("Start cleaning up process for thrift executable plugin...");

    }
}
