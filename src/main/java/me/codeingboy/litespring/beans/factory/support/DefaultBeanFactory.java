package me.codeingboy.litespring.beans.factory.support;

import me.codeingboy.litespring.beans.BeanDefinition;
import me.codeingboy.litespring.beans.factory.BeanFactory;

/**
 * Default implementation of {@link DefaultBeanFactory}
 *
 * @author CodeingBoy
 * @version 1
 * @see BeanFactory
 */
public class DefaultBeanFactory implements BeanFactory {
    public DefaultBeanFactory(String configFile) {
    }

    @Override
    public Object getBean(String beanId) {
        return null;
    }

    @Override
    public BeanDefinition getBeanDefinition(String beanId) {
        return null;
    }
}
