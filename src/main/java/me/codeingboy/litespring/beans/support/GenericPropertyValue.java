package me.codeingboy.litespring.beans.support;

/**
 * Default implementation of {@link PropertyValue}
 *
 * @author CodeingBoy
 * @version 1
 * @see PropertyValue
 */
public class GenericPropertyValue implements PropertyValue {
    private String name;
    private String value;

    public GenericPropertyValue(String name, String value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public Object getConvertedValue() {
        return null;
    }
}
