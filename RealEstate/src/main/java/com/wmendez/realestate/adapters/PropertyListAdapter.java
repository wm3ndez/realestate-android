package com.wmendez.realestate.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.wmendez.realestate.R;
import com.wmendez.realestate.models.Image;
import com.wmendez.realestate.models.Property;
import com.wmendez.realestate.network.APIClient;
import com.wmendez.realestate.utils.Formatter;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class PropertyListAdapter extends BaseAdapter {

    private Context mContext;
    List<Property> propertyList;
    private LayoutInflater mInflater;

    public PropertyListAdapter(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
    }


    @Override
    public int getCount() {
        if (propertyList == null)
            return 0;
        return propertyList.size();
    }

    @Override
    public Property getItem(int position) {
        if (propertyList == null)
            return null;
        return propertyList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view != null) {
            holder = (ViewHolder) view.getTag();
        } else {
            view = mInflater.inflate(R.layout.property_list_item, parent, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }

        Property property = getItem(position);
        if (property != null) {
            if (property.images.size() > 0) {
                Image image = property.images.get(0);
                String url = String.format("%s%s", APIClient.API_URL, image.url);
                Picasso.with(mContext).load(url).into(holder.image);
            }

            holder.title.setText(property.title);
            holder.address.setText(property.address);
            holder.price.setText(Formatter.currency(property.price));
            holder.beds.setText("" + property.beds);
            holder.baths.setText("" + property.baths);

        }

        return view;
    }


    public void setPropertyList(List<Property> properties) {
        propertyList = properties;
        notifyDataSetChanged();
    }

    static class ViewHolder {
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

        public ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }

}
