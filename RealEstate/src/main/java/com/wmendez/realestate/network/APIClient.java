package com.wmendez.realestate.network;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;

public class APIClient {
    public static final String API_URL = "http://192.168.1.79:8000";

    public static class Image {
        public String url;
        public int order;
        public String title;
    }

    public static class Property {
        public int id;
        public String title;
        public String absolute_url;
        public String description;
        public String address;
        public String type;
        public String offer;
        public String created_at;
        public int baths;
        public int beds;
        public float price;
        public List<Image> images;

    }


    public interface API {
        @GET("/api/propiedades/?format=json")
        List<Property> propertyList();

        @GET("/api/propiedades/?format=json")
        void propertyList(Callback<List<Property>> cb);
    }

}
