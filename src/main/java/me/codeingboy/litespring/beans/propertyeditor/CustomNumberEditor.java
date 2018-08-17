package me.codeingboy.litespring.beans.propertyeditor;

import me.codeingboy.litespring.utils.NumberUtils;
import me.codeingboy.litespring.utils.StringUtils;

import java.beans.PropertyEditorSupport;
import java.text.NumberFormat;

/**
 * A property editor for {@link Number}
 *
 * @author CodeingBoy
 * @version 2
 * @see PropertyEditorSupport
 */
public class CustomNumberEditor extends PropertyEditorSupport {
    private NumberFormat numberFormat;
    private Class<? extends Number> numberClass;
    private boolean allowEmpty;

    public CustomNumberEditor(Class<? extends Number> numberClass, NumberFormat numberFormat, boolean allowEmpty) {
        this.numberFormat = numberFormat;
        this.numberClass = numberClass;
        this.allowEmpty = allowEmpty;
    }

    public CustomNumberEditor(Class<? extends Number> numberClass, boolean allowEmpty) {
        this(numberClass, null, allowEmpty);
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (this.allowEmpty && !StringUtils.hasLength(text)) {
            setValue(null);
        } else if (numberFormat != null) {
            Number number = NumberUtils.parseNumber(text, numberClass, numberFormat);
            setValue(number);
        } else {
            Number number = NumberUtils.parseNumber(text, numberClass);
            setValue(number);
        }
    }
}
