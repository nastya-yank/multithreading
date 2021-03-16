package com.epam.golubeva.multithreading.entity.state;

import com.epam.golubeva.multithreading.service.FerryService;

public class LoadingState extends StateFerry {

    @Override
    public void interpret(FerryService service) {
        service.loadCarsOnFerry(ferry.currentCoast(), ferry.MAX_PARKING_PLACES, ferry.MAX_CARRYING_CAPACITY);
        ferry.changeState(new SailingState());
    }
}