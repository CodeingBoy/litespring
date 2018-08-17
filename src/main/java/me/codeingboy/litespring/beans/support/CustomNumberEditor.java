package me.codeingboy.litespring.beans.support;

import me.codeingboy.litespring.utils.NumberUtils;
import me.codeingboy.litespring.utils.StringUtils;

import java.beans.PropertyEditorSupport;
import java.text.NumberFormat;
import java.text.ParseException;

/**
 * @author CodeingBoy
 * @version 1
 * @see PropertyEditorSupport
 */
public class CustomNumberEditor extends PropertyEditorSupport {
    private NumberFormat numberFormat;
    private Class numberClass;
    private boolean allowEmpty;

    public CustomNumberEditor(Class numberClass, NumberFormat numberFormat, boolean allowEmpty) {
        this.numberFormat = numberFormat;
        this.numberClass = numberClass;
        this.allowEmpty = allowEmpty;
    }

    public CustomNumberEditor(Class numberClass, boolean allowEmpty) {
        this(numberClass, null, allowEmpty);
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (this.allowEmpty && !StringUtils.hasLength(text)) {
            setValue(null);
        } else if (numberFormat != null) {
            try {
                Number number = numberFormat.parse(text);
                setValue(number);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else {
            Number number = NumberUtils.parseNumber(text, numberClass);
            setValue(number);
        }
    }
}
