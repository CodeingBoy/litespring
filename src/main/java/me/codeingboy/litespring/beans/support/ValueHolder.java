package me.codeingboy.litespring.beans.support;

/**
 * A holder holding constructor arguments' info
 *
 * @author CodeingBoy
 * @version 1
 */
public class ValueHolder {
    private Object value;
    private Integer index;
    private String name;

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
