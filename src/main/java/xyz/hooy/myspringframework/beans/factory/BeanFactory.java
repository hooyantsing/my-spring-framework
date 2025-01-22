package xyz.hooy.myspringframework.beans.factory;

import xyz.hooy.myspringframework.beans.BeansException;
import xyz.hooy.myspringframework.beans.NoSuchBeanDefinitionException;

public interface BeanFactory {

    /**
     * 如果当前 BeanFactory 不存在指定名称的 Bean，并且存在父级 BeanFactory，则继续向父级询问。
     */
    Object getBean(String name) throws BeansException;

    <T> T getBean(String name, Class<T> requiredType) throws BeansException;

    <T> T getBean(Class<T> requiredType) throws BeansException;

    /**
     * 如果当前 BeanFactory 不存在指定名称的 Bean，并且存在父级 BeanFactory，则继续向父级询问。
     */
    boolean containsBean(String name);

    Class<?> getType(String name) throws NoSuchBeanDefinitionException;
}
