package xyz.hooy.myspringframework.beans.factory;

import xyz.hooy.myspringframework.beans.BeanDefinitionStoreException;
import xyz.hooy.myspringframework.beans.NoSuchBeanDefinitionException;
import xyz.hooy.myspringframework.beans.definition.BeanDefinition;

public interface BeanDefinitionRegistry {

    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) throws BeanDefinitionStoreException;

    void removeBeanDefinition(String beanName) throws NoSuchBeanDefinitionException;

    BeanDefinition getBeanDefinition(String beanName) throws NoSuchBeanDefinitionException;

    boolean containsBeanDefinition(String beanName);

    String[] getBeanDefinitionNames();

    int getBeanDefinitionCount();

    boolean isBeanNameInUse(String beanName);
}
