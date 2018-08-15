package me.codeingboy.litespring.beans.factory.config;

import me.codeingboy.litespring.beans.factory.support.DefaultBeanFactory;

/**
 * A resolver for converting bean definition to value
 *
 * @author CodeingBoy
 * @version 1
 * @see me.codeingboy.litespring.beans.support.PropertyValue
 */
public class BeanDefinitionValueResolver {
    private DefaultBeanFactory beanFactory;

    public BeanDefinitionValueResolver(DefaultBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public Object resolveValueIfNecessary(Object value) {
        if (value instanceof RuntimeBeanReference) {
            return resolveRuntimeBeanReference((RuntimeBeanReference) value);
        } else if (value instanceof TypedStringValue) {
            return resolveTypedStringValue((TypedStringValue) value);
        } else {
            throw new IllegalArgumentException("The value is neither RuntimeBeanReference nor TypedStringValue");
        }
    }

    private Object resolveTypedStringValue(TypedStringValue value) {
        return null;
    }

    private Object resolveRuntimeBeanReference(RuntimeBeanReference reference) {
        return beanFactory.getBean(reference.getBeanName());
    }

}
