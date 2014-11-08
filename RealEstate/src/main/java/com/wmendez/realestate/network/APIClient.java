package com.wmendez.realestate.network;

import com.wmendez.realestate.models.Property;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;

public class APIClient {
    public static final String API_URL = "http://192.168.1.84:8000";

    public class APIResponse<T> {
        public int count;
        public String next;
        public String previous;
        public List<T> results;
    }


    public interface API {
        @GET("/api/listings/?format=json")
        void propertyList(Callback<APIResponse<Property>> cb);
    }

}
