package com.prezydium;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;

import java.io.IOException;
import java.util.logging.Logger;

public class QueryMapApi {

    private static final Logger LOGGER = Logger.getLogger(QueryMapApi.class.toString());
    private static final int MINIMAL_ADDRESS_LENGTH = 6;

    private final GeoApiContext context;

    public QueryMapApi(String apiKey) {
        this.context = new GeoApiContext.Builder()
                .apiKey(apiKey)
                .build();
    }

    public GeocodingResult getGeocodingResult(String address) {
        if (address.length() < MINIMAL_ADDRESS_LENGTH){
            LOGGER.warning("Address is too short: " + address);
            return null;
        }
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
