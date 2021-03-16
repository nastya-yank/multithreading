package com.epam.golubeva.multithreading.entity.state;

import com.epam.golubeva.multithreading.entity.Ferry;
import com.epam.golubeva.multithreading.service.FerryService;

public class UnloadingState extends StateFerry {

    @Override
    public void interpret(FerryService service) {
        Ferry.getInstance().changeCoast();
        logger.info(String.format("The ferry is reached %s coast", ferry.currentCoast().getType()));
        service.unloadCarsFromFerry(ferry.currentCoast(), ferry.getLoadedCars());
        ferry.changeState(new LoadingState());
    }
}
