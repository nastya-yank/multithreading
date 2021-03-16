package com.epam.golubeva.multithreading.service;

import com.epam.golubeva.multithreading.entity.Auto;
import com.epam.golubeva.multithreading.entity.RiverCoast;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class FerryService {
    private static final Logger logger = LogManager.getLogger();
    private ExecutorService executor;

    public void loadCarsOnFerry(RiverCoast currentRiverCoast, int freeParkingPlaces, double freeCapacity) {
        logger.info(String.format("Loading ferry on %s coast %n Cars on %s coast: %s "
                , currentRiverCoast.getType(), currentRiverCoast.getType(), currentRiverCoast.getCarList()));
        int parkedCars = currentRiverCoast.getCarList().size();
        executor = Executors.newCachedThreadPool();
        Auto auto;
        for (int i = 0; i < parkedCars; i++) {
            auto = currentRiverCoast.getCar();
            if (!isFerryFull(freeParkingPlaces, freeCapacity, auto)) {
                acceptCarToParkOnFerry(executor, auto);
                freeCapacity -= auto.getWeight();
                freeParkingPlaces -= auto.getSize();
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    logger.error("Thread cannot be sleeped throwing exception:", e);
                }
            } else {
                currentRiverCoast.setCar(auto);
            }
        }
        logger.info("The ferry is full");
    }

    public void unloadCarsFromFerry(RiverCoast currentRiverCoast, Queue<Auto> loadedAutos) {
        logger.info(String.format("The ferry is unloading on %s coast", currentRiverCoast.getType()));
        while (!loadedAutos.isEmpty()) {
            unloadCarFromFerry(executor, loadedAutos);
        }
        logger.info("The ferry unloading finished");
    }

    private boolean isFerryFull(int freeParkingPlaces, double freeCapacity, Auto auto) {
        return !(freeCapacity >= auto.getWeight() && freeParkingPlaces >= auto.getSize());
    }

    private void acceptCarToParkOnFerry(ExecutorService executor, Auto auto) {
        executor.submit(auto);
        auto.getLock().lock();
    }

    private void unloadCarFromFerry(ExecutorService executor, Queue<Auto> loadedAutos) {
        Auto auto = loadedAutos.poll();
        auto.getLock().unlock();
        executor.shutdown();
    }
}
