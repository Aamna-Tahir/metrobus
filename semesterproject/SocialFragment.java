// SocialFragment.java
package com.example.semesterproject;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Arrays;
import java.util.List;

public class SocialFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_social, container, false);

        ListView listView = view.findViewById(R.id.listViewSocial);
        List<String> socialItems = Arrays.asList("Due to maintenance work, the MetroBus service will be temporarily suspended between 6th Road and Khatam-e-Nabuwat. We apologize for the inconvenience.", "Please note that due to ongoing construction, the MetroBus will not stop on Faizabad. Kindly plan your journey accordingly.", "We are excited to announce the opening of a new MetroBus station at Islamabad. The new station will be operational from July 1st,2024.", "Please be informed that the MetroBus fares have been revised. The new fare is Rs.30/-. Thank you for your understanding.", "Please be informed that the Metro bus service will remain closed on June 9th,2024 due to security concerns related to an ongoing match. Your safety is our top priority.");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), R.layout.list_item_social, socialItems);
        listView.setAdapter(adapter);
        return view;
    }
}
