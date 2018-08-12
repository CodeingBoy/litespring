package me.codeingboy.litespring.beans.factory.config;

/**
 * A string represents typed value
 *
 * @author CodeingBoy
 * @version 1
 */
public class TypedStringValue {
    private final String value;

    public TypedStringValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
