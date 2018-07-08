package me.codeingboy.litespring.v1;

import me.codeingboy.litespring.context.ApplicationContext;
import me.codeingboy.litespring.context.ClassPathXmlApplicationContext;
import me.codeingboy.litespring.services.PetStoreService;
import org.junit.Assert;
import org.junit.Test;

/**
 * Test for {@link ApplicationContext}
 *
 * @author CodeingBoy
 * @version 1
 */
public class ApplicationContextTest {
    private final static String BEAN_ID_PET_STORE_SERVICE = "petStoreService";

    @Test
    public void getBeanTest() {
        ApplicationContext context = new ClassPathXmlApplicationContext("petstore-v1.xml");
        PetStoreService petStoreService = (PetStoreService) context.getBean(BEAN_ID_PET_STORE_SERVICE);
        Assert.assertNotNull(petStoreService);
    }

}