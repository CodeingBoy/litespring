package me.codeingboy.litespring.context;

import me.codeingboy.litespring.beans.factory.support.DefaultBeanFactory;
import me.codeingboy.litespring.beans.factory.support.xml.XmlBeanDefinitionReader;

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
        reader.registerBeanDefinitions(fileName);
    }

    @Override
    public Object getBean(String beanId) {
        return beanFactory.getBean(beanId);
    }
}
