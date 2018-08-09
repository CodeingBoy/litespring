package me.codeingboy.litespring.core.io;

/**
 * Interface for loading {@link Resource}
 *
 * @author CodeingBoy
 * @version 1
 */
public interface ResourceLoader {

    Resource getResource(String path);

    ClassLoader getClassLoader();
}
