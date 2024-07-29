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

public class PrimaryFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_primary, container, false);

        ListView listView = view.findViewById(R.id.listViewPrimary);
        List<String> primaryItems = Arrays.asList("Your fare of Rs. 30 has been deducted successfully. Your remaining balance is Rs. 120.", "Your MetroBus balance is low. Please recharge soon to continue using the service without interruptions. Your current balance is Rs. 10.", "Your MetroBus wallet has been recharged with Rs. 100. Your new balance is Rs. 150. Thank you for recharging!", "We were unable to deduct the fare of Rs. 30 due to insufficient balance. Please recharge your wallet and try again. Your current balance is Rs. 10.", "Your monthly MetroBus pass has been renewed successfully. You can now travel unlimited until July,1st,2024.");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), R.layout.list_item_primary, primaryItems);
        listView.setAdapter(adapter);
        return view;
    }
}
