package me.codeingboy.litespring;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * Test suite for all tests
 *
 * @author CodeingBoy
 * @version 1
 */
@RunWith(Suite.class)
@SuiteClasses({
        ClassPathApplicationContextTest.class,
        FileSystemApplicationContextTest.class,
        DefaultBeanFactoryTest.class,
        ResourceTest.class
})
public class AllTests {

}
