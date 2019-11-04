package com.github.ducuiqianhe.thrift;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


@Mojo(name="cleanUp")
public class CleanUpMojo extends AbstractMojo {

    private Log log = getLog();

    @Parameter(defaultValue = "${os.detected.classifier}", required = true)
    private String osVersion;

    @Parameter(property="outputDirectory")
    private String outputDirectory;

    public void execute() {
        log.info("Start cleaning up process for thrift executable plugin...");

        final String depsDescriptor = outputDirectory == null ? "src/main/resources/thrift-" + osVersion + ".exe" : outputDirectory + "/thrift-" + osVersion + ".exe";
        try {
            Files.deleteIfExists(Paths.get(depsDescriptor));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
