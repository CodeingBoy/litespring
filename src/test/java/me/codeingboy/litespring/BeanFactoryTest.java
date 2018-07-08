package me.codeingboy.litespring;

import me.codeingboy.litespring.beans.BeanCreationException;
import me.codeingboy.litespring.beans.BeanDefinition;
import me.codeingboy.litespring.beans.BeanDefinitionReadException;
import me.codeingboy.litespring.beans.factory.BeanFactory;
import me.codeingboy.litespring.beans.factory.support.DefaultBeanFactory;
import me.codeingboy.litespring.services.PetStoreService;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

/**
 * Test for {@link BeanFactory}
 *
 * @author CodeingBoy
 * @version 1
 */
public class BeanFactoryTest {
    private final static String BEAN_ID_PET_STORE_SERVICE = "petStoreService";

    @Test
    public void beanGetTest() {
        BeanFactory factory = new DefaultBeanFactory("petstore-v1.xml");
        BeanDefinition beanDefinition = factory.getBeanDefinition(BEAN_ID_PET_STORE_SERVICE);

        assertEquals("me.codeingboy.litespring.services.PetStoreService", beanDefinition.getClassName());

        Object bean = factory.getBean(BEAN_ID_PET_STORE_SERVICE);
        assertNotNull(bean);
        assertTrue(bean instanceof PetStoreService);

        PetStoreService petStoreService = (PetStoreService) bean;
    }

    @Test(expected = BeanDefinitionReadException.class)
    public void fileNotExistsTest() {
        BeanFactory factory = new DefaultBeanFactory("NotExists.xml");
    }

    @Test
    public void beanDefinitionNotExistsTest() {
        BeanFactory factory = new DefaultBeanFactory("petstore-v1.xml");
        BeanDefinition shouldBeNull = factory.getBeanDefinition("notExists");
        assertNull(shouldBeNull);
    }

    @Test(expected = BeanCreationException.class)
    public void notExistsBeanClassTest() {
        BeanFactory factory = new DefaultBeanFactory("petstore-v1.xml");
        BeanDefinition beanDefinition = factory.getBeanDefinition("invalidBean");
        assertNotNull(beanDefinition);

        Object shouldThrowException = factory.getBean("invalidBean");
    }
}
