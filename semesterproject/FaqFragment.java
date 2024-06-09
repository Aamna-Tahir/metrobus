package com.example.semesterproject;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FaqFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FaqFragment extends Fragment {
    private List<FaqItem> faqList;
    private RecyclerView recyclerView;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FaqFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FaqFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FaqFragment newInstance(String param1, String param2) {
        FaqFragment fragment = new FaqFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_faq, container, false);
        recyclerView = view.findViewById(R.id.recyclerview);
        initData();
        setRecyclerView();
        return view;
    }

    private void setRecyclerView() {
        FAQAdapter faqAdapter = new FAQAdapter(faqList);
        recyclerView.setAdapter(faqAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
    }

    private void initData() {
        faqList = new ArrayList<>();
        faqList.add(new FaqItem("How do I use the MetroBus app to pay for my ride?", "Open the MetroBus app, select the QR code option, and scan the QR code at the bus entrance. The fare will be automatically deducted from your wallet."));
        faqList.add(new FaqItem("Do I need to purchase a physical ticket after scanning the QR code?", "No, scanning the QR code is sufficient. The fare is deducted from your wallet, and you can directly board the bus."));
        faqList.add(new FaqItem("How can I add funds to my MetroBus wallet?", "Go to the 'MyBalance' section in the app and choose your preferred payment method to add funds to your wallet."));
        faqList.add(new FaqItem("What if my QR code scan fails?", "Ensure your phone has a stable internet connection and enough balance in your wallet. If the issue persists, contact our customer support through the app."));
        faqList.add(new FaqItem("How can I check my wallet balance?", "Open the 'MyBalance' section in the app to view your current balance."));
        faqList.add(new FaqItem("How can I check my transaction history?", "Open the 'Alerts' section in the app to view your transaction history."));
        faqList.add(new FaqItem("Is it safe to use QR codes for payment?", "Yes, the QR code payment system is secure and encrypted to protect your transaction information."));
        faqList.add(new FaqItem("What happens if I accidentally scan the QR code more than once?", "The app ensures that you are only charged once per ride. If you notice any discrepancies, please contact customer support."));
        faqList.add(new FaqItem("Can I use the MetroBus app without internet access?", "An internet connection is required to scan the QR code and process the payment. Ensure you have internet access before boarding the bus."));
        faqList.add(new FaqItem("How can I get a refund if there is an issue with my fare deduction?", "Contact our customer support through the app with details of the transaction. Refunds will be processed according to our refund policy."));
    }
}
