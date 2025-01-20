package xyz.hooy.myspringframework.beans.factory;

import xyz.hooy.myspringframework.beans.BeansException;
import xyz.hooy.myspringframework.beans.DefaultSingletonBeanRegistry;
import xyz.hooy.myspringframework.beans.NoSuchBeanDefinitionException;
import xyz.hooy.myspringframework.core.convert.ConversionService;

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
        return false;
    }

    @Override
    public Object getBean(String name) throws BeansException {
        return null;
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return null;
    }

    @Override
    public <T> T getBean(Class<T> requiredType) throws BeansException {
        return null;
    }

    @Override
    public boolean containsBean(String name) {
        return false;
    }

    @Override
    public Class<?> getType(String name) throws NoSuchBeanDefinitionException {
        return null;
    }
}
