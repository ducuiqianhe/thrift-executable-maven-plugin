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
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;


@Mojo(name="getThriftExecutable")
public class ThriftExeMojo extends AbstractMojo {

    private Log log = getLog();

    @Parameter(defaultValue = "${project}", required = true)
    private MavenProject project;

    @Parameter(defaultValue = "${os.detected.classifier}", required = true)
    private String osVersion;

    @Parameter(property="outputDirectory")
    private String outputDirectory;

    public void execute() {
        log.info( "Fetching thrift executable version: " + osVersion + ", for artifact " + project.getArtifactId() + ", version " + project.getVersion());

        // create output file
        final String depsDescriptor = outputDirectory == null ? "src/main/resources/thrift-" + osVersion + ".exe" : outputDirectory + "/thrift-" + osVersion + ".exe";
        log.info(depsDescriptor);
        File descriptorFile = new File(depsDescriptor);

        if (!descriptorFile.exists()) {
            try {
                descriptorFile.createNewFile();
            } catch (IOException e) {
                log.info("Error encountered: " + e);
            }
        } else {
            return;
        }

        // get corresponding executable resource
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("thrift-" + osVersion + ".zip");
        InputStream thriftStream = null;
        try {
            File file = getFileFromInputStream(inputStream);
            ZipFile zipFile = new ZipFile(file);
            ZipEntry zipEntry = zipFile.getEntry("thrift-" + osVersion + ".exe");
            thriftStream = zipFile.getInputStream(zipEntry);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // write to output file
        try {
            final FileOutputStream fos = new FileOutputStream(descriptorFile);
            if (thriftStream != null) {
                IOUtils.copy(thriftStream,fos);
            }
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private File getFileFromInputStream(InputStream inputStream) throws IOException {
        File file = File.createTempFile("artifact-", ".tmp");
        OutputStream jarOutStream = new FileOutputStream(file);
        IOUtils.copy(inputStream, jarOutStream);
        file.deleteOnExit();
        return file;
    }
}
