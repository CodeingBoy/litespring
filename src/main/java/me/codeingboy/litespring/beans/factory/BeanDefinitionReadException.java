package me.codeingboy.litespring.beans.factory;

import me.codeingboy.litespring.beans.BeansException;

/**
 * Exception occurred while reading bean definition xml
 *
 * @author CodeingBoy
 * @version 1
 * @see BeansException
 */
public class BeanDefinitionReadException extends BeansException {

    public BeanDefinitionReadException() {
    }

    public BeanDefinitionReadException(String message) {
        super(message);
    }

    public BeanDefinitionReadException(String message, Throwable cause) {
        super(message, cause);
    }

    public BeanDefinitionReadException(Throwable cause) {
        super(cause);
    }

    public BeanDefinitionReadException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
