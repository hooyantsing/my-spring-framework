package xyz.hooy.trace;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

public class SpringFrameworkTest {

    @Test
    void beanFactory(){
        XmlBeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("application-context.xml"));
        // beanFactory.ignoreDependencyType(Engine.class);
        Car car = (Car) beanFactory.getBean("car");
        car.displayEngineType();
    }

    @Test
    void applicationContext(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("application-context.xml");
        Car car = (Car) applicationContext.getBean("car");
        car.displayEngineType();
    }
}
