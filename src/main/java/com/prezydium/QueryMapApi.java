package com.prezydium;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;

import java.io.IOException;

public class QueryMapApi {

    private final GeoApiContext context;

    public QueryMapApi(String apiKey) {
        this.context = new GeoApiContext.Builder()
                .apiKey(apiKey)
                .build();
    }

    public GeocodingResult getGeocodingResult(String address) {

        GeocodingResult[] results = null;
        try {
            results = GeocodingApi.geocode(context,
                    address).await();
        } catch (ApiException | InterruptedException | IOException e) {
            e.printStackTrace();
        }
        return results != null ? results[0] : null;
    }
}
