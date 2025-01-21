package xyz.hooy.myspringframework.beans.definition;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * 存储元数据的实现
 */
public abstract class AttributeAccessorSupport implements AttributeAccessor {

    private final Map<String, Object> attributes = new LinkedHashMap<>();

    @Override
    public void setAttribute(String name, Object value) {
        if (Objects.isNull(name)) {
            throw new IllegalArgumentException("Name must not be null.");
        }
        if (Objects.nonNull(value)) {
            attributes.put(name, value);
        } else {
            removeAttribute(name); // 等价 attributes.put(name, null);
        }
    }

    @Override
    public Object getAttribute(String name) {
        if (Objects.isNull(name)) {
            throw new IllegalArgumentException("Name must not be null.");
        }
        return attributes.get(name);
    }

    @Override
    public Object removeAttribute(String name) {
        if (Objects.isNull(name)) {
            throw new IllegalArgumentException("Name must not be null.");
        }
        return attributes.remove(name);
    }

    @Override
    public boolean hasAttribute(String name) {
        if (Objects.isNull(name)) {
            throw new IllegalArgumentException("Name must not be null.");
        }
        return attributes.containsKey(name);
    }

    @Override
    public String[] attributeNames() {
        Set<String> attributeNames = attributes.keySet();
        return attributeNames.toArray(new String[0]);
    }

    protected void copyAttributesFrom(AttributeAccessor source) {
        if (Objects.isNull(source)) {
            throw new IllegalArgumentException("Source must not be null.");
        }
        for (String attributeName : source.attributeNames()) {
            Object attribute = source.getAttribute(attributeName);
            setAttribute(attributeName, attribute);
        }
    }
}
