package me.codeingboy.litespring.beans.support;

/**
 * Bean's property
 *
 * @author CodeingBoy
 * @version 2
 */
public class PropertyValue {
    private String name;
    private Object value;
    private boolean converted;
    private Object convertedValue;

    public PropertyValue() {
    }

    public PropertyValue(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }

    public Object getConvertedValue() {
        if (!converted) {
            return value;
        }
        return null;
    }
}
