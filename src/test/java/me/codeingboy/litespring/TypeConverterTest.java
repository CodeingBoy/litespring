package me.codeingboy.litespring;

import me.codeingboy.litespring.beans.typeconverter.SimpleTypeConverter;
import me.codeingboy.litespring.beans.typeconverter.TypeConverter;
import org.junit.Test;

import static junit.framework.TestCase.*;

/**
 * Test for {@link TypeConverter}
 * 
 * @author CodeingBoy
 * @version 2
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

    @Test
    public void string2StringTest() {
        String input = "Bug is annoying";

        TypeConverter converter = new SimpleTypeConverter();
        String convertedValue = converter.convertIfNecessary(input, String.class);
        assertNotNull(convertedValue);
        assertEquals("Bug is annoying", convertedValue);
    }

    @Test
    public void string2BooleanTest() {
        String input = "true";

        TypeConverter converter = new SimpleTypeConverter();
        Boolean convertedValue = converter.convertIfNecessary(input, Boolean.class);
        assertNotNull(convertedValue);
        assertTrue(convertedValue);
    }
}
