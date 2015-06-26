package com.wmendez.realestate.adapters;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.wmendez.realestate.R;
import com.wmendez.realestate.models.Image;
import com.wmendez.realestate.models.Property;
import com.wmendez.realestate.utils.Formatter;
import com.wmendez.realestate.utils.Pref;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class PropertyListAdapter extends RecyclerView.Adapter<PropertyListAdapter.ViewHolder> {

    private final Context mContext;
    private List<Property> properties;
    private int rowLayout;

    public PropertyListAdapter(Context context, List<Property> properties, int rowLayout) {
        this.properties = properties;
        this.rowLayout = rowLayout;
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(rowLayout, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int i) {
        Property property = properties.get(i);
        if (property != null) {
            if (property.images.size() > 0) {
                Image image = property.images.get(0);
                String url = String.format("%s%s", Pref.getServerUrl(mContext), image.url);
                Picasso.with(mContext).load(url).into(viewHolder.image, new Callback() {
                    @Override
                    public void onSuccess() {
                        /*
                        Palette palette = Palette.generate(((BitmapDrawable) viewHolder.image.getDrawable())
                                .getBitmap());
                        Palette.Swatch mutedSwatch = palette.getMutedSwatch();
                        try {
                            viewHolder.info.setBackgroundColor(mutedSwatch.getRgb());
                            viewHolder.title.setTextColor(mutedSwatch.getTitleTextColor());
                            viewHolder.beds.setTextColor(mutedSwatch.getTitleTextColor());
                            viewHolder.baths.setTextColor(mutedSwatch.getTitleTextColor());
                            viewHolder.address.setTextColor(mutedSwatch.getTitleTextColor());
                            viewHolder.price.setTextColor(mutedSwatch.getTitleTextColor());
                        } catch (NullPointerException ex) {
                            ex.printStackTrace();
                        }
                        */

                    }

                    @Override
                    public void onError() {
                        viewHolder.info.setBackgroundColor(mContext.getResources().getColor(R.color.primary));
                    }
                });
            }

            viewHolder.title.setText(property.title);
            viewHolder.address.setText(property.address);
            viewHolder.price.setText(Formatter.moneyFormat(property.price, false));
            viewHolder.beds.setText("" + property.beds);
            viewHolder.baths.setText("" + property.baths);

        }
    }

    @Override
    public int getItemCount() {
        return properties == null ? 0 : properties.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.property_info)
        View info;
        @InjectView(R.id.title)
        TextView title;
        @InjectView(R.id.picture)
        ImageView image;
        @InjectView(R.id.address)
        TextView address;
        @InjectView(R.id.price)
        TextView price;
        @InjectView(R.id.beds)
        TextView beds;
        @InjectView(R.id.baths)
        TextView baths;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }

    }
}
