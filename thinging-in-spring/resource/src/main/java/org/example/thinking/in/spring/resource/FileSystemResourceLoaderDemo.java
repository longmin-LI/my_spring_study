package org.example.thinking.in.spring.resource;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;

import java.io.File;
import java.io.IOException;
import java.io.Reader;

/**
 * @see org.springframework.core.io.FileSystemResourceLoader
 * @see org.springframework.core.io.support.EncodedResource
 * @author lilongmin
 */
public class FileSystemResourceLoaderDemo {

    public static void main(String[] args) throws IOException {
        String currentJavaFilePath = "/" + System.getProperty("user.dir") + "/thinging-in-spring/resource/src/main/java/org/example/thinking/in/spring/resource/EncodedFileSystemSourceDemo.java";

        FileSystemResourceLoader loader = new FileSystemResourceLoader();

        Resource resource = loader.getResource(currentJavaFilePath);
        EncodedResource encodedResource = new EncodedResource(resource,"UTF-8");
        //字符输入流
        try (Reader reader = encodedResource.getReader()){
            System.out.println(IOUtils.toString(reader));
        }
    }
}
