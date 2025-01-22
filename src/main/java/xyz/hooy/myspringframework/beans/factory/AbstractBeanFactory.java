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
        return doGetBean(name);
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
        return containsLocalBean(name) ||
                (Objects.nonNull(getParentBeanFactory()) && getParentBeanFactory().containsBean(name));
    }

    @Override
    public Class<?> getType(String name) throws NoSuchBeanDefinitionException {
        throw new UnsupportedOperationException();
    }

    protected Object doGetBean(String name) {
        Object bean = getSingleton(name);
        if (Objects.nonNull(bean)) { // 直接返回
            return bean;
        } else if (containsBeanDefinition(name)) { // 根据 BeanDefinition 创建 Bean
            BeanDefinition beanDefinition = getBeanDefinition(name);
            return createBean(name, beanDefinition);
        } else if (Objects.nonNull(getParentBeanFactory())) { // 委托给父级 BeanFactory
            return getParentBeanFactory().getBean(name);
        } else {
            throw new BeanCreationException("Not found " + name + "bean.");
        }
    }

    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition) throws BeanCreationException;

    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    /**
     * 仅检查当前 BeanFactory 是否包含给定名称的 BeanDefinition
     */
    protected abstract boolean containsBeanDefinition(String beanName);

    public boolean isBeanNameInUse(String beanName) {
        return containsLocalBean(beanName);
    }
}
