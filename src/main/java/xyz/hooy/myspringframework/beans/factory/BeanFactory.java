package xyz.hooy.myspringframework.beans.factory;

import xyz.hooy.myspringframework.beans.BeansException;
import xyz.hooy.myspringframework.beans.NoSuchBeanDefinitionException;

public interface BeanFactory {

    Object getBean(String name) throws BeansException;

    <T> T getBean(String name, Class<T> requiredType) throws BeansException;

    <T> T getBean(Class<T> requiredType) throws BeansException;

    boolean containsBean(String name);

    Class<?> getType(String name) throws NoSuchBeanDefinitionException;
}
