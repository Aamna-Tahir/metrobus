package com.example.semesterproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class RoutesAdapter extends ArrayAdapter<String> {

    private Context context;
    private ArrayList<String> stations;

    public RoutesAdapter(Context context, ArrayList<String> stations) {
        super(context, 0, stations);
        this.context = context;
        this.stations = stations;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_route, parent, false);
        }

        // Get the data item for this position
        String station = getItem(position);

        // Lookup view for data population
        TextView stationNameTextView = convertView.findViewById(R.id.stationNameTextView);

        // Populate the data into the template view using the data object
        stationNameTextView.setText(station);

        // Return the completed view to render on screen
        return convertView;
    }
}
