package com.example.semesterproject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HelpFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HelpFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HelpFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HelpFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HelpFragment newInstance(String param1, String param2) {
        HelpFragment fragment = new HelpFragment();
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
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_help2, container, false);
        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Find and initialize your views here
        LinearLayout faq = view.findViewById(R.id.faq);
        faq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFaqFragment();
            }
        });
        LinearLayout contact = view.findViewById(R.id.contact);
        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showContactDialog();
            }
        });


        LinearLayout privacypolicy = view.findViewById(R.id.privacy);
        privacypolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPrivacyPolicyDialog();
            }
        });
        // Find the FAQ card LinearLayout
        LinearLayout faqCard = view.findViewById(R.id.faqs);

        // Set an OnClickListener on the FAQ card
        faqCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Call the showTermsDialog method when the FAQ card is clicked
                showTermsDialog();
            }
        });
    }

    private void showTermsDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Terms and Conditions")
                .setMessage("These outline the rules and regulations for the use of the Metro Bus Service mobile application.\n" + "\n" + "License:\n"+
                        "Unless otherwise stated, Metro Bus Service and/or its licensors own the intellectual property rights for all material on the Metro Bus Service app. All intellectual property rights are reserved. You may access this from the Metro Bus Service app for your own personal use subjected to restrictions set in these terms and conditions.\n" +
                        "\n" +
                        "Restrictions:\n" +
                        "- You are specifically restricted from all of the following:\n" +
                        "- Publishing any app material in any other media.\n" +
                        "- Selling, sublicensing, and/or otherwise commercializing any app material.\n" +
                        "- Using the Metro Bus Service app in any way that is or may be damaging to the app.\n" +
                        "- Using the Metro Bus Service app in any way that impacts user access to the app.\n" +
                        "- Using the Metro Bus Service app contrary to applicable laws and regulations, or in any way may cause harm to the app, or to any person or business entity.\n" +
                        "\n" + "Disclaimer:\n" +
                        "The Metro Bus Service app is provided \"as is,\" with all faults, and Metro Bus Service makes no express or implied representations or warranties, of any kind related to the Metro Bus Service app or the materials contained on the app. Additionally, nothing contained on the Metro Bus Service app shall be interpreted as advising you.\n" +
                        "\n" +
                        "Governing Law\n" +
                        "These terms and conditions are governed by and construed in accordance with the laws of Pakistan and you irrevocably submit to the exclusive jurisdiction of the courts in that country.\n" +
                        "\n" +
                        "If you have any questions about these terms and conditions, please contact us.")
                .setPositiveButton("Close", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
        // Make the dialog full screen
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.copyFrom(dialog.getWindow().getAttributes());
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT;
        dialog.getWindow().setAttributes(layoutParams);
    }
    private void showPrivacyPolicyDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Privacy Policy")
                .setMessage("This Privacy Policy describes how your personal information is collected, used, and shared when you use the Metro Bus Service mobile application.\n" +
                        "\n" +
                        "Information We Collect:\n" +
                        "When you use the Metro Bus Service app, we collect certain information about you, including:\n"+
                        "- Your name\n" +
                        "- Your email address\n" +
                        "- Your phone number\n" +
                        "- Your CNIC number\n" + "\n" +
                        "How We Use Your Information:\n" +
                        "We use the information we collect to:\n" +
                        "- Provide and maintain the Metro Bus Service app\n" +
                        "- Improve and personalize your experience with the app\n" +
                        "- Communicate with you about the app and its updates\n" +
                        "- Monitor the usage of the app\n" +
                        "- Sharing Your Information\n" +
                        "- We may share your personal information with third parties for the following purposes:\n" +
                        "- To provide the services of the Metro Bus Service app\n" +
                        "- To comply with legal obligations\n" +
                        "- To protect and defend our rights and property\n" +
                        "\n" +"Data Retention:\n" +
                        "We will retain your personal information only for as long as necessary to provide you with the services of the Metro Bus Service app and as described in this Privacy Policy.\n" +
                        "\n" +
                        "Your Rights:\n" +
                        "You have the right to access, correct, and delete your personal information. You may also request that we restrict the processing of your personal information or object to its processing.\n" +
                        "\n" +
                        "Changes to This Privacy Policy:\n" +
                        "We may update our Privacy Policy from time to time. We will notify you of any changes by posting the new Privacy Policy on this page.\n" +
                        "\n" +
                        "Contact Us:\n" +
                        "If you have any questions about our Privacy Policy, please contact us at [contact@email.com]." )
                .setPositiveButton("Close", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
        // Make the dialog full screen
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.copyFrom(dialog.getWindow().getAttributes());
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT;
        dialog.getWindow().setAttributes(layoutParams);
    }
    private void showContactDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Contact")
                .setMessage("Would you like to contact us?\n\n (051) 8446000")
                .setPositiveButton("Call", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        openDialer();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
        builder.create().show();
    }

    private void openDialer() {
        String strPhone="0518446000";

        Intent callIntent = new Intent(Intent.ACTION_DIAL);
        callIntent.setData(Uri.parse("tel:" + strPhone));
        startActivity(callIntent);
    }
    private void openFaqFragment() {
        try {
            // Create and show the FaqFragment
            FaqFragment faqFragment = new FaqFragment();
            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, faqFragment); // Assuming fragment_container is the ID of the container layout
            transaction.addToBackStack(null);  // Add to back stack so user can navigate back
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}