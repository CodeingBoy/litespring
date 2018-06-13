package me.codeingboy.litespring.beans.support;

import me.codeingboy.litespring.beans.BeanDefinition;

/**
 * Default implementation of {@link BeanDefinition}
 *
 * @author CodeingBoy
 * @version 1
 */
public class GenericBeanDefinition implements BeanDefinition {
    private String className;

    public GenericBeanDefinition(String className) {
        this.className = className;
    }

    @Override
    public String getClassName() {
        return this.className;
    }
}
