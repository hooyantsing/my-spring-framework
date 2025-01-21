package xyz.hooy.myspringframework.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class ClassPathResource implements Resource {

    private final String path;

    public ClassPathResource(String path) {
        if (path.startsWith("/")) {
            path = path.substring(1);
        }
        this.path = path;
    }

    @Override
    public boolean exists() {
        return Objects.nonNull(resolveURL());
    }

    @Override
    public String getName() {
        return Paths.get(path).getFileName().toString();
    }

    @Override
    public String getDescription() {
        return "class path io [" + path + "]";
    }

    @Override
    public long contentLength() throws IOException {
        File file = new File(getURI().getSchemeSpecificPart());
        long length = file.length();
        if (length == 0L && !file.exists()) {
            throw new FileNotFoundException(getDescription() + " cannot be resolved in the file system for checking its content length.");
        }
        return length;
    }

    @Override
    public URI getURI() throws IOException {
        URL url = resolveURL();
        if (Objects.isNull(url)) {
            throw new FileNotFoundException(getDescription() + " cannot be resolved to URL because it does not exist.");
        }
        String uriString = url.toString().replace(" ", "%20");
        try {
            return new URI(uriString);
        } catch (URISyntaxException e) {
            throw new IOException(e);
        }
    }

    @Override
    public InputStream getInputStream() throws IOException {
        InputStream inputStream = getClass().getResourceAsStream(path);
        if (Objects.isNull(inputStream)) {
            throw new FileNotFoundException(getDescription() + " cannot be opened because it does not exist.");
        }
        return inputStream;
    }

    @Override
    public Resource createRelative(String relativePath) throws IOException {
        Path newPath = Paths.get(path).resolve(relativePath).normalize();
        return new ClassPathResource(newPath.toString());
    }

    protected URL resolveURL() {
        try {
            return getClass().getResource(path);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
