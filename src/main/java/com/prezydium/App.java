package com.prezydium;

import com.google.maps.model.GeocodingResult;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        validateInputParameters(args);

        String googleApiKey = args[0];
        String inputFilePath = args[1];
        List<String[]> outputPoints = new ArrayList<>();
        List<String[]> inputPoints = CsvProcessor.readAll(inputFilePath);
        QueryMapApi queryMapApi = new QueryMapApi(googleApiKey);
        inputPoints.forEach(point -> {
            String address = point[3] + " " + point[4] + " ," + point[2];
            GeocodingResult geocodingResult = queryMapApi.getGeocodingResult(address);
            outputPoints.add(new String[]{
                    point[1],
                    address,
                    point[5],
                    String.valueOf(geocodingResult.geometry.location.lat),
                    String.valueOf(geocodingResult.geometry.location.lng)
            });
        });
        CsvProcessor.csvWriteAll(outputPoints, "resultpoints.csv");
    }

    private static void validateInputParameters(String[] args) {
        if (args.length != 2) {
            throw  new IllegalArgumentException("Application should recive 2 input parameters: API key and input csv file path");
        }
    }
}
