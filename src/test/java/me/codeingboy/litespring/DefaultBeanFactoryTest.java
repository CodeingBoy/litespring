package me.codeingboy.litespring;

import me.codeingboy.litespring.beans.BeanDefinition;
import me.codeingboy.litespring.beans.factory.BeanCreationException;
import me.codeingboy.litespring.beans.factory.BeanDefinitionReadException;
import me.codeingboy.litespring.beans.factory.config.RuntimeBeanReference;
import me.codeingboy.litespring.beans.factory.config.TypedStringValue;
import me.codeingboy.litespring.beans.factory.support.DefaultBeanFactory;
import me.codeingboy.litespring.beans.factory.support.xml.XmlBeanDefinitionReader;
import me.codeingboy.litespring.beans.support.ConstructorArgument;
import me.codeingboy.litespring.beans.support.PropertyValue;
import me.codeingboy.litespring.beans.support.ValueHolder;
import me.codeingboy.litespring.core.io.ClasspathResource;
import me.codeingboy.litespring.core.io.Resource;
import me.codeingboy.litespring.services.PetStoreService;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Test for {@link DefaultBeanFactory}
 *
 * @author CodeingBoy
 * @version 2
 */
public class DefaultBeanFactoryTest {

    private final static String BEAN_ID_PET_STORE_SERVICE = "petStoreService";
    private final static String BEAN_ID_PROTOTYPE_BEAN = "prototypeBean";

    private DefaultBeanFactory factory;
    private XmlBeanDefinitionReader reader;

    @Before
    public void setup() throws Exception {
        factory = new DefaultBeanFactory();
        reader = new XmlBeanDefinitionReader(factory);
    }

    @Test
    public void beanGetTest() {
        Resource resource = new ClasspathResource("petstore-v3.xml");
        reader.registerBeanDefinitions(resource);

        BeanDefinition beanDefinition = factory.getBeanDefinition(BEAN_ID_PET_STORE_SERVICE);
        assertEquals("me.codeingboy.litespring.services.PetStoreService", beanDefinition.getClassName());

        Object bean = factory.getBean(BEAN_ID_PET_STORE_SERVICE);
        assertNotNull(bean);
        assertTrue(bean instanceof PetStoreService);

        PetStoreService petStoreService = (PetStoreService) bean;
    }

    @Test(expected = BeanDefinitionReadException.class)
    public void fileNotExistsTest() {
        reader.registerBeanDefinitions(new ClasspathResource("NotExists.xml"));
    }

    @Test
    public void beanDefinitionNotExistsTest() {
        Resource resource = new ClasspathResource("petstore-v3.xml");

        reader.registerBeanDefinitions(resource);

        BeanDefinition shouldBeNull = factory.getBeanDefinition("notExists");
        assertNull(shouldBeNull);
    }

    @Test(expected = BeanCreationException.class)
    public void notExistsBeanClassTest() {
        Resource resource = new ClasspathResource("petstore-v3.xml");
        reader.registerBeanDefinitions(resource);

        BeanDefinition beanDefinition = factory.getBeanDefinition("invalidBean");
        assertNotNull(beanDefinition);

        Object shouldThrowException = factory.getBean("invalidBean");
    }

    @Test
    public void singletonBeanTest() {
        Resource resource = new ClasspathResource("petstore-v3.xml");
        reader.registerBeanDefinitions(resource);

        BeanDefinition beanDefinition = factory.getBeanDefinition(BEAN_ID_PET_STORE_SERVICE);
        assertEquals("me.codeingboy.litespring.services.PetStoreService", beanDefinition.getClassName());

        assertTrue(beanDefinition.isSingleton());
        assertFalse(beanDefinition.isPrototype());
        assertEquals(BeanDefinition.SCOPE_DEFAULT, beanDefinition.getScope());

        Object bean = factory.getBean(BEAN_ID_PET_STORE_SERVICE);
        assertNotNull(bean);
        assertTrue(bean instanceof PetStoreService);

        Object shouldBeSameBean = factory.getBean(BEAN_ID_PET_STORE_SERVICE);
        assertEquals(bean, shouldBeSameBean);
    }

    @Test
    public void prototypeTest() {
        Resource resource = new ClasspathResource("petstore-v3.xml");
        reader.registerBeanDefinitions(resource);

        BeanDefinition beanDefinition = factory.getBeanDefinition(BEAN_ID_PROTOTYPE_BEAN);
        assertEquals("me.codeingboy.litespring.services.PetStoreService", beanDefinition.getClassName());

        assertTrue(beanDefinition.isPrototype());
        assertFalse(beanDefinition.isSingleton());
        assertEquals(BeanDefinition.SCOPE_PROTOTYPE, beanDefinition.getScope());

        Object bean = factory.getBean(BEAN_ID_PROTOTYPE_BEAN);
        assertNotNull(bean);
        assertTrue(bean instanceof PetStoreService);

        Object shouldNotBeSameBean = factory.getBean(BEAN_ID_PROTOTYPE_BEAN);
        assertNotEquals(bean, shouldNotBeSameBean);
    }

    @Test
    public void beanPropertyTest() {
        Resource resource = new ClasspathResource("petstore-v3.xml");
        reader.registerBeanDefinitions(resource);

        BeanDefinition beanDefinition = factory.getBeanDefinition(BEAN_ID_PET_STORE_SERVICE);
        assertEquals("me.codeingboy.litespring.services.PetStoreService", beanDefinition.getClassName());

        List<PropertyValue> propertyValueList = beanDefinition.getPropertyValues();
        assertEquals(0, propertyValueList.size());
    }

    @Test
    public void beanConstructorTest() {
        Resource resource = new ClasspathResource("petstore-v3.xml");
        reader.registerBeanDefinitions(resource);

        BeanDefinition beanDefinition = factory.getBeanDefinition(BEAN_ID_PET_STORE_SERVICE);
        assertEquals("me.codeingboy.litespring.services.PetStoreService", beanDefinition.getClassName());

        ConstructorArgument constructorArgument = beanDefinition.getConstructorArgument();
        List<ValueHolder> valueHolders = constructorArgument.getValueHolders();
        assertEquals(4, valueHolders.size());

        ValueHolder valueHolder1 = valueHolders.get(0);
        assertTrue(valueHolder1.getValue() instanceof RuntimeBeanReference);
        RuntimeBeanReference reference1 = (RuntimeBeanReference) valueHolder1.getValue();
        assertEquals("accountDao", reference1.getBeanName());

        ValueHolder valueHolder2 = valueHolders.get(1);
        assertTrue(valueHolder2.getValue() instanceof RuntimeBeanReference);
        RuntimeBeanReference reference2 = (RuntimeBeanReference) valueHolder2.getValue();
        assertEquals("itemDao", reference2.getBeanName());

        ValueHolder valueHolder3 = valueHolders.get(2);
        assertTrue(valueHolder3.getValue() instanceof TypedStringValue);
        TypedStringValue value3 = (TypedStringValue) valueHolder3.getValue();
        assertEquals("CodeingBoy", value3.getValue());

        ValueHolder valueHolder4 = valueHolders.get(3);
        assertTrue(valueHolder4.getValue() instanceof TypedStringValue);
        TypedStringValue value4 = (TypedStringValue) valueHolder4.getValue();
        assertEquals("3", value4.getValue());
    }

    private PropertyValue getPropertyValue(List<PropertyValue> values, String propertyName) {
        for (PropertyValue value : values) {
            if (value.getName().equals(propertyName)) {
                return value;
            }
        }
        return null;
    }
}
