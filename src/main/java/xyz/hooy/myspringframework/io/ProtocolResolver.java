package xyz.hooy.myspringframework.io;

@FunctionalInterface
public interface ProtocolResolver {

    Resource resolve(String location, ResourceLoader resourceLoader);
}
