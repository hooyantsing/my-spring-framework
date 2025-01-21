package xyz.hooy.myspringframework.beans.factory;

import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    private final Map<String, Object> singletonObjects = new ConcurrentHashMap<>(256);

    @Override
    public void registerSingleton(String beanName, Object singletonObject) {
        if (Objects.isNull(beanName)) {
            throw new IllegalArgumentException("Bean name must not be null.");
        }
        if (Objects.isNull(singletonObject)) {
            throw new IllegalArgumentException("Singleton object must not be null.");
        }
        Object oldSingletonObject = singletonObjects.get(beanName);
        if (Objects.nonNull(oldSingletonObject)) {
            throw new IllegalStateException("Could not register object [" + singletonObject +
                    "] under bean name '" + beanName + "': there is already object [" + oldSingletonObject + "] bound.");
        }
        singletonObjects.put(beanName, singletonObject);
    }

    @Override
    public Object getSingleton(String beanName) {
        return singletonObjects.get(beanName);
    }

    @Override
    public boolean containsSingleton(String beanName) {
        return singletonObjects.containsKey(beanName);
    }

    @Override
    public String[] getSingletonNames() {
        Set<String> singletonNames = singletonObjects.keySet();
        return singletonNames.toArray(new String[0]);
    }

    @Override
    public int getSingletonCount() {
        return getSingletonNames().length;
    }
}
