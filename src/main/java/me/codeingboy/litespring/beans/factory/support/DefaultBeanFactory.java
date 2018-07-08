package me.codeingboy.litespring.beans.factory.support;

import me.codeingboy.litespring.beans.BeanDefinition;
import me.codeingboy.litespring.beans.factory.BeanCreationException;
import me.codeingboy.litespring.beans.factory.BeanFactory;
import me.codeingboy.litespring.beans.support.BeanDefinitionRegistry;
import me.codeingboy.litespring.utils.ClassUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Default implementation of {@link DefaultBeanFactory}
 *
 * @author CodeingBoy
 * @version 1
 * @see BeanFactory
 */
public class DefaultBeanFactory implements BeanFactory, BeanDefinitionRegistry {

    private Map<String, BeanDefinition> definitionMap = new HashMap<>();

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
        ClassLoader classLoader = ClassUtils.getDefaultClassLoader();
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

}
