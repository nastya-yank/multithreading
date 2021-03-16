package com.epam.golubeva.multithreading.parser;

import com.epam.golubeva.multithreading.entity.AutoType;
import com.epam.golubeva.multithreading.entity.CoastType;

public class AutoParser {
    private static final String REGEX_DATA_TYPE = "(?<=[a-zA-Z][:])\\s+";
    private static final String SPLIT_SYMBOL = ";";
    private static final int CAR_TYPE_ID = 0;
    private static final int CAR_WEIGHT_ID = 1;
    private static final int CAR_DESTINATION_POINT_ID = 2;
    private static final int VALUE_ID = 1;
    private static final String TYPE_CAR = "Car";
    private static final String COAST_TYPE_EAST = "east";

    public AutoType parseType(String line) {
        String[] subLines = line.split(SPLIT_SYMBOL);
        String type = subLines[CAR_TYPE_ID].split(REGEX_DATA_TYPE)[VALUE_ID];
        if (type.equals(TYPE_CAR)) {
            return AutoType.CAR;
        } else {
            return AutoType.TRUCK;
        }
    }
    public double parseArea(String line) {
        double area;
        String[] subLines = line.split(SPLIT_SYMBOL);
        area = Double.valueOf(subLines[CAR_WEIGHT_ID].split(REGEX_DATA_TYPE)[VALUE_ID]);
        return area;
    }

    public double parseWeight(String line) {
        double weight;
        String[] subLines = line.split(SPLIT_SYMBOL);
        weight = Double.valueOf(subLines[CAR_WEIGHT_ID].split(REGEX_DATA_TYPE)[VALUE_ID]);
        return weight;
    }

    public CoastType destinationPoint(String line) {
        String[] subLines = line.split(SPLIT_SYMBOL);
        String type = subLines[CAR_DESTINATION_POINT_ID].split(REGEX_DATA_TYPE)[VALUE_ID];
        if (type.equals(COAST_TYPE_EAST)) {
            return CoastType.EAST;
        } else {
            return CoastType.WEST;
        }
    }
}
