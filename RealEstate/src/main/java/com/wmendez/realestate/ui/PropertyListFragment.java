package com.wmendez.realestate.ui;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.wmendez.realestate.R;
import com.wmendez.realestate.adapters.PropertyListAdapter;
import com.wmendez.realestate.models.Property;
import com.wmendez.realestate.network.APIClient;


import butterknife.ButterKnife;
import butterknife.InjectView;
import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class PropertyListFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private static final String TAG = PropertyListFragment.class.getCanonicalName();
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

        RequestInterceptor requestInterceptor = new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {
                request.addHeader("Authorization", "Token 95436bbf22d2f91a80e594cd2730e97629b378f4");
            }
        };

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(APIClient.API_URL)
                .setRequestInterceptor(requestInterceptor)
                .build();

        api = restAdapter.create(APIClient.API.class);
        adapter = new PropertyListAdapter(getActivity());
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Property property = adapter.getItem(i);
                Log.i(TAG, property.title);
                Intent intent = new Intent(getActivity(), PropertyDetailActivity.class);
                intent.putExtra("property", property);
                getActivity().startActivity(intent);
            }
        });

        swipeLayout.setColorSchemeColors(android.R.color.holo_blue_bright,
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
        api.propertyList(new Callback<APIClient.APIResponse<Property>>() {

            @Override
            public void success(APIClient.APIResponse apiResponse, Response response) {
                swipeLayout.setRefreshing(false);
                adapter.setPropertyList(apiResponse.results);

            }

            @Override
            public void failure(RetrofitError error) {
                swipeLayout.setRefreshing(false);
                error.printStackTrace();
            }
        });
    }
}
