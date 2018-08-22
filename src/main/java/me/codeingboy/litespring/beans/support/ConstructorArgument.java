package me.codeingboy.litespring.beans.support;

import java.util.ArrayList;
import java.util.List;

/**
 * A class representing bean's constructor and corresponding arguments
 *
 * @author CodeingBoy
 * @version 1
 */
public class ConstructorArgument {
    private List<ValueHolder> valueHolderList = new ArrayList<>();

    public void addValueHolder(ValueHolder valueHolder) {
        valueHolderList.add(valueHolder);
    }

    public List<ValueHolder> getValueHolders() {
        return valueHolderList;
    }
}
