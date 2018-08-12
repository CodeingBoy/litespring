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
    private String scope = SCOPE_DEFAULT;

    public GenericBeanDefinition(String className, String scope) {
        this.className = className;
        this.scope = scope;
    }

    @Override
    public String getClassName() {
        return this.className;
    }

    @Override
    public boolean isSingleton() {
        return scope.equals(BeanDefinition.SCOPE_SINGLETON) || scope.equals(BeanDefinition.SCOPE_DEFAULT);
    }

    @Override
    public boolean isPrototype() {
        return scope.equals(BeanDefinition.SCOPE_PROTOTYPE);
    }

    @Override
    public String getScope() {
        return scope;
    }
}
