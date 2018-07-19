package me.codeingboy.litespring.core.io;

import java.io.InputStream;

/**
 * A resource located in file system
 *
 * @author CodeingBoy
 * @version 1
 * @see Resource
 */
public class FileSystemResource implements Resource {
    public FileSystemResource(String path) {

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
