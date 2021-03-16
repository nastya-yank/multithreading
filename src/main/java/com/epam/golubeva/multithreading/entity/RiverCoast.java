package com.epam.golubeva.multithreading.entity;

import java.util.ArrayDeque;
import java.util.Queue;

public class RiverCoast {
    private Queue<Auto> autos;
    private CoastType coast;

    public RiverCoast(CoastType coast) {
        this.coast = coast;
        autos = new ArrayDeque<>();
    }

    public Queue<Auto> getCarList() {
        return this.autos;
    }

    public Auto getCar() {
        return autos.poll();
    }

    public void setCar(Auto auto) {
        autos.add(auto);

    }

    public CoastType getType() {
        return this.coast;
    }
}
