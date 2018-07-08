package me.codeingboy.litespring.beans.factory.support.xml;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import me.codeingboy.litespring.beans.BeanDefinition;
import me.codeingboy.litespring.beans.factory.BeanDefinitionReadException;
import me.codeingboy.litespring.beans.support.BeanDefinitionRegistry;
import me.codeingboy.litespring.beans.support.GenericBeanDefinition;
import me.codeingboy.litespring.utils.ClassUtils;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * A parser for parsing XML bean config file
 *
 * @author CodeingBoy
 * @version 2
 */
public class XmlBeanDefinitionReader {

    private final static String BEAN_ELEMENT = "bean";

    private final static String ID_ATTRIBUTE = "id";
    private final static String CLASS_ATTRIBUTE = "class";

    private List<BeanElement> beanElements = new ArrayList<>();
    private BeanDefinitionRegistry registry;

    /**
     * Construct a reader
     *
     * @param registry factory to be register definitions
     */
    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
        this.registry = registry;
    }

    /**
     * Register bean definitions to factory
     *
     * @param fileName bean definition file name
     */
    public void registerBeanDefinitions(String fileName) {
        try {
            parseXmlBeanDefinitionFile(fileName);
            for (XmlBeanDefinitionReader.BeanElement element : beanElements) {
                BeanDefinition definition = new GenericBeanDefinition(element.getClassName());
                registry.registerBeanDefinition(element.getId(), definition);
            }
        } catch (FileNotFoundException | DocumentException e) {
            throw new BeanDefinitionReadException("Exception occurred while reading bean definition xml", e);
        }
    }

    private void parseXmlBeanDefinitionFile(String fileName) throws FileNotFoundException, DocumentException {
        ClassLoader classLoader = ClassUtils.getDefaultClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);
        if (inputStream == null) {
            throw new FileNotFoundException();
        }

        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(inputStream);
        Element rootElement = document.getRootElement();
        parseBeanElements(rootElement);
    }

    private void parseBeanElements(Element root) {
        Iterator<Element> iterator = root.elementIterator(BEAN_ELEMENT);
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
