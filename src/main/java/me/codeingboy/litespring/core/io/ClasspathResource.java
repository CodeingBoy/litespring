package me.codeingboy.litespring.core.io;

import me.codeingboy.litespring.utils.ClassUtils;

import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Resources located in classpath
 *
 * @author CodeingBoy
 * @version 1
 * @see Resource
 */
public class ClasspathResource implements Resource {
    private ClassLoader classLoader;
    private String path;

    public ClasspathResource(String path, ClassLoader classLoader) {
        this.path = path;
        if (classLoader == null) {
            classLoader = ClassUtils.getDefaultClassLoader();
        }
        this.classLoader = classLoader;
    }

    public ClasspathResource(String path) {
        this(path, null);
    }

    @Override
    public InputStream getInputStream() throws FileNotFoundException {
        ClassLoader classLoader = ClassUtils.getDefaultClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(path);
        if (inputStream == null) {
            throw new FileNotFoundException();
        }
        return inputStream;
    }

    @Override
    public String getDescription() {
        return this.path;
    }
}
