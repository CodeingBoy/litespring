package me.codeingboy.litespring.beans.factory.support;

import me.codeingboy.litespring.beans.BeanDefinition;
import me.codeingboy.litespring.beans.factory.BeanCreationException;
import me.codeingboy.litespring.beans.factory.BeanFactory;
import me.codeingboy.litespring.beans.factory.ConfigurableBeanFactory;
import me.codeingboy.litespring.beans.support.BeanDefinitionRegistry;
import me.codeingboy.litespring.utils.ClassUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Default implementation of {@link DefaultBeanFactory}
 *
 * @author CodeingBoy
 * @version 2
 * @see BeanFactory
 */
public class DefaultBeanFactory implements BeanFactory, BeanDefinitionRegistry, ConfigurableBeanFactory {

    private Map<String, BeanDefinition> definitionMap = new HashMap<>();

    private ClassLoader classLoader;

    /**
     * Construct a bean factory
     */
    public DefaultBeanFactory() {
    }

    @Override
    public Object getBean(String beanId) {
        BeanDefinition definition = getBeanDefinition(beanId);
        if (definition == null) {
            throw new BeanCreationException("Bean definition not found");
        }
        ClassLoader classLoader = getBeanClassLoader();
        try {
            Class<?> clazz = classLoader.loadClass(definition.getClassName());
            return clazz.newInstance();
        } catch (Exception e) {
            throw new BeanCreationException("Exception occurred while creating bean " + beanId + "'s instance", e);
        }
    }

    @Override
    public BeanDefinition getBeanDefinition(String beanId) {
        return definitionMap.get(beanId);
    }

    @Override
    public void registerBeanDefinition(String beanId, BeanDefinition beanDefinition) {
        this.definitionMap.put(beanId, beanDefinition);
    }

    @Override
    public ClassLoader getBeanClassLoader() {
        if (classLoader == null) {
            return ClassUtils.getDefaultClassLoader();
        }
        return classLoader;
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }
}
