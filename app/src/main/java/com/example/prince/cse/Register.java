package com.example.prince.cse;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Register extends AppCompatActivity {

    private BackgroundTask backgroundTask = null;

    EditText inputName;
    EditText inputEmail;
    EditText inputPassword;
    EditText inputConfirmPassword;

    Button buttonRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        inputName = (EditText) findViewById(R.id.editName);
        inputEmail = (EditText) findViewById(R.id.editEmail);
        inputPassword = (EditText) findViewById(R.id.editPassword);
        inputConfirmPassword = (EditText) findViewById(R.id.editConfirmPassword);

        buttonRegister = (Button) findViewById(R.id.buttonRegister);

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                attemptRegister();

            }
        });

    }

    private void attemptRegister() {
        if (backgroundTask != null) {
            return;
        }

        // Reset errors.
        inputName.setError(null);
        inputEmail.setError(null);
        inputPassword.setError(null);
        inputConfirmPassword.setError(null);

        // Store values at the time of the login attempt.
        String name = inputName.getText().toString();
        String email = inputEmail.getText().toString();
        String password = inputPassword.getText().toString();
        String confirmPassword = inputConfirmPassword.getText().toString();

        boolean cancel = false;
        View focusView = null;



        // Check for a valid full name.
        if (TextUtils.isEmpty(name)) {
            inputName.setError(getString(R.string.error_field_required));
            focusView = inputName;
            cancel = true;
        }



        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            inputEmail.setError(getString(R.string.error_field_required));
            focusView = inputEmail;
            cancel = true;
        } else if (!isEmailValid(email)) {
            inputEmail.setError(getString(R.string.error_invalid_email));
            focusView = inputEmail;
            cancel = true;
        }


        // Check for a valid password, if the user entered one.
        if (TextUtils.isEmpty(password) || !isPasswordValid(password)) {
            inputPassword.setError(getString(R.string.error_invalid_password));
            focusView = inputPassword;
            cancel = true;
        }

        if (TextUtils.isEmpty(confirmPassword) || !isPasswordValid(confirmPassword)) {
            inputConfirmPassword.setError(getString(R.string.error_invalid_password));
            focusView = inputConfirmPassword;
            cancel = true;
        }

        //check if password and confirm password is the same

        if (!(password.equals(confirmPassword))) {

            inputConfirmPassword.setError(getString(R.string.password_not_matching));
            focusView = inputConfirmPassword;
            cancel = true;

        }


        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.



            name = inputName.getText().toString();
            email = inputEmail.getText().toString();
            password = inputPassword.getText().toString();
            String method = "register";
            backgroundTask = new BackgroundTask(getApplicationContext());
            backgroundTask.execute(method,name,email,password);

            finish();

        }
    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }







}
