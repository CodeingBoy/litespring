package me.codeingboy.litespring;

import me.codeingboy.litespring.beans.typeconverter.SimpleTypeConverter;
import me.codeingboy.litespring.beans.typeconverter.TypeConverter;

import org.junit.Test;

import static junit.framework.TestCase.*;

/**
 * Test for {@link TypeConverter}
 * 
 * @author CodeingBoy
 * @version 1
 */
public class TypeConverterTest {

    @Test
    public void string2IntTest() {
        String input = "2";

        TypeConverter converter = new SimpleTypeConverter();
        Integer convertedValue = converter.convertIfNecessary(input, Integer.class);
        assertNotNull(convertedValue);
        assertEquals(2, convertedValue.intValue());
    }

}
