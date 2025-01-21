package xyz.hooy.myspringframework.beans.definition;

import java.util.Objects;

public class BeanMetadataAttributeAccessor extends AttributeAccessorSupport implements BeanMetadataElement {

    private Object source;

    public void setSource(Object source) {
        this.source = source;
    }

    public void addMetadataAttribute(BeanMetadataAttribute beanMetadataAttribute) {
        super.setAttribute(beanMetadataAttribute.getName(), beanMetadataAttribute);
    }

    public BeanMetadataAttribute getMetadataAttribute(String name) {
        return (BeanMetadataAttribute) super.getAttribute(name);
    }

    public BeanMetadataAttribute removeMetadataAttribute(String name) {
        return (BeanMetadataAttribute) super.removeAttribute(name);
    }

    @Override
    public void setAttribute(String name, Object value) {
        addMetadataAttribute(new BeanMetadataAttribute(name, value));
    }

    @Override
    public Object getAttribute(String name) {
        BeanMetadataAttribute beanMetadataAttribute = getMetadataAttribute(name);
        return Objects.nonNull(beanMetadataAttribute) ? beanMetadataAttribute.getValue() : null;
    }

    @Override
    public Object removeAttribute(String name) {
        BeanMetadataAttribute beanMetadataAttribute = removeMetadataAttribute(name);
        return Objects.nonNull(beanMetadataAttribute) ? beanMetadataAttribute.getValue() : null;
    }

    @Override
    public Object getSource() {
        return source;
    }
}
