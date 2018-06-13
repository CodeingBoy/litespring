package me.codeingboy.litespring.utils;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * A parser for parser XML bean config file
 *
 * @author CodeingBoy
 * @version 1
 */
public class BeanConfigXmlParser {
    private final static String ID_ATTRIBUTE = "id";
    private final static String CLASS_ATTRIBUTE = "class";

    private List<BeanElement> beanElements = new ArrayList<>();

    public BeanConfigXmlParser(String fileName) throws FileNotFoundException, DocumentException {
        parse(fileName);
    }

    public List<BeanElement> getBeanElements() {
        return beanElements;
    }

    private void parse(String fileName) throws FileNotFoundException, DocumentException {
        InputStream inputStream = new FileInputStream(fileName);
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(inputStream);
        Element rootElement = document.getRootElement();
        parseBeanElements(rootElement);
    }

    private void parseBeanElements(Element root) {
        Iterator<Element> iterator = root.elementIterator("bean");
        Element currentElement;
        while (iterator.hasNext()) {
            currentElement = iterator.next();

            Attribute idAttribute = currentElement.attribute(ID_ATTRIBUTE);
            String id = idAttribute.getValue();

            Attribute classAttribute = currentElement.attribute(CLASS_ATTRIBUTE);
            String className = classAttribute.getValue();

            BeanElement beanElement = new BeanElement(id, className);

            beanElements.add(beanElement);
        }
    }

    public static class BeanElement {
        private String id;
        private String className;

        public BeanElement(String id, String className) {
            this.id = id;
            this.className = className;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getClassName() {
            return className;
        }

        public void setClassName(String className) {
            this.className = className;
        }
    }
}
