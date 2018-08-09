package me.codeingboy.litespring.beans;

/**
 * Bean's definition information, such as class name and id
 *
 * @author CodeingBoy
 * @version 1
 */
public interface BeanDefinition {

    String SCOPE_SINGLETON = "Singleton", SCOPE_DEFAULT = SCOPE_SINGLETON, SCOPE_PROTOTYPE = "prototype";

    /**
     * Get bean's class name
     *
     * @return bean's class name
     */
    String getClassName();

    boolean isSingleton();

    boolean isPrototype();

    String getScope();
}
