package xyz.hooy.myspringframework.io;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileSystemResource implements Resource {

    private final Path path;

    public FileSystemResource(String pathString) {
        this(Paths.get(pathString));
    }

    public FileSystemResource(Path path) {
        this.path = path;
    }

    @Override
    public boolean exists() {
        return Files.exists(path);
    }

    @Override
    public String getName() {
        return path.getFileName().toString();
    }

    @Override
    public String getDescription() {
        return "file system io [" + getAbsolutePath() + "]";
    }

    @Override
    public long contentLength() throws IOException {
        return Files.size(path);
    }

    @Override
    public URI getURI() {
        return path.toUri();
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return Files.newInputStream(path);
    }

    @Override
    public Resource createRelative(String relativePath) {
        Path newPath = getAbsolutePath().resolve(relativePath).normalize();
        return new FileSystemResource(newPath);
    }

    public Path getAbsolutePath() {
        return path.toAbsolutePath();
    }
}
