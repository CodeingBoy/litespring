package me.codeingboy.litespring.beans.support;

/**
 * Interface of bean's property
 *
 * @author CodeingBoy
 * @version 1
 */
public interface PropertyValue {
    String getName();

    String getValue();

    Object getConvertedValue();
}
