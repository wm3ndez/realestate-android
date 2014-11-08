package com.wmendez.realestate.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.wmendez.realestate.R;
import com.wmendez.realestate.models.Image;
import com.wmendez.realestate.utils.Pref;

import java.util.ArrayList;
import java.util.List;

public class ImagePagerAdapter extends PagerAdapter {

    private final List<Image> imageList;
    private final Context context;
    private ArrayList<View> reusable = new ArrayList<View>();

    public ImagePagerAdapter(Context context, List<Image> imageList) {
        this.context = context;
        this.imageList = imageList;
    }


    @Override
    public int getCount() {
        if (imageList == null)
            return 0;
        return imageList.size();
    }

    @Override
    public Object instantiateItem(ViewGroup parent, int position) {
        View view;
        ImageView imageView;
        if (reusable.size() > 0) {
            view = reusable.remove(0);
        } else {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.property_details_images, parent, false);
        }
        imageView = (ImageView) view.findViewById(R.id.property_details_image);
        String url = String.format("%s%s", Pref.getServerUrl(context), imageList.get(position).url);
        Picasso.with(context).load(url).into(imageView);
//        imageView.setOnClickListener(null);

        parent.addView(view);
        return (view);
    }

    @Override
    public void destroyItem(ViewGroup parent, int position, Object viewObject) {
        View view = (View) viewObject;
        parent.removeView(view);
        reusable.add(view);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == object);
    }

    @Override
    public int getItemPosition(Object object) {
        return (POSITION_NONE);
    }
}
