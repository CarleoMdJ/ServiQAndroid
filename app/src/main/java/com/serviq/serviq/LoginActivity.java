package com.serviq.serviq;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;
    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "foo@gmail.com:hello", "bar@example.com:world"
    };

    @BindView(R.id.input_email)
    EditText _emailText;
    @BindView(R.id.input_password)
    EditText _passwordText;
    @BindView(R.id.btn_login)
    Button _loginButton;
    @BindView(R.id.link_signup)
    TextView _signupLink;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        _loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login();
            }
        });

        _signupLink.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Signup activity
                Intent intent = new Intent(getApplicationContext(),SignupActivity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
                finish();
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });
    }

    public void login() {
        Log.d(TAG, "Login");

        if (!validate()) {
            onLoginFailed();
            return;
        }

        _loginButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,
                R.style.AppTheme_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage(getString(R.string.auth));
        progressDialog.show();


        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {

                        String email = _emailText.getText().toString();
                        String password = _passwordText.getText().toString();
                        _emailText.setError(getString(R.string.error_login));
                        _passwordText.setError(getString(R.string.error_login));

                        Boolean valido = false;
                        for (String credential : DUMMY_CREDENTIALS) {
                            String[] pieces = credential.split(":");
                            if (pieces[0].equals(email) && pieces[1].equals(password)){
                                valido = true;
                                break;
                            }
                        }
                        if (valido) {
                            _emailText.setError(null);
                            _passwordText.setError(null);
                            onLoginSuccess();
                        }else{
                            onLoginFailed();
                        }
                        progressDialog.dismiss();
                    }
                }, 3000);
    }


//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (requestCode == REQUEST_SIGNUP) {
//            if (resultCode == RESULT_OK) {
//                Log.d(TAG, "signup");
//                this.finish();
//            }
//        }
//    }

    @Override
    public void onBackPressed() {
        // Disable going back to the MainActivity
        moveTaskToBack(true);
    }

    public void onLoginSuccess() {
        _loginButton.setEnabled(true);
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), getString(R.string.fail_login), Toast.LENGTH_LONG).show();
        _loginButton.setEnabled(true);
    }

    public boolean validate() {
        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        _emailText.setError(null);
        _passwordText.setError(null);

        if(email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() || password.isEmpty() || password.length() < 4 || password.length() > 10) {
            if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                _emailText.setError(getString(R.string.error_email));
            }
            if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
                _passwordText.setError(getString(R.string.error_password));
            }
        }else{
            return true;
        }
        return false;
    }
}
