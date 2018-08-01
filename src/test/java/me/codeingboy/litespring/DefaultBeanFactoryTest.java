package me.codeingboy.litespring;

import me.codeingboy.litespring.beans.BeanDefinition;
import me.codeingboy.litespring.beans.factory.BeanCreationException;
import me.codeingboy.litespring.beans.factory.BeanDefinitionReadException;
import me.codeingboy.litespring.beans.factory.support.DefaultBeanFactory;
import me.codeingboy.litespring.beans.factory.support.xml.XmlBeanDefinitionReader;
import me.codeingboy.litespring.core.io.ClasspathResource;
import me.codeingboy.litespring.core.io.Resource;
import me.codeingboy.litespring.services.PetStoreService;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test for {@link DefaultBeanFactory}
 *
 * @author CodeingBoy
 * @version 2
 */
public class DefaultBeanFactoryTest {

    private final static String BEAN_ID_PET_STORE_SERVICE = "petStoreService";

    private DefaultBeanFactory factory;
    private XmlBeanDefinitionReader reader;

    @Before
    public void setup() throws Exception {
        factory = new DefaultBeanFactory();
        reader = new XmlBeanDefinitionReader(factory);
    }

    @Test
    public void beanGetTest() {
        Resource resource = new ClasspathResource("petstore-v1.xml");
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
        Resource resource = new ClasspathResource("petstore-v1.xml");

        reader.registerBeanDefinitions(resource);

        BeanDefinition shouldBeNull = factory.getBeanDefinition("notExists");
        assertNull(shouldBeNull);
    }

    @Test(expected = BeanCreationException.class)
    public void notExistsBeanClassTest() {
        Resource resource = new ClasspathResource("petstore-v1.xml");
        reader.registerBeanDefinitions(resource);

        BeanDefinition beanDefinition = factory.getBeanDefinition("invalidBean");
        assertNotNull(beanDefinition);

        Object shouldThrowException = factory.getBean("invalidBean");
    }
}
