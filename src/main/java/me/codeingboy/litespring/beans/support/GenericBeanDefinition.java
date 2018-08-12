package me.codeingboy.litespring.beans.support;

import me.codeingboy.litespring.beans.BeanDefinition;

import java.util.List;

/**
 * Default implementation of {@link BeanDefinition}
 *
 * @author CodeingBoy
 * @version 1
 */
public class GenericBeanDefinition implements BeanDefinition {

    private String className;
    private String scope = SCOPE_DEFAULT;
    private List<PropertyValue> propertyValues;

    public GenericBeanDefinition(String className, String scope, List<PropertyValue> propertyValues) {
        this.className = className;
        this.scope = scope;
        this.propertyValues = propertyValues;
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

    @Override
    public List<PropertyValue> getPropertyValues() {
        return propertyValues;
    }
}
