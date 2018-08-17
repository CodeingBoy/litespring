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
    private final static Collection<String> trueStrings = Arrays.asList("true", "yes", "on", "1");
    private final static Collection<String> falseStrings = Arrays.asList("false", "no", "off", "0");
    private boolean allowEmpty;

    public CustomBooleanEditor(boolean allowEmpty) {
        this.allowEmpty = allowEmpty;
    }

    public CustomBooleanEditor() {
        this(true);
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (allowEmpty && !StringUtils.hasLength(text)) {
            setValue(null);
        }

        if (trueStrings.contains(text)) {
            setValue(true);
        } else if (falseStrings.contains(text)) {
            setValue(false);
        } else {
            throw new IllegalArgumentException();
        }
    }
}
