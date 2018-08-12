package me.codeingboy.litespring.beans.support;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Default implementation of {@link SingletonBeanRegistry}
 *
 * @author CodeingBoy
 * @version 1
 * @see SingletonBeanRegistry
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {
    Map<String, Object> singletonBeanMap = new ConcurrentHashMap<>();

    @Override
    public void registerSingleton(String beanId, Object bean) {
        if (beanId == null || beanId.isEmpty()) {
            throw new IllegalArgumentException("Parameter beanId not not be null or empty");
        }
        if (singletonBeanMap.containsKey(beanId)) {
            throw new IllegalArgumentException("Singleton bean '" + beanId + "' already exists");
        }
        singletonBeanMap.put(beanId, bean);
    }

    @Override
    public Object getSingleton(String beanId) {
        return singletonBeanMap.get(beanId);
    }

}
