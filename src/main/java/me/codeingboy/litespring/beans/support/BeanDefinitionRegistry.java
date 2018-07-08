package me.codeingboy.litespring.beans.support;

import me.codeingboy.litespring.beans.BeanDefinition;

/**
 * Interface for registering {@link me.codeingboy.litespring.beans.BeanDefinition} to {@link me.codeingboy.litespring.beans.factory.BeanFactory}
 *
 * @author CodeingBoy
 * @version 1
 */
public interface BeanDefinitionRegistry {

    /**
     * Get bean's definition
     *
     * @param beanId bean's id
     * @return bean's definition object, if not found, return <code>null</code>
     */
    BeanDefinition getBeanDefinition(String beanId);

    /**
     * Register bean definition to factory
     *
     * @param beanId bean's id
     * @param beanDefinition bean's definition
     */
    void registerBeanDefinition(String beanId, BeanDefinition beanDefinition);
}
