package me.codeingboy.litespring.context;

import me.codeingboy.litespring.core.io.FileSystemResource;

/**
 * Application context based on xml on file system
 *
 * @author CodeingBoy
 * @version 2
 * @see AbstractXmlApplicationContext
 */
public class FileSystemXmlApplicationContext extends AbstractXmlApplicationContext {

    public FileSystemXmlApplicationContext(String configFilePath) {
        super(new FileSystemResource(configFilePath));
    }

}
