package xyz.hooy.myspringframework.core.io;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

public class DefaultResourceLoader implements ResourceLoader {

    public static final String CLASSPATH_URL_PREFIX = "classpath:";

    public static final String FILE_URL_PREFIX = "file:";

    private final Set<ProtocolResolver> protocolResolvers = new LinkedHashSet<>(4);

    // 加载 SPI 得到的解析类
    public void addProtocolResolver(ProtocolResolver resolver) {
        this.protocolResolvers.add(resolver);
    }

    @Override
    public Resource getResource(String location) {
        // 优先使用 SPI 扩展
        for (ProtocolResolver protocolResolver : protocolResolvers) {
            Resource resource = protocolResolver.resolve(location, this);
            if (Objects.nonNull(resource)) {
                return resource;
            }
        }
        // 默认处理
        if (location.startsWith("/") || location.startsWith(CLASSPATH_URL_PREFIX)) {
            return new ClassPathResource(location);
        } else if (location.startsWith(FILE_URL_PREFIX)) {
            return new FileSystemResource(location);
        }
        throw new UnsupportedOperationException();
    }
}
