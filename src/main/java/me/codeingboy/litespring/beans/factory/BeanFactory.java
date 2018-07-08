package me.codeingboy.litespring.beans.factory;

/**
 * Factory of beans
 *
 * @author CodeingBoy
 * @version 1
 */
public interface BeanFactory {

    /**
     * Get bean from bean factory
     *
     * @param beanId bean's id
     * @return an instance of bean, or null if not found
     */
    Object getBean(String beanId);
}
