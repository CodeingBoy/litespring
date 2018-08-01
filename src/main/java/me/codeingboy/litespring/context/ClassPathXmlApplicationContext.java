package me.codeingboy.litespring.context;

import me.codeingboy.litespring.beans.factory.support.DefaultBeanFactory;
import me.codeingboy.litespring.beans.factory.support.xml.XmlBeanDefinitionReader;
import me.codeingboy.litespring.core.io.ClasspathResource;
import me.codeingboy.litespring.core.io.Resource;

/**
 * Application context based on xml in classpath
 *
 * @author CodeingBoy
 * @version 1
 * @see ApplicationContext
 */
public class ClassPathXmlApplicationContext implements ApplicationContext {

    private DefaultBeanFactory beanFactory;

    public ClassPathXmlApplicationContext(String fileName) {
        beanFactory = new DefaultBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);

        Resource resource = new ClasspathResource(fileName);

        reader.registerBeanDefinitions(resource);
    }

    @Override
    public Object getBean(String beanId) {
        return beanFactory.getBean(beanId);
    }
}
