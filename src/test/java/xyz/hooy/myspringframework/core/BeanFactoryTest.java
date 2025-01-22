package xyz.hooy.myspringframework.core;

import org.junit.jupiter.api.Test;
import xyz.hooy.myspringframework.beans.definition.BeanDefinition;
import xyz.hooy.myspringframework.beans.definition.GenericBeanDefinition;
import xyz.hooy.myspringframework.beans.factory.DefaultListableBeanFactory;

public class BeanFactoryTest {

    @Test
    void test() {
        DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();
        BeanDefinition beanDefinition = new GenericBeanDefinition();
        beanDefinition.setBeanClass(HelloService.class);
        defaultListableBeanFactory.registerBeanDefinition("helloService", beanDefinition);

        HelloService helloService = (HelloService) defaultListableBeanFactory.getBean("helloService");
        helloService.say();
    }

    public static class HelloService {

        public void say() {
            System.out.println("Hello");
        }
    }
}
