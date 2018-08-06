package me.codeingboy.litespring.beans.factory;

/**
 * An interface providing ways to bean factory configuration
 *
 * @author CodeingBoy
 * @version 1
 */
public interface ConfigurableBeanFactory extends BeanFactory {
    /**
     * Get class loader used for load beans
     *
     * @return A {@link ClassLoader}
     */
    ClassLoader getBeanClassLoader();

    /**
     * Set class loader used for load beans
     */
    void setBeanClassLoader(ClassLoader classLoader);
}
