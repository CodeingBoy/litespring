package me.codeingboy.litespring;

import me.codeingboy.litespring.context.ApplicationContext;
import me.codeingboy.litespring.context.support.FileSystemXmlApplicationContext;
import me.codeingboy.litespring.services.PetStoreService;
import org.junit.Assert;
import org.junit.Test;

/**
 * Test for {@link FileSystemXmlApplicationContext}
 *
 * @author CodeingBoy
 * @version 1
 */
public class FileSystemApplicationContextTest {

    private final static String BEAN_ID_PET_STORE_SERVICE = "petStoreService";

    @Test
    public void getBeanTest() {
        ApplicationContext context = new FileSystemXmlApplicationContext("./src/test/resources/petstore-v1.xml");
        PetStoreService petStoreService = (PetStoreService) context.getBean(BEAN_ID_PET_STORE_SERVICE);
        Assert.assertNotNull(petStoreService);
    }

}