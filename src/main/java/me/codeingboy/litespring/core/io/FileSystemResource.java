package me.codeingboy.litespring.core.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * A resource located in file system
 *
 * @author CodeingBoy
 * @version 1
 * @see Resource
 */
public class FileSystemResource implements Resource {
    private String path;
    private File file;

    public FileSystemResource(String path) {
        if (path == null) {
            throw new IllegalArgumentException();
        }
        this.path = path;
        this.file = new File(path);
    }

    @Override
    public InputStream getInputStream() throws FileNotFoundException {
        return new FileInputStream(file);
    }

    @Override
    public String getDescription() {
        return file + "[" + file.getAbsolutePath() + "]";
    }
}
