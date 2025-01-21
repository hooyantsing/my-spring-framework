package xyz.hooy.myspringframework.beans.definition;

public interface BeanDefinition extends AttributeAccessor, BeanMetadataElement {

    String SCOPE_SINGLETON = "singleton";
    String SCOPE_PROTOTYPE = "prototype";

    void setBeanClass(Class<?> beanClass);

    Class<?> getBeanClass();

    boolean isSingleton();
}
