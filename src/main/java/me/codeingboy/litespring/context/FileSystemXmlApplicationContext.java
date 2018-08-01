package me.codeingboy.litespring.context;

import me.codeingboy.litespring.beans.factory.support.DefaultBeanFactory;
import me.codeingboy.litespring.beans.factory.support.xml.XmlBeanDefinitionReader;
import me.codeingboy.litespring.core.io.FileSystemResource;

/**
 * Application context based on xml on file system
 *
 * @author CodeingBoy
 * @version 1
 * @see ApplicationContext
 */
public class FileSystemXmlApplicationContext implements ApplicationContext {
    private DefaultBeanFactory factory;

    public FileSystemXmlApplicationContext(String configFilePath) {
        factory = new DefaultBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);

        FileSystemResource configResource = new FileSystemResource(configFilePath);

        reader.registerBeanDefinitions(configResource);
    }

    @Override
    public Object getBean(String beanId) {
        return factory.getBean(beanId);
    }
}
