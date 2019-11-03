package com.prezydium;

import com.google.maps.model.GeocodingResult;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        String googleApiKey = args[0];
        List<String[]> outputPoints = new ArrayList<>();
        List<String[]> inputPoints = new CsvProcessor().readAll();
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
        new CsvProcessor().csvWriterAll(outputPoints, Paths.get(
                ClassLoader.getSystemResource("resultpoints.csv").toURI()));
    }
}
