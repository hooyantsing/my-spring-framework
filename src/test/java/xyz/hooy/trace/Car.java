package xyz.hooy.trace;

public class Car {

    private Engine engine;

    public void displayEngineType() {
        System.out.println("This car has an " + engine.getType() + " engine.");
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }
}