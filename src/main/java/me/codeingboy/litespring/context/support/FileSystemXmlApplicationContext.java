package me.codeingboy.litespring.context.support;

import me.codeingboy.litespring.core.io.Resource;

/**
 * Application context based on xml on file system
 *
 * @author CodeingBoy
 * @version 4
 * @see AbstractXmlApplicationContext
 */
public class FileSystemXmlApplicationContext extends AbstractXmlApplicationContext {

    public FileSystemXmlApplicationContext(String configFilePath) {
        super(configFilePath);
    }

    @Override
    public Resource getResourceByPath(String path) {
        return getResource(path);
    }

}
