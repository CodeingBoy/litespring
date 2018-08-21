package me.codeingboy.litespring.beans;

/**
 * An exception representing the value can not be converted to desired type
 *
 * @author CodeingBoy
 * @version 1
 */
public class TypeMismatchException extends Exception {
    private Object value;
    private Class type;

    public TypeMismatchException(Object value, Class type) {
        this.value = value;
        this.type = type;
    }

    @Override
    public String getMessage() {
        return String.format("Value %s can't be converted to type %s", value, type.getName());
    }
}
