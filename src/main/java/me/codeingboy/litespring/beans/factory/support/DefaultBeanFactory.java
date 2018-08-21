package me.codeingboy.litespring.beans.factory.support;

import me.codeingboy.litespring.beans.BeanDefinition;
import me.codeingboy.litespring.beans.SimpleTypeConverter;
import me.codeingboy.litespring.beans.TypeConverter;
import me.codeingboy.litespring.beans.TypeMismatchException;
import me.codeingboy.litespring.beans.factory.BeanCreationException;
import me.codeingboy.litespring.beans.factory.BeanFactory;
import me.codeingboy.litespring.beans.factory.ConfigurableBeanFactory;
import me.codeingboy.litespring.beans.factory.config.BeanDefinitionValueResolver;
import me.codeingboy.litespring.beans.support.BeanDefinitionRegistry;
import me.codeingboy.litespring.beans.support.DefaultSingletonBeanRegistry;
import me.codeingboy.litespring.beans.support.PropertyValue;
import me.codeingboy.litespring.utils.ClassUtils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * Default implementation of {@link DefaultBeanFactory}
 *
 * @author CodeingBoy
 * @version 3
 * @see BeanFactory
 */
public class DefaultBeanFactory extends DefaultSingletonBeanRegistry
        implements BeanFactory, BeanDefinitionRegistry, ConfigurableBeanFactory {

    private Map<String, BeanDefinition> definitionMap = new HashMap<>();
    private BeanDefinitionValueResolver resolver = new BeanDefinitionValueResolver(this);
    private TypeConverter typeConverter = new SimpleTypeConverter();

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
        if (definition.isSingleton()) {
            Object singleton = getSingleton(beanId);
            if (singleton == null) {
                singleton = createBean(beanId, definition);
                registerSingleton(beanId, singleton);
            }
            return singleton;
        }
        return createBean(beanId, definition);
    }

    private Object createBean(String beanId, BeanDefinition definition) {
        Object bean = instantiateBean(beanId, definition);
        bean = populateBean(bean, definition);
        return bean;
    }

    private Object populateBean(Object bean, BeanDefinition definition) {
        for (PropertyValue propertyValue : definition.getPropertyValues()) {
            String name = propertyValue.getName();
            Object value = resolver.resolveValueIfNecessary(propertyValue.getValue());
            if (value == null) {
                continue;
            }
            try {
                BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass());
                for (PropertyDescriptor descriptor : beanInfo.getPropertyDescriptors()) {
                    if (descriptor.getName().equals(name)) {
                        try {
                            value = typeConverter.convertIfNecessary(value, descriptor.getPropertyType());
                            descriptor.getWriteMethod().invoke(bean, value);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (IllegalArgumentException e) {
                            // swallow it
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        } catch (TypeMismatchException e) {
                            throw new RuntimeException(e);
                        }
                        break;
                    }
                }
            } catch (IntrospectionException e) {
                e.printStackTrace();
            }
        }
        return bean;
    }

    private Object instantiateBean(String beanId, BeanDefinition definition) {
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
