package me.codeingboy.litespring;

import me.codeingboy.litespring.beans.propertyeditor.CustomBooleanEditor;
import me.codeingboy.litespring.beans.propertyeditor.CustomNumberEditor;
import org.junit.Test;

import static junit.framework.TestCase.*;

/**
 * Test of {@link CustomBooleanEditor}
 *
 * @author CodeingBoy
 * @version 2
 * @see CustomBooleanEditor
 */
public class CustomBooleanEditorTest {

    @Test
    public void trueStringTest() {
        CustomBooleanEditor editor = new CustomBooleanEditor(true);

        editor.setAsText("true");
        Object value = editor.getValue();
        assertNotNull(value);
        assertTrue(value instanceof Boolean);
        assertTrue((Boolean) value);
    }

    @Test
    public void falseStringTest() {
        CustomBooleanEditor editor = new CustomBooleanEditor(true);

        editor.setAsText("false");
        Object value = editor.getValue();
        assertNotNull(value);
        assertTrue(value instanceof Boolean);
        assertFalse((Boolean) value);
    }

    @Test
    public void trueUpperCaseStringTest() {
        CustomBooleanEditor editor = new CustomBooleanEditor(true);

        editor.setAsText("TRUE");
        Object value = editor.getValue();
        assertNotNull(value);
        assertTrue(value instanceof Boolean);
        assertTrue((Boolean) value);
    }

    @Test
    public void falseUpperCaseStringTest() {
        CustomBooleanEditor editor = new CustomBooleanEditor(true);

        editor.setAsText("FALSE");
        Object value = editor.getValue();
        assertNotNull(value);
        assertTrue(value instanceof Boolean);
        assertFalse((Boolean) value);
    }

    @Test
    public void yesStringTest() {
        CustomBooleanEditor editor = new CustomBooleanEditor(true);

        editor.setAsText("yes");
        Object value = editor.getValue();
        assertNotNull(value);
        assertTrue(value instanceof Boolean);
        assertTrue((Boolean) value);
    }

    @Test
    public void noStringTest() {
        CustomBooleanEditor editor = new CustomBooleanEditor(true);

        editor.setAsText("no");
        Object value = editor.getValue();
        assertNotNull(value);
        assertTrue(value instanceof Boolean);
        assertFalse((Boolean) value);
    }

    @Test
    public void onStringTest() {
        CustomBooleanEditor editor = new CustomBooleanEditor(true);

        editor.setAsText("on");
        Object value = editor.getValue();
        assertNotNull(value);
        assertTrue(value instanceof Boolean);
        assertTrue((Boolean) value);
    }

    @Test
    public void offStringTest() {
        CustomBooleanEditor editor = new CustomBooleanEditor(true);

        editor.setAsText("off");
        Object value = editor.getValue();
        assertNotNull(value);
        assertTrue(value instanceof Boolean);
        assertFalse((Boolean) value);
    }

    @Test
    public void oneStringTest() {
        CustomBooleanEditor editor = new CustomBooleanEditor(true);

        editor.setAsText("1");
        Object value = editor.getValue();
        assertNotNull(value);
        assertTrue(value instanceof Boolean);
        assertTrue((Boolean) value);
    }

    @Test
    public void zeroStringTest() {
        CustomBooleanEditor editor = new CustomBooleanEditor(true);

        editor.setAsText("0");
        Object value = editor.getValue();
        assertNotNull(value);
        assertTrue(value instanceof Boolean);
        assertFalse((Boolean) value);
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
