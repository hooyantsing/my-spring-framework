package xyz.hooy.myspringframework.core.io;

@FunctionalInterface
public interface ProtocolResolver {

    Resource resolve(String location, ResourceLoader resourceLoader);
}
