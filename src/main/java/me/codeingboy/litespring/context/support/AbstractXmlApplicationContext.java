package me.codeingboy.litespring.context.support;

import me.codeingboy.litespring.beans.factory.support.DefaultBeanFactory;
import me.codeingboy.litespring.beans.factory.support.xml.XmlBeanDefinitionReader;
import me.codeingboy.litespring.context.ApplicationContext;
import me.codeingboy.litespring.core.io.DefaultResourceLoader;
import me.codeingboy.litespring.core.io.Resource;

/**
 * An abstract xml application context based on {@link Resource} abstraction
 *
 * @author CodeingBoy
 * @version 4
 * @see ApplicationContext
 */
public abstract class AbstractXmlApplicationContext extends DefaultResourceLoader implements ApplicationContext {
    private DefaultBeanFactory beanFactory;

    @Override
    public ClassLoader getBeanClassLoader() {
        return getClassLoader();
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        setClassLoader(classLoader);
    }

    public AbstractXmlApplicationContext(String configFile) {
        beanFactory = new DefaultBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);

        Resource resource = getResourceByPath(configFile);
        reader.registerBeanDefinitions(resource);
    }

    public abstract Resource getResourceByPath(String path);

    @Override
    public Object getBean(String beanId) {
        return beanFactory.getBean(beanId);
    }
}
