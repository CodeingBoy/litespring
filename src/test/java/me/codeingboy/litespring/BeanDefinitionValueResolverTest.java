package me.codeingboy.litespring;

import me.codeingboy.litespring.beans.BeanDefinition;
import me.codeingboy.litespring.beans.factory.config.BeanDefinitionValueResolver;
import me.codeingboy.litespring.beans.factory.support.DefaultBeanFactory;
import me.codeingboy.litespring.beans.factory.support.xml.XmlBeanDefinitionReader;
import me.codeingboy.litespring.beans.support.PropertyValue;
import me.codeingboy.litespring.core.io.ClasspathResource;
import me.codeingboy.litespring.core.io.Resource;
import me.codeingboy.litespring.dao.AccountDao;
import org.junit.Test;

import java.util.List;

import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.assertNotNull;

/**
 * Test for {@link BeanDefinitionValueResolver}
 *
 * @author CodeingBoy
 * @version 1
 * @see BeanDefinitionValueResolver
 */
public class BeanDefinitionValueResolverTest {
    private final static String BEAN_ID_PET_STORE_SERVICE = "petStoreService";

    @Test
    public void runtimeBeanReferenceTest() {
        DefaultBeanFactory factory = new DefaultBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        Resource resource = new ClasspathResource("petstore-v2.xml");
        reader.registerBeanDefinitions(resource);

        BeanDefinition definition = factory.getBeanDefinition(BEAN_ID_PET_STORE_SERVICE);
        PropertyValue accountDaoPropertyValue = getPropertyValue(definition.getPropertyValues(), "accountDao");
        assertNotNull(accountDaoPropertyValue);

        BeanDefinitionValueResolver resolver = new BeanDefinitionValueResolver(factory);
        Object accountDao = resolver.resolveValueIfNecessary(accountDaoPropertyValue.getValue());
        assertNotNull(accountDao);
        assertTrue(accountDao instanceof AccountDao);
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
