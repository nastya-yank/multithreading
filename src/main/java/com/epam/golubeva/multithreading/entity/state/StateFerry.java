package com.epam.golubeva.multithreading.entity.state;

import com.epam.golubeva.multithreading.entity.Ferry;
import com.epam.golubeva.multithreading.service.FerryService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class StateFerry {
    protected static final Logger logger = LogManager.getLogger();

    protected Ferry ferry;

    StateFerry() {
        this.ferry = Ferry.getInstance();
    }

    public abstract void interpret(FerryService service);
}
