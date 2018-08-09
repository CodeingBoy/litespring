package me.codeingboy.litespring.core.io;

import me.codeingboy.litespring.utils.ClassUtils;

/**
 * Default implementation of {@link ResourceLoader}
 *
 * @author CodeingBoy
 * @version 1
 * @see ResourceLoader
 */
public class DefaultResourceLoader implements ResourceLoader {
    public final static String CLASSPATH_PREFIX = "classpath:";

    private ClassLoader classLoader;

    @Override
    public Resource getResource(String path) {
        if (path.startsWith(CLASSPATH_PREFIX)) {
            path = path.substring(10);
            return getClasspathResource(path);
        }
        return getFileSystemResource(path);
    }

    private ClasspathResource getClasspathResource(String classpathBasedPath) {
        return new ClasspathResource(classpathBasedPath, getClassLoader());
    }

    private FileSystemResource getFileSystemResource(String fileSystemBasedPath) {
        return new FileSystemResource(fileSystemBasedPath);
    }

    @Override
    public ClassLoader getClassLoader() {
        if (classLoader == null) {
            return ClassUtils.getDefaultClassLoader();
        }
        return classLoader;
    }

    public void setClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }
}
