package xyz.hooy.myspringframework.beans;

public class NoSuchBeanDefinitionException extends BeansException {

    private final String beanName;
    private final Class<?> beanType;

    public NoSuchBeanDefinitionException(String name) {
        super("No bean named '" + name + "' available");
        this.beanName = name;
        this.beanType = null;
    }

    public NoSuchBeanDefinitionException(String name, String message) {
        super("No bean named '" + name + "' available: " + message);
        this.beanName = name;
        this.beanType = null;
    }

    public NoSuchBeanDefinitionException(Class<?> beanType) {
        super("No qualifying bean of type '" + beanType.getSimpleName() + "' available");
        this.beanName = null;
        this.beanType = beanType;
    }

    public NoSuchBeanDefinitionException(Class<?> beanType, String message) {
        super("No qualifying bean of type '" + beanType.getSimpleName() + "' available: " + message);
        this.beanName = null;
        this.beanType = beanType;
    }

    public String getBeanName() {
        return beanName;
    }

    public Class<?> getBeanType() {
        return beanType;
    }
}
