package com.epam.golubeva.multithreading.main;

import com.epam.golubeva.multithreading.entity.Auto;
import com.epam.golubeva.multithreading.entity.CoastType;
import com.epam.golubeva.multithreading.entity.Ferry;
import com.epam.golubeva.multithreading.entity.RiverCoast;
import com.epam.golubeva.multithreading.factory.AutoFactory;
import com.epam.golubeva.multithreading.reader.AutoReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        RiverCoast eastCoast = new RiverCoast(CoastType.EAST);
        RiverCoast westCoast = new RiverCoast(CoastType.WEST);
        Ferry.getInstance().setEastRiverCoast(eastCoast);
        Ferry.getInstance().setWestRiverCoast(westCoast);
        AutoReader reader = new AutoReader();
        AutoFactory factory = AutoFactory.getInstance();
        List<String> cars = reader.readData();
        for (String line : cars) {
            if (!line.isBlank()) {
                Auto auto = factory.createCar(line);
                if (CoastType.EAST.equals(auto.getDestinationPoint())) {
                    eastCoast.setCar(auto);
                    logger.info(String.format("%s is parked on %s coast", auto.toString(), eastCoast.getType()));
                } else {
                    westCoast.setCar(auto);
                    logger.info(String.format("%s is parked on %s coast", auto.toString(), westCoast.getType()));
                }
            }
        }
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(Ferry.getInstance());
        executor.shutdown();
    }
}
