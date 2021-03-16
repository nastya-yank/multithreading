package com.epam.golubeva.multithreading.entity;

public enum AutoType {
    CAR(1),
    TRUCK(3);

    private int occupiedParkingPlaces;

    AutoType(int parkingPlace) {
        this.occupiedParkingPlaces = parkingPlace;
    }

    public int getOccupiedParkingPlaces() {
        return occupiedParkingPlaces;
    }
}
