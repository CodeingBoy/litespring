package me.codeingboy.litespring.beans.factory.support.xml;

import me.codeingboy.litespring.beans.BeanDefinition;
import me.codeingboy.litespring.beans.factory.BeanDefinitionReadException;
import me.codeingboy.litespring.beans.factory.config.RuntimeBeanReference;
import me.codeingboy.litespring.beans.factory.config.TypedStringValue;
import me.codeingboy.litespring.beans.support.*;
import me.codeingboy.litespring.core.io.Resource;
import me.codeingboy.litespring.utils.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

/**
 * A parser for parsing XML bean config file
 *
 * @author CodeingBoy
 * @version 3
 */
public class XmlBeanDefinitionReader {

    private final static Logger LOGGER = LogManager.getLogger();

    private final static String BEAN_ELEMENT = "bean";

    private final static String ID_ATTRIBUTE = "id";
    private final static String CLASS_ATTRIBUTE = "class";
    private final static String SCOPE_ATTRIBUTE = "scope";

    private final static String PROPERTY_ELEMENT = "property";
    private final static String NAME_ATTRIBUTE = "name";
    private final static String REF_ATTRIBUTE = "ref";
    private final static String VALUE_ATTRIBUTE = "value";

    private final static String CONSTRUCTOR_ARG_ELEMENT = "constructor-arg";

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
                List<PropertyValue> propertyValues = element.getPropertyValues();
                BeanDefinition definition = new GenericBeanDefinition(element.getClassName(), scope, propertyValues, element.getConstructorArgument());
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

            List<PropertyValue> propertyValues = parsePropertyElements(currentElement);
            ConstructorArgument constructorArgument = parseConstructorArgsElement(currentElement);

            BeanElement beanElement = new BeanElement(id, className, scope, propertyValues, constructorArgument);
            beanElements.add(beanElement);
        }
    }

    private List<PropertyValue> parsePropertyElements(Element beanElement) {
        Iterator<Element> elementIterator = beanElement.elementIterator(PROPERTY_ELEMENT);
        List<PropertyValue> values = new ArrayList<>();

        while (elementIterator.hasNext()) {
            Element propertyElement = elementIterator.next();

            Attribute nameAttribute = propertyElement.attribute(NAME_ATTRIBUTE);
            if (!StringUtils.hasLength(nameAttribute.getValue())) {
                LOGGER.fatal("Property element should have a 'name' attribute");
                return null;
            }
            String propertyName = nameAttribute.getValue();
            Object value = parsePropertyValue(propertyElement);

            PropertyValue v = new PropertyValue(propertyName, value);
            values.add(v);
        }

        return values;
    }

    private ConstructorArgument parseConstructorArgsElement(Element beanElement) {
        Iterator<Element> elementIterator = beanElement.elementIterator(CONSTRUCTOR_ARG_ELEMENT);
        ConstructorArgument argument = new ConstructorArgument();

        while (elementIterator.hasNext()) {
            Element constructorArgsElement = elementIterator.next();

            Object value = parsePropertyValue(constructorArgsElement);

            ValueHolder holder = new ValueHolder();
            holder.setValue(value);
            argument.addValueHolder(holder);
        }

        return argument;
    }

    private Object parsePropertyValue(Element propertyElement) {
        Attribute refAttribute = propertyElement.attribute(REF_ATTRIBUTE);
        Attribute valueAttribute = propertyElement.attribute(VALUE_ATTRIBUTE);

        if (refAttribute != null) {
            String reference = refAttribute.getValue();
            if (!StringUtils.hasText(reference)) {
                LOGGER.error("ref attribute should specify a non-null string");
                return null;
            }
            return new RuntimeBeanReference(reference);
        } else if (valueAttribute != null) {
            String value = valueAttribute.getValue();
            return new TypedStringValue(value);
        } else {
            throw new BeanDefinitionReadException("Property element should have either a 'ref' attribute or a 'value' attribute");
        }
    }

    public static class BeanElement {

        private String id;
        private String className;
        private String scope;
        private List<PropertyValue> propertyValues;
        private ConstructorArgument constructorArgument;

        public BeanElement(String id, String className, String scope, List<PropertyValue> propertyValues, ConstructorArgument constructorArgument) {
            this.id = id;
            this.className = className;
            this.scope = scope;
            this.propertyValues = propertyValues;
            this.constructorArgument = constructorArgument;
        }

        public BeanElement(String id, String className, String scope, List<PropertyValue> propertyValues) {
            this.id = id;
            this.className = className;
            this.scope = scope;
            this.propertyValues = propertyValues;
        }

        public BeanElement(String id, String className, String scope) {
            this.id = id;
            this.className = className;
            this.scope = scope;
        }

        public List<PropertyValue> getPropertyValues() {
            return propertyValues;
        }

        public void setPropertyValues(List<PropertyValue> propertyValues) {
            this.propertyValues = propertyValues;
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

        public ConstructorArgument getConstructorArgument() {
            return constructorArgument;
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
