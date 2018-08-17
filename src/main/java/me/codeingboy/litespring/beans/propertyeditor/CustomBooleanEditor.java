package me.codeingboy.litespring.beans.propertyeditor;

import me.codeingboy.litespring.utils.StringUtils;

import java.beans.PropertyEditorSupport;
import java.util.Arrays;
import java.util.Collection;

/**
 * A property editor for {@link Boolean}
 *
 * @author CodeingBoy
 * @version 1
 * @see PropertyEditorSupport
 */
public class CustomBooleanEditor extends PropertyEditorSupport {
    private final static Collection<String> TRUE_STRINGS = Arrays.asList("true", "yes", "on", "1");
    private final static Collection<String> FALSE_STRINGS = Arrays.asList("false", "no", "off", "0");
    private final static String VALUE_TRUE = "true", VALUE_FALSE = "false";
    private boolean allowEmpty;

    public CustomBooleanEditor(boolean allowEmpty) {
        this.allowEmpty = allowEmpty;
    }

    public CustomBooleanEditor() {
        this(true);
    }

    public boolean containsIgnoreCase(Collection<String> strings, String key) {
        for (String string : strings) {
            if (string.equalsIgnoreCase(key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String getAsText() {
        Boolean value = (Boolean) getValue();
        if (value == null) {
            return "";
        }

        if (value) {
            return VALUE_TRUE;
        } else {
            return VALUE_FALSE;
        }
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (allowEmpty && !StringUtils.hasLength(text)) {
            setValue(null);
        }

        if (containsIgnoreCase(TRUE_STRINGS, text)) {
            setValue(true);
        } else if (containsIgnoreCase(FALSE_STRINGS, text)) {
            setValue(false);
        } else {
            throw new IllegalArgumentException();
        }
    }
}
