package me.codeingboy.litespring.context;

import me.codeingboy.litespring.beans.factory.support.DefaultBeanFactory;
import me.codeingboy.litespring.beans.factory.support.xml.XmlBeanDefinitionReader;
import me.codeingboy.litespring.core.io.Resource;

/**
 * An abstract xml application context based on {@link Resource} abstraction
 *
 * @author CodeingBoy
 * @version 1
 * @see ApplicationContext
 */
public abstract class AbstractXmlApplicationContext implements ApplicationContext {
    private DefaultBeanFactory beanFactory;

    public AbstractXmlApplicationContext(Resource resource) {
        beanFactory = new DefaultBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);

        reader.registerBeanDefinitions(resource);
    }

    @Override
    public Object getBean(String beanId) {
        return beanFactory.getBean(beanId);
    }
}
