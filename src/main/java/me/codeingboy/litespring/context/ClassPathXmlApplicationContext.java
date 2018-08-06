package me.codeingboy.litespring.context;

import me.codeingboy.litespring.core.io.ClasspathResource;
import me.codeingboy.litespring.core.io.Resource;

/**
 * Application context based on xml in classpath
 *
 * @author CodeingBoy
 * @version 3
 * @see AbstractXmlApplicationContext
 */
public class ClassPathXmlApplicationContext extends AbstractXmlApplicationContext {

    public ClassPathXmlApplicationContext(String fileName) {
        super(fileName);
    }

    @Override
    public Resource getResourceByPath(String path) {
        return new ClasspathResource(path);
    }

}
