package me.codeingboy.litespring.core.io;

import java.io.InputStream;

/**
 * Resources located in classpath
 *
 * @author CodeingBoy
 * @version 1
 * @see Resource
 */
public class ClasspathResource implements Resource {
    public ClasspathResource(String path) {
    }

    @Override
    public InputStream getInputStream() {
        return null;
    }

    @Override
    public String getDescription() {
        return null;
    }
}
