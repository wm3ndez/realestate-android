package com.wmendez.realestate.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wmendez.realestate.R;
import com.wmendez.realestate.adapters.ImagePagerAdapter;
import com.wmendez.realestate.models.Property;

public class PropertyDetailFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String PROPERTY = "property";
    private Property property;


    public PropertyDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            property = getArguments().getParcelable(PROPERTY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_property_detail, container, false);
        ImagePagerAdapter adapter = new ImagePagerAdapter(getActivity(), property.images);
        ViewPager viewpager = (ViewPager) view.findViewById(R.id.property_images);
        viewpager.setAdapter(adapter);
        ((TextView) view.findViewById(R.id.property_title)).setText(property.title);

        return view;
    }

    public static Fragment newInstance(Property property) {
        PropertyDetailFragment fragment = new PropertyDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable(PROPERTY, property);
        fragment.setArguments(args);
        return fragment;
    }
}
