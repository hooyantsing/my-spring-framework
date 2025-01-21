package xyz.hooy.myspringframework.beans.factory;

import xyz.hooy.myspringframework.beans.BeanCreationException;
import xyz.hooy.myspringframework.beans.BeansException;
import xyz.hooy.myspringframework.beans.NoSuchBeanDefinitionException;
import xyz.hooy.myspringframework.beans.definition.BeanDefinition;
import xyz.hooy.myspringframework.convert.ConversionService;

import java.util.Objects;

public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements ConfigurableBeanFactory {

    private BeanFactory parentBeanFactory;

    private ConversionService conversionService;

    @Override
    public ConversionService getConversionService() {
        return conversionService;
    }

    @Override
    public void setConversionService(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @Override
    public BeanFactory getParentBeanFactory() {
        return parentBeanFactory;
    }

    @Override
    public boolean containsLocalBean(String name) {
        return containsSingleton(name) || containsBeanDefinition(name);
    }

    @Override
    public Object getBean(String name) throws BeansException {
        Object bean = getSingleton(name);
        if (Objects.nonNull(bean)) {
            return bean;
        }
        BeanDefinition beanDefinition = getBeanDefinition(name);
        return createBean(name, beanDefinition);
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> T getBean(Class<T> requiredType) throws BeansException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean containsBean(String name) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Class<?> getType(String name) throws NoSuchBeanDefinitionException {
        throw new UnsupportedOperationException();
    }

    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition) throws BeanCreationException;

    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    protected abstract boolean containsBeanDefinition(String beanName);

    public boolean isBeanNameInUse(String beanName) {
        return containsLocalBean(beanName);
    }
}
