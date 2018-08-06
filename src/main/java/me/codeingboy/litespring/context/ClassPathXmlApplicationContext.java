package me.codeingboy.litespring.context;

import me.codeingboy.litespring.core.io.ClasspathResource;

/**
 * Application context based on xml in classpath
 *
 * @author CodeingBoy
 * @version 2
 * @see AbstractXmlApplicationContext
 */
public class ClassPathXmlApplicationContext extends AbstractXmlApplicationContext {

    public ClassPathXmlApplicationContext(String fileName) {
        super(new ClasspathResource(fileName));
    }
}
