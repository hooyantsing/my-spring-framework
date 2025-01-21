package xyz.hooy.myspringframework.beans.definition;

import java.util.Objects;

/**
 * 存储定义键值对
 */
public class BeanMetadataAttribute {

    private final String name;
    private final Object value;

    public BeanMetadataAttribute(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BeanMetadataAttribute that = (BeanMetadataAttribute) o;
        if (!Objects.equals(name, that.name)) {
            return false;
        }
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }
}
