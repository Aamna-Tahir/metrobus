package com.example.metrobusservice;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.LinkMovementMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    private EditText phnNoEdtTxt;
    private EditText passwd;
    private Button btn;
    boolean passVisible;
    private static final String COUNTRY_CODE = "+92 | ";

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).hide();

        phnNoEdtTxt = findViewById(R.id.phNo_field);
        passwd = findViewById(R.id.pwd_field);
        btn = findViewById(R.id.login_btn);

        // Initialize the TextView and set up the clickable link
        TextView textView = findViewById(R.id.signUpTxtView);
        String text = getString(R.string.register);

        // Create a SpannableString with the full text
        SpannableString spannableString = new SpannableString(text);

        // Find the start and end of the "Sign up" part
        String signUp = "Sign up";
        int start = text.indexOf(signUp);
        int end = start + signUp.length();

        // Set a ClickableSpan for the "Sign up" part
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
                startActivity(intent);
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(false); // Remove underline
            }
        };
        spannableString.setSpan(clickableSpan, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);


        int linkColor = getResources().getColor(R.color.black);
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(linkColor);
        spannableString.setSpan(colorSpan, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);


        StyleSpan boldSpan = new StyleSpan(android.graphics.Typeface.BOLD);
        spannableString.setSpan(boldSpan, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);


        textView.setText(spannableString);
        textView.setMovementMethod(LinkMovementMethod.getInstance());

        phnNoEdtTxt.setText(COUNTRY_CODE);
        phnNoEdtTxt.setSelection(phnNoEdtTxt.getText().length());

        // Add TextWatcher to handle the input
        phnNoEdtTxt.addTextChangedListener(new TextWatcher() {
            private boolean isEditing = false;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // No action here
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // No action here
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (isEditing) {
                    return;
                }
                isEditing = true;

                if (!s.toString().startsWith(COUNTRY_CODE)) {
                    phnNoEdtTxt.setText(COUNTRY_CODE);
                    phnNoEdtTxt.setSelection(phnNoEdtTxt.getText().length());
                }

                isEditing = false;
            }
        });

        passwd.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int Right = 2;
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= passwd.getRight() - passwd.getCompoundDrawables()[Right].getBounds().width()) {
                        int selection = passwd.getSelectionEnd();
                        if (passVisible) {
                            passwd.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_visibility_off_24, 0);
                            passwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            passVisible = false;
                        } else {
                            passwd.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_visibility_24, 0);
                            passwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            passVisible = true;
                        }
                        passwd.setSelection(selection);
                        return true;
                    }
                }
                return false;
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phoneInput = phnNoEdtTxt.getText().toString().trim();
                String passwordInput = passwd.getText().toString().trim();

                // Check for empty fields
                if (TextUtils.isEmpty(phoneInput) || phoneInput.equals(COUNTRY_CODE)) {
                    phnNoEdtTxt.setError("Please enter phone number");
                    return;
                }

                if (TextUtils.isEmpty(passwordInput)) {
                    passwd.setError("Please enter password");
                    return;
                }

                // Remove the country code from the phone number for validation
                String phoneNumber = phoneInput.startsWith(COUNTRY_CODE) ? phoneInput.substring(COUNTRY_CODE.length()) : phoneInput;

                // Validate phone number
                Pattern pattern = Pattern.compile("^\\d{10}$");
                Matcher matcher = pattern.matcher(phoneNumber);
                boolean matchFound = matcher.matches(); // Use matches() instead of find() to check the entire string

                if (matchFound) {
                    if (passwordInput.equals("admin")) {
                        Intent intent = new Intent(MainActivity.this, DashboardActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(MainActivity.this, "Incorrect password. Try again.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Invalid phone number. Please enter exactly 10 digits.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
