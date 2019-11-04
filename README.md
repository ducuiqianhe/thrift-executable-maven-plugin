# Thrift-executable-maven-plugin
Maven plugin for auto downloading binary thrift executable. Needed to be used together with maven-thrift-plugin (https://mvnrepository.com/artifact/org.apache.thrift.tools/maven-thrift-plugin)

Currenly supporting two os versions: windows-x86_64, linux-x86_64

### Sample usage
```xml
<plugins>
    <extensions>
        <extension>
            <groupId>kr.motd.maven</groupId>
            <artifactId>os-maven-plugin</artifactId>
            <version>1.5.0.Final</version>
        </extension>
    </extensions>
    <plugin>
        <groupId>com.github.ducuiqianhe</groupId>
        <artifactId>thrift-executable-plugin</artifactId>
        <version>1.0-SNAPSHOT</version>
        <configuration>
            <outputDirectory>src/main/resources</outputDirectory>
        </configuration>
        <executions>
            <execution>
                <id>1</id>
                <phase>generate-sources</phase>
                <goals>
                    <goal>getThriftExecutable</goal>
                </goals>
            </execution>
            <execution>
                <id>2</id>
                <phase>compile</phase>
                <goals>
                    <goal>cleanUp</goal>
                </goals>
            </execution>
        </executions>
    </plugin>
    <plugin>
        <groupId>org.apache.thrift.tools</groupId>
        <artifactId>maven-thrift-plugin</artifactId>
        <version>0.1.11</version>
        <configuration>
            <thriftExecutable>src/main/resources/thrift-${os.detected.classifier}.exe</thriftExecutable>
            <generator>java</generator>
        </configuration>
        <executions>
            <execution>
                <id>thrift-sources</id>
                <phase>generate-sources</phase>
                <goals>
                    <goal>compile</goal>
                </goals>
            </execution>
        </executions>
    </plugin>
</plugins>
```

### Configurations
1. outputDirectory: output directry of the downloaded thrift executable. thriftExecutable in maven-thrift-plugin needed to be specified to the same path. Default: "src/main/resources"
2. If you do not wish to keep the executable after file generation, please execute "cleanUp" goal in phase "compile".