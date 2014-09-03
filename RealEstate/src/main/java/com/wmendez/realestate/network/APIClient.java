package com.wmendez.realestate.network;

import com.wmendez.realestate.models.Property;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;

public class APIClient {
    public static final String API_URL = "http://192.168.1.79:8000";


    public interface API {
        @GET("/api/propiedades/?format=json")
        List<Property> propertyList();

        @GET("/api/propiedades/?format=json")
        void propertyList(Callback<List<Property>> cb);
    }

}
