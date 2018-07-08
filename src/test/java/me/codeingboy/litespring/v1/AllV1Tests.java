package me.codeingboy.litespring.v1;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * Test suite for v1 tests
 *
 * @author CodeingBoy
 * @version 1
 */
@RunWith(Suite.class)
@SuiteClasses({
    ApplicationContextTest.class,
    DefaultBeanFactoryTest.class
})
public class AllV1Tests {

}
