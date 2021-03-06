package me.codeingboy.litespring.beans;

/**
 * An interface with type convert operations defined
 * 
 * @author CodeingBoy
 * @version 1
 */
public interface TypeConverter {
    <T> T convertIfNecessary(Object value, Class<T> objectClass) throws TypeMismatchException;
}
