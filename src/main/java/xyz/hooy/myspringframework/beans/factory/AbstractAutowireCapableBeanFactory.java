package xyz.hooy.myspringframework.beans.factory;

import xyz.hooy.myspringframework.beans.BeanCreationException;
import xyz.hooy.myspringframework.beans.definition.BeanDefinition;

public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition) throws BeanCreationException {
        Object beanObject = doCreateBean(beanName, beanDefinition);
        registerSingleton(beanName, beanObject);
        return beanObject;
    }

    protected Object doCreateBean(String beanName, BeanDefinition beanDefinition) {
        Class<?> beanClass = beanDefinition.getBeanClass();
        try {
            return beanClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new BeanCreationException("Initialization of bean failed", e);
        }
    }
}
