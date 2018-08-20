package me.codeingboy.litespring.beans.typeconverter;

/**
 * An interface with type convert operations defined
 * 
 * @author CodeingBoy
 * @version 1
 */
public interface TypeConverter {
    <T> T convertIfNecessary(String value, Class<T> objectClass);
}
