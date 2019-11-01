package com.github.ducuiqianhe.thrift;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;
import org.apache.commons.io.IOUtils;

import java.io.*;


@Mojo(name="getThriftExecutable")
public class ThriftExeMojo extends AbstractMojo {

    private Log log = getLog();

    @Parameter(defaultValue = "${project}", required = true)
    private MavenProject project;

    @Parameter(defaultValue = "${os.detected.classifier}", required = true)
    private String osVersion;

    @Parameter(defaultValue = "${project.build.directory}", required = true)
    private String buildDirectory;

    // TODO: add custom output directory
//    @Parameter(property="outputDirectory")
//    private String outputDirectory = buildDirectory;

    public void execute() throws MojoExecutionException, MojoFailureException {
        log.info( "Fetching thrift executable version: " + osVersion + ", for artifact " + project.getArtifactId() + ", version " + project.getVersion());
        log.info(buildDirectory);

        // get corresponding executable resource
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("thrift-" + osVersion + ".exe");


        // unzip

        // create output file

        final String depsDescriptor = "thrift-" + osVersion + ".exe";
        File descriptorFile = new File(depsDescriptor);

        if (!descriptorFile.exists()) {
            try {
                descriptorFile.createNewFile();
            } catch (IOException e) {
                log.info("Error encountered: " + e);
            }
        }

        // write to output file
        try {
            final FileOutputStream fos = new FileOutputStream(descriptorFile);
            IOUtils.copy(inputStream,fos);
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
