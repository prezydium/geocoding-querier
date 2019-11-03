package com.prezydium;

import com.google.maps.model.GeocodingResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import static com.prezydium.Utils.buildAddress;

public class App {

    private static final Logger LOGGER = Logger.getLogger(App.class.toString());

    public static void main(String[] args) {
        validateInputParameters(args);
        String googleApiKey = args[0];
        String inputFilePath = args[1];
        List<String[]> inputPoints = new ArrayList<>();
        try {
            inputPoints = CsvProcessor.readAll(inputFilePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (inputPoints.isEmpty()) {
            LOGGER.severe("Empty input list from csv file");
            System.exit(1);
        }
        List<String[]> outputPoints = addGeolocationToPoints(googleApiKey, inputPoints);
        try {
            CsvProcessor.csvWriteAll(outputPoints, "resultpoints.csv");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static List<String[]> addGeolocationToPoints(String googleApiKey, List<String[]> inputPoints) {
        List<String[]> outputPoints = new ArrayList<>();
        QueryMapApi queryMapApi = new QueryMapApi(googleApiKey);
        inputPoints.forEach(csvRov -> {
            String address = buildAddress(csvRov);
            GeocodingResult geocodingResult = queryMapApi.getGeocodingResult(address);
            if (geocodingResult != null){
                String[] processedCsvRow = new String[]{
                        csvRov[1],
                        address,
                        csvRov[5],
                        String.valueOf(geocodingResult.geometry.location.lat),
                        String.valueOf(geocodingResult.geometry.location.lng)
                };
                outputPoints.add(processedCsvRow);
                LOGGER.info("Processed row: " + Arrays.toString(processedCsvRow));
            } else {
                LOGGER.warning("Empty query result of: " + Arrays.toString(csvRov));
            }
        });
        return outputPoints;
    }

    private static void validateInputParameters(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("Application should recive 2 input parameters: API key and input csv file path");
        }
    }
}
