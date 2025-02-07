package xyz.hooy.trace;

import org.springframework.beans.factory.InitializingBean;

public class Engine implements InitializingBean {

    private String type;

    public Engine(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Bean initialization action.");
    }
}