package org.example.thinking.in.spring.resource;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.util.StreamUtils;

import java.io.*;

/**
 * {@link org.springframework.core.io.FileSystemResource}
 * {@link org.springframework.core.io.support.EncodedResource}
 */
public class EncodedFileSystemSourceDemo {

    public static void main(String[] args) throws IOException {
        String currentJavaFilePath =  System.getProperty("user.dir") + "/thinging-in-spring/resource/src/main/java/org/example/thinking/in/spring/resource/EncodedFileSystemSourceDemo.java";

        File file = new File(currentJavaFilePath);
        //FileSystemSource -> WritableResource -> Resource
        FileSystemResource fileSystemResource = new FileSystemResource(currentJavaFilePath);
        EncodedResource encodedResource = new EncodedResource(fileSystemResource, "UTF-8");
        //字符输入流
        try (Reader reader = encodedResource.getReader()){
            System.out.println(IOUtils.toString(reader));
        }
    }
}
