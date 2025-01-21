package xyz.hooy.myspringframework.beans.definition;

import java.util.Objects;

public abstract class AbstractBeanDefinition extends BeanMetadataAttributeAccessor implements BeanDefinition {

    private Class<?> beanClass;

    private String scope = SCOPE_SINGLETON;

    @Override
    public void setBeanClass(Class<?> beanClass) {
        if (Objects.isNull(beanClass)) {
            throw new IllegalArgumentException("Bean class must not be null.");
        }
        this.beanClass = beanClass;
    }

    @Override
    public Class<?> getBeanClass() {
        if (hasBeanClass()) {
            return beanClass;
        } else {
            throw new IllegalStateException("No bean class specified on bean definition.");
        }
    }

    @Override
    public boolean isSingleton() {
        return SCOPE_SINGLETON.equals(scope);
    }

    public String getBeanClassName() {
        return hasBeanClass() ? beanClass.getName() : null;
    }

    public boolean hasBeanClass() {
        return Objects.nonNull(beanClass);
    }
}
