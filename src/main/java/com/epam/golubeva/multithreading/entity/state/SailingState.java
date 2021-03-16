package com.epam.golubeva.multithreading.entity.state;

import com.epam.golubeva.multithreading.service.FerryService;

import java.util.concurrent.TimeUnit;

public class SailingState extends StateFerry {

    @Override
    public void interpret(FerryService service) {
        logger.info("The ferry is sailing");
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.error("Thread cannot be sleeped throwing exception:", e);
        }
        ferry.changeState(new UnloadingState());
    }
}