package me.codeingboy.litespring.beans.factory.config;

/**
 * Runtime's reference of bean
 *
 * @author CodeingBoy
 * @version 1
 */
public class RuntimeBeanReference {
    private final String beanName;

    public RuntimeBeanReference(String beanName) {
        this.beanName = beanName;
    }

    public String getBeanName() {
        return beanName;
    }
}
