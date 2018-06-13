package me.codeingboy.litespring.beans.factory.support;

import me.codeingboy.litespring.beans.BeanDefinition;
import me.codeingboy.litespring.beans.factory.BeanFactory;
import me.codeingboy.litespring.beans.support.GenericBeanDefinition;
import me.codeingboy.litespring.utils.BeanConfigXmlParser;
import org.dom4j.DocumentException;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Default implementation of {@link DefaultBeanFactory}
 *
 * @author CodeingBoy
 * @version 1
 * @see BeanFactory
 */
public class DefaultBeanFactory implements BeanFactory {
    private Map<String, BeanDefinition> definitionMap = new HashMap<>();

    /**
     * Constructing bean factory by using a xml config file
     * @param configFile the location of config file, based on classpath
     */
    public DefaultBeanFactory(String configFile) {
        loadBeanDefinitionFromXmlFile(configFile);
    }

    /**
     * Load bean definition from configuration xml file
     * @param configFile the location of config file, based on classpath
     */
    private void loadBeanDefinitionFromXmlFile(String configFile) {
        try {
            BeanConfigXmlParser parser = new BeanConfigXmlParser(configFile);
            List<BeanConfigXmlParser.BeanElement> beanElements = parser.getBeanElements();
            for (BeanConfigXmlParser.BeanElement element : beanElements) {
                BeanDefinition definition = new GenericBeanDefinition(element.getClassName());
                definitionMap.put(element.getId(), definition);
            }
        } catch (FileNotFoundException | DocumentException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Object getBean(String beanId) {
        return null;
    }

    @Override
    public BeanDefinition getBeanDefinition(String beanId) {
        return definitionMap.get(beanId);
    }

}
