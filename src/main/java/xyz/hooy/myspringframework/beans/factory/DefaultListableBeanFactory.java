package xyz.hooy.myspringframework.beans.factory;

import org.apache.commons.lang3.StringUtils;
import xyz.hooy.myspringframework.beans.BeanDefinitionStoreException;
import xyz.hooy.myspringframework.beans.NoSuchBeanDefinitionException;
import xyz.hooy.myspringframework.beans.definition.BeanDefinition;

import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements BeanDefinitionRegistry {

    private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>(256);

    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) throws BeanDefinitionStoreException {
        if (StringUtils.isBlank(beanName)) {
            throw new IllegalArgumentException("Bean name must not be empty.");
        }
        if (Objects.isNull(beanDefinition)) {
            throw new IllegalArgumentException("BeanDefinition must not be null.");
        }
        beanDefinitionMap.put(beanName, beanDefinition);
    }

    @Override
    public void removeBeanDefinition(String beanName) throws NoSuchBeanDefinitionException {
        if (StringUtils.isBlank(beanName)) {
            throw new IllegalArgumentException("'beanName' must not be empty.");
        }
        BeanDefinition beanDefinition = beanDefinitionMap.remove(beanName);
        if (Objects.isNull(beanDefinition)) {
            throw new NoSuchBeanDefinitionException(beanName);
        }
    }

    @Override
    public BeanDefinition getBeanDefinition(String beanName) throws NoSuchBeanDefinitionException {
        BeanDefinition beanDefinition = this.beanDefinitionMap.get(beanName);
        if (Objects.isNull(beanDefinition)) {
            throw new org.springframework.beans.factory.NoSuchBeanDefinitionException(beanName);
        }
        return beanDefinition;
    }

    @Override
    public boolean containsBeanDefinition(String beanName) {
        return beanDefinitionMap.containsKey(beanName);
    }

    @Override
    public String[] getBeanDefinitionNames() {
        Set<String> beanDefinitionNames = beanDefinitionMap.keySet();
        return beanDefinitionNames.toArray(new String[0]);
    }

    @Override
    public int getBeanDefinitionCount() {
        return beanDefinitionMap.size();
    }
}
