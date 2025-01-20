package xyz.hooy.myspringframework.core.io;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

public interface Resource {

    boolean exists();

    String getName();

    String getDescription();

    long contentLength() throws IOException;

    URI getURI() throws IOException;

    InputStream getInputStream() throws IOException;

    Resource createRelative(String relativePath) throws IOException;
}
