package com.epam.golubeva.multithreading.factory;

import com.epam.golubeva.multithreading.entity.Auto;
import com.epam.golubeva.multithreading.entity.AutoType;
import com.epam.golubeva.multithreading.entity.CoastType;
import com.epam.golubeva.multithreading.parser.AutoParser;

public class AutoFactory {
    private static AutoFactory instance;

    private AutoFactory() {
    }

    public static AutoFactory getInstance() {
        if (instance == null) {
            instance = new AutoFactory();
        }
        return instance;
    }

    public Auto createCar(String line) {
        AutoParser parser = new AutoParser();
        double area = parser.parseArea(line);
        double weight = parser.parseWeight(line);
        AutoType type = parser.parseType(line);
        CoastType destinationPoint = parser.destinationPoint(line);
        return new Auto(area, weight, type, destinationPoint);
    }
}