package me.codeingboy.litespring.beans.typeconverter;

import me.codeingboy.litespring.beans.propertyeditor.CustomBooleanEditor;
import me.codeingboy.litespring.beans.propertyeditor.CustomNumberEditor;

/**
 * A simple implementation of {@link SimpleTypeConverter}
 * 
 * @author CodeingBoy
 * @version 1
 */
public class SimpleTypeConverter implements TypeConverter {

    private CustomBooleanEditor booleanEditor = new CustomBooleanEditor(true);
    
    private CustomNumberEditor numberEditor = new CustomNumberEditor(Integer.class, true);

    @Override
    public <T> T convertIfNecessary(String value, Class<T> objectClass) {
        if (objectClass == Integer.class) {
            numberEditor.setAsText(value);
            return (T)numberEditor.getValue();
        }
        return (T)null;
    }
}
