package me.codeingboy.litespring.beans.factory.support.xml;

import me.codeingboy.litespring.beans.BeanDefinition;
import me.codeingboy.litespring.beans.factory.BeanDefinitionReadException;
import me.codeingboy.litespring.beans.support.BeanDefinitionRegistry;
import me.codeingboy.litespring.beans.support.GenericBeanDefinition;
import me.codeingboy.litespring.beans.support.GenericPropertyValue;
import me.codeingboy.litespring.beans.support.PropertyValue;
import me.codeingboy.litespring.core.io.Resource;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

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
    private final static String SCOPE_ATTRIBUTE = "scope";

    private final static String PROPERTY_ELEMENT = "property";
    private final static String NAME_ATTRIBUTE = "name";
    private final static String REF_ATTRIBUTE = "ref";
    private final static String VALUE_ATTRIBUTE = "value";

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
     * @param resource Bean definition resource
     */
    public void registerBeanDefinitions(Resource resource) {
        try {
            parseXmlBeanDefinitionFile(resource);
            for (XmlBeanDefinitionReader.BeanElement element : beanElements) {
                String scope = element.getScope();
                List<PropertyElement> propertyElements = element.getPropertyElements();
                List<PropertyValue> propertyValues = propertyElements.stream()
                        .map(e -> new GenericPropertyValue(e.getName(), e.getRef())).collect(Collectors.toList());
                BeanDefinition definition = new GenericBeanDefinition(element.getClassName(), scope, propertyValues);
                registry.registerBeanDefinition(element.getId(), definition);
            }
        } catch (FileNotFoundException | DocumentException e) {
            throw new BeanDefinitionReadException("Exception occurred while reading bean definition xml", e);
        }
    }

    private void parseXmlBeanDefinitionFile(Resource resource) throws FileNotFoundException, DocumentException {
        InputStream inputStream = resource.getInputStream();

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

            Attribute scopeAttribute = currentElement.attribute(SCOPE_ATTRIBUTE);
            String scope = BeanDefinition.SCOPE_DEFAULT;
            if (scopeAttribute != null) {
                scope = scopeAttribute.getValue();
            }

            List<PropertyElement> propertyElements = parsePropertyElements(currentElement);

            BeanElement beanElement = new BeanElement(id, className, scope, propertyElements);

            beanElements.add(beanElement);
        }
    }

    private List<PropertyElement> parsePropertyElements(Element beanElement) {
        Iterator<Element> elementIterator = beanElement.elementIterator(PROPERTY_ELEMENT);
        List<PropertyElement> elements = new ArrayList<>();

        while (elementIterator.hasNext()) {
            Element propertyElement = elementIterator.next();
            Attribute nameAttribute = propertyElement.attribute(NAME_ATTRIBUTE);
            Attribute refAttribute = propertyElement.attribute(REF_ATTRIBUTE);
            Attribute valueAttribute = propertyElement.attribute(VALUE_ATTRIBUTE);

            if (nameAttribute == null) {
                throw new BeanDefinitionReadException("Property element should have a 'name' attribute");
            }
            String propertyName = nameAttribute.getValue();
            PropertyElement e = new PropertyElement();
            e.setName(propertyName);

            if (refAttribute != null) {
                String reference = refAttribute.getValue();
                e.setRef(reference);
            } else if (valueAttribute != null) {
                String value = valueAttribute.getValue();
                e.setValue(value);
            } else {
                throw new BeanDefinitionReadException("Property element should have either a 'ref' attribute or a 'value' attribute");
            }
            elements.add(e);
        }

        return elements;
    }

    public static class BeanElement {

        private String id;
        private String className;
        private String scope;
        private List<PropertyElement> propertyElements;

        public BeanElement(String id, String className, String scope, List<PropertyElement> propertyElements) {
            this.id = id;
            this.className = className;
            this.scope = scope;
            this.propertyElements = propertyElements;
        }

        public BeanElement(String id, String className, String scope) {
            this.id = id;
            this.className = className;
            this.scope = scope;
        }

        public List<PropertyElement> getPropertyElements() {
            return propertyElements;
        }

        public void setPropertyElements(List<PropertyElement> propertyElements) {
            this.propertyElements = propertyElements;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getScope() {
            return scope;
        }

        public void setScope(String scope) {
            this.scope = scope;
        }

        public String getClassName() {
            return className;
        }

        public void setClassName(String className) {
            this.className = className;
        }
    }

    public static class PropertyElement {
        private String name;
        private String ref;
        private String value;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getRef() {
            return ref;
        }

        public void setRef(String ref) {
            this.ref = ref;
        }
    }
}
