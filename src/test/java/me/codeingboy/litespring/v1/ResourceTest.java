package me.codeingboy.litespring.v1;

import me.codeingboy.litespring.core.io.ClasspathResource;
import me.codeingboy.litespring.core.io.FileSystemResource;
import me.codeingboy.litespring.core.io.Resource;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * Test for {@link me.codeingboy.litespring.core.io.Resource}
 *
 * @author CodeingBoy
 * @version 1
 * @see me.codeingboy.litespring.core.io.Resource
 */
public class ResourceTest {

    @Test
    public void classpathResourceTest() {
        Resource resource = new ClasspathResource("petstore-v1.xml");
        try (InputStream inputStream = resource.getInputStream()) {
            Assert.assertNotNull("input stream should be not null", inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void fileSystemResourceTest() {
        Resource resource = new FileSystemResource("./src/test/resources/petstore-v1.xml");
        try (InputStream inputStream = resource.getInputStream()) {
            Assert.assertNotNull("input stream should be not null", inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }
}