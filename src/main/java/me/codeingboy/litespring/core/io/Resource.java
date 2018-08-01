package me.codeingboy.litespring.core.io;

import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * An abstraction of Resource
 *
 * @author CodeingBoy
 * @version 1
 */
public interface Resource {

    InputStream getInputStream() throws FileNotFoundException;

    String getDescription();

}
