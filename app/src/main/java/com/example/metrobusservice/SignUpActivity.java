package com.example.metrobusservice;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignUpActivity extends AppCompatActivity {

    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private EditText cnicEditText;
    private EditText contactEditText;
    private EditText emailEditText;
    private EditText passwordEditText;
    private EditText confirmPasswordEditText;
    private Button signUpButton;
    private DatabaseManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getSupportActionBar().hide();

        // Initialize the DatabaseManager
        dbManager = new DatabaseManager(this);
        dbManager.open();

        // Initialize UI elements
        firstNameEditText = findViewById(R.id.firstName);
        lastNameEditText = findViewById(R.id.lastName);
        cnicEditText = findViewById(R.id.cnic);
        contactEditText = findViewById(R.id.contact);
        emailEditText = findViewById(R.id.email);
        passwordEditText = findViewById(R.id.pwd_field);
        confirmPasswordEditText = findViewById(R.id.confirm_pwd);
        signUpButton = findViewById(R.id.signup_btn);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
    }

    private void registerUser() {
        // Get input values
        String firstName = firstNameEditText.getText().toString().trim();
        String lastName = lastNameEditText.getText().toString().trim();
        String cnic = cnicEditText.getText().toString().trim();
        String contact = contactEditText.getText().toString().trim();
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();
        String confirmPassword = confirmPasswordEditText.getText().toString().trim();

        // Validate input
        if (TextUtils.isEmpty(firstName)) {
            Toast.makeText(this, "First Name is required", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(lastName)) {
            Toast.makeText(this, "Last Name is required", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(cnic) || cnic.length() != 13) {
            Toast.makeText(this, "CNIC must be 13 digits", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(contact) || contact.length() != 11 || !contact.startsWith("0")) {
            Toast.makeText(this, "Contact must start with 0 and be 11 digits", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Email is required", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Password is required", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!password.equals(confirmPassword)) {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            return;
        }

        // Insert user into database
        dbManager.insertUser(firstName, lastName, cnic, contact, email, password);

        // Show success message
        Toast.makeText(this, "User registered successfully", Toast.LENGTH_SHORT).show();

        // Redirect to DashboardActivity
        Intent intent = new Intent(SignUpActivity.this, DashboardActivity.class);
        startActivity(intent);

        // Clear input fields
        clearInputFields();
    }

    private void clearInputFields() {
        firstNameEditText.setText("");
        lastNameEditText.setText("");
        cnicEditText.setText("");
        contactEditText.setText("");
        emailEditText.setText("");
        passwordEditText.setText("");
        confirmPasswordEditText.setText("");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbManager.close();
    }
}
