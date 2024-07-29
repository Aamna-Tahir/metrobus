package com.example.semesterproject;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class RoutesFragment extends Fragment {

    private ListView listView;
    private ArrayList<String> stationNames;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_routes, container, false);
        listView = view.findViewById(R.id.routesListView);

        // List of station names
        stationNames = new ArrayList<>(Arrays.asList("Saddar", "Marrir Chowk", "Liaqat Bhag", "Committee Chowk", "Waris Khan",
                "Chandni Chowk", "Rehmanabad", "6th Road", "Khatam-e-Nabuwat", "Faizabad", "I.J.P", "Potohar",
                "Khayaban-e-Johar", "Faiz Ahmed Faiz", "Kashmir Highway", "Chaman","Ibn-e-Sina","Katchery","PIMS","Stock Exchange","7th Avenue","Shaheed-e-Millat","Parade Ground","Pak Secretariat"));

        // Setting up the adapter
        RoutesAdapter adapter = new RoutesAdapter(getActivity(), stationNames);
        listView.setAdapter(adapter);

        return view;
    }
}
