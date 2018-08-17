package me.codeingboy.litespring;

import me.codeingboy.litespring.beans.propertyeditor.CustomNumberEditor;
import org.junit.Test;

import static junit.framework.TestCase.*;

/**
 * Test of {@link CustomNumberEditor}
 *
 * @author CodeingBoy
 * @version 1
 * @see CustomNumberEditor
 */
public class CustomNumberEditorTest {

    @Test
    public void numberTest() {
        CustomNumberEditor editor = new CustomNumberEditor(Integer.class, true);

        editor.setAsText("3");
        Object value = editor.getValue();
        assertNotNull(value);
        assertTrue(value instanceof Integer);
        assertEquals(3, value);
    }

    @Test
    public void emptyStringTest() {
        CustomNumberEditor editor = new CustomNumberEditor(Integer.class, true);

        editor.setAsText("");
        Object value = editor.getValue();
        assertNull(value);
    }

    @Test(expected = IllegalArgumentException.class)
    public void invalidStringTest() {
        CustomNumberEditor editor = new CustomNumberEditor(Integer.class, true);

        editor.setAsText("3.1");
    }
}
