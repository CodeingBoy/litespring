package me.codeingboy.litespring;

import me.codeingboy.litespring.context.ApplicationContext;
import me.codeingboy.litespring.context.support.ClassPathXmlApplicationContext;
import me.codeingboy.litespring.services.PetStoreService;
import org.junit.Assert;
import org.junit.Test;

/**
 * Test for {@link ClassPathXmlApplicationContext}
 *
 * @author CodeingBoy
 * @version 2
 */
public class ClassPathApplicationContextTest {

    private final static String BEAN_ID_PET_STORE_SERVICE = "petStoreService";

    @Test
    public void getBeanTest() {
        ApplicationContext context = new ClassPathXmlApplicationContext("petstore-v2.xml");
        PetStoreService petStoreService = (PetStoreService) context.getBean(BEAN_ID_PET_STORE_SERVICE);
        Assert.assertNotNull(petStoreService);

        Assert.assertNotNull(petStoreService.getAccountDao());
        Assert.assertNotNull(petStoreService.getItemDao());
    }

}
