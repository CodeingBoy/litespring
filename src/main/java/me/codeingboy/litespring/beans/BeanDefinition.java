package me.codeingboy.litespring.beans;

import me.codeingboy.litespring.beans.support.ConstructorArgument;
import me.codeingboy.litespring.beans.support.PropertyValue;

import java.util.List;

/**
 * Bean's definition information, such as class name and id
 *
 * @author CodeingBoy
 * @version 2
 */
public interface BeanDefinition {

    String SCOPE_SINGLETON = "Singleton", SCOPE_DEFAULT = "", SCOPE_PROTOTYPE = "prototype";

    /**
     * Get bean's class name
     *
     * @return bean's class name
     */
    String getClassName();

    /**
     * Is this bean's scope singleton?
     *
     * @return if scope is singleton, return <code>true</code>; else return <code>false</code>
     */
    boolean isSingleton();

    /**
     * Is this bean's scope prototype?
     * @return if scope is prototype, return <code>true</code>; else return <code>false</code>
     */
    boolean isPrototype();

    /**
     * Get bean's scope
     * @return A string representing bean's scope
     */
    String getScope();

    /**
     * Get bean's properties
     *
     * @return bean's properties
     */
    List<PropertyValue> getPropertyValues();

    /**
     * Get bean's constructor arguments
     *
     * @return an object representing constructor arguments
     */
    ConstructorArgument getConstructorArgument();
}
