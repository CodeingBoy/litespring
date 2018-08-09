package me.codeingboy.litespring.context.support;

import me.codeingboy.litespring.core.io.Resource;

/**
 * Application context based on xml in classpath
 *
 * @author CodeingBoy
 * @version 5
 * @see AbstractXmlApplicationContext
 */
public class ClassPathXmlApplicationContext extends AbstractXmlApplicationContext {

    public ClassPathXmlApplicationContext(String fileName) {
        super(CLASSPATH_PREFIX + fileName);
    }

    @Override
    public Resource getResourceByPath(String path) {
        return getResource(path);
    }

}
