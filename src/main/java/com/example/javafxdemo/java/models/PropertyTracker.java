package com.example.javafxdemo.java.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PropertyTracker {
    private Map<Property, List<Observer>> observersByProperty;
    private Map<Property, Boolean> occupancyByProperty;
    private Map<Observer, List<Property>> propertiesByObserver;

    public PropertyTracker() {
        this.observersByProperty = new HashMap<>();
        this.occupancyByProperty = new HashMap<>();
        this.propertiesByObserver = new HashMap<>();
    }

    public String setOccupancy(Property property, boolean isOccupied) {
        this.occupancyByProperty.put(property, isOccupied);
        return notifyObservers(property, isOccupied);
    }

    public void registerObserver(Property property, Observer observer) {
        List<Observer> observers = observersByProperty.getOrDefault(property, new ArrayList<>());
        observers.add(observer);
        observersByProperty.put(property, observers);

        List<Property> properties = propertiesByObserver.getOrDefault(observer, new ArrayList<>());
        properties.add(property);
        propertiesByObserver.put(observer, properties);
    }

    public void removeObserver(Property property, Observer observer) {
        List<Observer> observers = observersByProperty.getOrDefault(property, new ArrayList<>());
        observers.remove(observer);
        observersByProperty.put(property, observers);

        List<Property> properties = propertiesByObserver.getOrDefault(observer, new ArrayList<>());
        properties.remove(property);
        propertiesByObserver.put(observer, properties);
    }
    public String notifyObservers(Property property, boolean isOccupied) {
        List<Observer> observers = observersByProperty.getOrDefault(property, new ArrayList<>());
        for (Observer observer : observers) {
            observer.updateTenants(property, isOccupied);
        }
        return "notifying";
    }

    public void removeObserver(Observer observer) {
        List<Property> properties = propertiesByObserver.getOrDefault(observer, new ArrayList<>());
        for (Property property : properties) {
            removeObserver(property, observer);
        }
        propertiesByObserver.remove(observer);
    }
}