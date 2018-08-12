package me.codeingboy.litespring.beans.support;

/**
 * Bean registry for singleton bean
 *
 * @author CodeingBoy
 * @version 1
 */
public interface SingletonBeanRegistry {

    void registerSingleton(String beanId, Object bean);

    Object getSingleton(String beanId);
}
