package me.codeingboy.litespring.beans.typeconverter;

import me.codeingboy.litespring.beans.TypeMismatchException;
import me.codeingboy.litespring.beans.propertyeditor.CustomBooleanEditor;
import me.codeingboy.litespring.beans.propertyeditor.CustomNumberEditor;
import me.codeingboy.litespring.utils.ClassUtils;

import java.beans.PropertyEditorSupport;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple implementation of {@link SimpleTypeConverter}
 * 
 * @author CodeingBoy
 * @version 2
 */
public class SimpleTypeConverter implements TypeConverter {

    private Map<Class, PropertyEditorSupport> defaultEditors;

    private CustomBooleanEditor booleanEditor = new CustomBooleanEditor(true);
    
    private CustomNumberEditor numberEditor = new CustomNumberEditor(Integer.class, true);

    @Override
    public <T> T convertIfNecessary(Object value, Class<T> objectClass) throws TypeMismatchException {
        if (ClassUtils.isAssignable(value.getClass(), objectClass)) {
            return (T) value;
        }

        if (value instanceof String) {
            PropertyEditorSupport defaultEditor = getDefaultEditor(objectClass);
            try {
                defaultEditor.setAsText((String) value);
            } catch (IllegalArgumentException e) {
                throw new TypeMismatchException(value, objectClass);
            }
            return (T) defaultEditor.getValue();
        }
        throw new IllegalArgumentException();
    }

    private PropertyEditorSupport getDefaultEditor(Class requiredType) {
        if (defaultEditors == null) {
            createDefaultEditors();
        }
        return defaultEditors.get(requiredType);
    }

    private void createDefaultEditors() {
        defaultEditors = new HashMap<>();

        defaultEditors.put(boolean.class, new CustomBooleanEditor(false));
        defaultEditors.put(Boolean.class, new CustomBooleanEditor(true));

        defaultEditors.put(int.class, new CustomNumberEditor(Integer.class, false));
        defaultEditors.put(Integer.class, new CustomNumberEditor(Integer.class, true));

        defaultEditors.put(short.class, new CustomNumberEditor(Short.class, false));
        defaultEditors.put(Short.class, new CustomNumberEditor(Short.class, true));

        defaultEditors.put(long.class, new CustomNumberEditor(Long.class, false));
        defaultEditors.put(Long.class, new CustomNumberEditor(Long.class, true));
    }
}
