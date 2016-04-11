package com.preetiharkanth.listview.extra.listviewdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Ajay Wisawe on 2/29/2016.
 */
public class ColorAdaptor extends ArrayAdapter<Color> {

    List<Color> mData;
    Context mContext;
    int mResource;
    /**
     * Constructor
     *
     * @param context  The current context.
     * @param resource The resource ID for a layout file containing a TextView to use when
     *                 instantiating views.
     * @param objects  The objects to represent in the ListView.
     */
    public ColorAdaptor(Context context, int resource, List<Color> objects) {
        super(context, resource, objects);
        this.mData = objects;
        this.mContext = context;
        this.mResource = resource;

    }

    /**
     * {@inheritDoc}
     *
     * @param position
     * @param convertView
     * @param parent
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(mResource,parent,false);
        }

        Color color = mData.get(position);
        TextView colorNameTextView = (TextView)convertView.findViewById(R.id.colorNameTextView);
        colorNameTextView.setText(color.colorName);
        TextView colorHexTextView = (TextView)convertView.findViewById(R.id.colorHexTextView);
        colorHexTextView.setText(color.colorHex);

        colorHexTextView.setTextColor(android.graphics.Color.parseColor(color.colorHex));

        if(position%2 == 0){
            convertView.setBackgroundColor(android.graphics.Color.WHITE);
        }else{
            convertView.setBackgroundColor(android.graphics.Color.RED);
        }

        return convertView;
    }
}
