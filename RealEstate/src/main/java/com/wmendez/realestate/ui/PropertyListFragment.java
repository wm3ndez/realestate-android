package com.wmendez.realestate.ui;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.wmendez.realestate.R;
import com.wmendez.realestate.adapters.PropertyListAdapter;
import com.wmendez.realestate.network.APIClient;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class PropertyListFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private PropertyListAdapter adapter;
    @InjectView(R.id.grid_view)
    GridView gridView;
    @InjectView(R.id.swipe_container)
    SwipeRefreshLayout swipeLayout;
    private APIClient.API api;

    public PropertyListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_main, container, false);
        ButterKnife.inject(this, view);

        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(APIClient.API_URL).build();
        api = restAdapter.create(APIClient.API.class);
        adapter = new PropertyListAdapter(getActivity());
        gridView.setAdapter(adapter);

        swipeLayout.setColorScheme(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        swipeLayout.setOnRefreshListener(this);
        swipeLayout.setRefreshing(true);
        onRefresh();

        return view;
    }


    @Override
    public void onRefresh() {
        api.propertyList(new Callback<List<APIClient.Property>>() {


            @Override
            public void success(List<APIClient.Property> properties, Response response) {
                swipeLayout.setRefreshing(false);
                adapter.setPropertyList(properties);
            }

            @Override
            public void failure(RetrofitError error) {
                swipeLayout.setRefreshing(false);
                error.printStackTrace();
            }
        });
    }
}
