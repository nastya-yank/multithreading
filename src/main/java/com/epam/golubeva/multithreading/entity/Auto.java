package com.epam.golubeva.multithreading.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Auto implements Runnable {
    private static final Logger logger = LogManager.getLogger();
    private static int counter = 0;
    private int id = counter++;
    private double area;
    private double weight;
    private AutoType type;
    private CoastType destinationPoint;
    private Lock locker;

    public Auto(double weight, double area, AutoType type, CoastType destinationPoint) {
        this.weight = weight;
        this.area=area;
        this.type = type;
        this.destinationPoint = destinationPoint;
        this.locker = new ReentrantLock();
    }

    @Override
    public void run() {
        Ferry.getInstance().getOnBard(this);
        logger.info(String.format(
                "Car: ["+this.toString()+"] is loaded on ferry "));

        this.locker.lock();
        logger.info(String.format(
                "Car: ["+this.toString()+"] reached destination"));
    }

    public double getWeight() {
        return this.weight;
    }

    public double getArea() {
        return area;
    }

    public CoastType getDestinationPoint() {
        return this.destinationPoint;
    }

    public int getSize() {
        return this.type.getOccupiedParkingPlaces();
    }

    public Lock getLock() {
        return this.locker;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = (int) (prime * result + area);
        result = (prime * result + (id ^ (id >>> 32)));
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        result = (int) (prime * result + weight);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Auto other = (Auto) obj;
        if (area != other.area)
            return false;
        if (id != other.id)
            return false;
        if (type != other.type)
            return false;
        if (weight != other.weight)
            return false;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Car [weight=");
        builder.append(weight);
        builder.append(", area=");
        builder.append(area);
        builder.append(", type=");
        builder.append(type);
        builder.append("]");
        return builder.toString();
    }
}
