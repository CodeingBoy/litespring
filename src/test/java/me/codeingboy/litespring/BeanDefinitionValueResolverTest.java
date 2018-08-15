package me.codeingboy.litespring;

import me.codeingboy.litespring.beans.factory.config.BeanDefinitionValueResolver;
import me.codeingboy.litespring.beans.factory.config.RuntimeBeanReference;
import me.codeingboy.litespring.beans.factory.config.TypedStringValue;
import me.codeingboy.litespring.beans.factory.support.DefaultBeanFactory;
import me.codeingboy.litespring.beans.factory.support.xml.XmlBeanDefinitionReader;
import me.codeingboy.litespring.core.io.ClasspathResource;
import me.codeingboy.litespring.core.io.Resource;
import me.codeingboy.litespring.dao.AccountDao;
import org.junit.Test;

import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

/**
 * Test for {@link BeanDefinitionValueResolver}
 *
 * @author CodeingBoy
 * @version 2
 * @see BeanDefinitionValueResolver
 */
public class BeanDefinitionValueResolverTest {
    private final static String BEAN_ID_PET_STORE_SERVICE = "petStoreService";

    @Test
    public void runtimeBeanReferenceResolveTest() {
        DefaultBeanFactory factory = new DefaultBeanFactory();
        Resource resource = new ClasspathResource("petstore-v2.xml");
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.registerBeanDefinitions(resource);

        RuntimeBeanReference reference = new RuntimeBeanReference("accountDao");

        BeanDefinitionValueResolver resolver = new BeanDefinitionValueResolver(factory);
        Object accountDao = resolver.resolveValueIfNecessary(reference);
        assertNotNull(accountDao);
        assertTrue(accountDao instanceof AccountDao);
    }

    @Test
    public void typedStringValueResolveTest() {
        DefaultBeanFactory factory = new DefaultBeanFactory();

        TypedStringValue typedStringValue = new TypedStringValue("test");

        BeanDefinitionValueResolver resolver = new BeanDefinitionValueResolver(factory);
        Object value = resolver.resolveValueIfNecessary(typedStringValue);
        assertNotNull(value);
        assertTrue(value instanceof String);
        assertEquals("test", value);
    }
}
