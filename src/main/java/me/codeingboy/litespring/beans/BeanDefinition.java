package me.codeingboy.litespring.beans;

/**
 * Bean's definition information, such as class name and id
 *
 * @author CodeingBoy
 * @version 1
 */
public interface BeanDefinition {

    /**
     * Get bean's class name
     *
     * @return bean's class name
     */
    String getClassName();
}
