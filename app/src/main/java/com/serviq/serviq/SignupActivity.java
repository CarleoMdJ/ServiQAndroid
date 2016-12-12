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

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignupActivity extends AppCompatActivity {
    private static final String TAG = "SignupActivity";
    private UserDataSource dataSource;

    @BindView(R.id.input_name) EditText _nameText;
    @BindView(R.id.input_email) EditText _emailText;
    @BindView(R.id.input_password) EditText _passwordText;
    @BindView(R.id.input_reEnterPassword) EditText _reEnterPasswordText;
    @BindView(R.id.btn_signup) Button _signupButton;
    @BindView(R.id.link_login) TextView _loginLink;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);

        _signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });

        _loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });
    }

    public void signup() {
        Log.d(TAG, "Signup");

        if(!validate()) {
            onSignupFailed();
            return;
        }

        _signupButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(SignupActivity.this,
                R.style.AppTheme_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage(getString(R.string.reg));
        progressDialog.show();

        String nombre = _nameText.getText().toString();
        String correo = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        dataSource = new UserDataSource(getApplicationContext());
        dataSource.open();

        User user = new User(nombre,correo,password);
        user = dataSource.create(user);
        dataSource.close();

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onSignupSuccess or onSignupFailed
                        // depending on success
                        onSignupSuccess();
                        // onSignupFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);

//        new android.os.Handler().postDelayed(
//                new Runnable() {
//                    public void run() {
//                        UserDataSource dataSource = new UserDataSource(getApplicationContext());
//                        Boolean valido = true;
//                        String nombre = _nameText.getText().toString();
//                        String correo = _emailText.getText().toString();
//                        String password = _passwordText.getText().toString();
//
//
//                        List<User> users = dataSource.findAll();
//
//                        if (!users.isEmpty()){
//                            for (User user : users){
//                                if (user.getCorreo().equals(correo)){
//                                    valido = false;
//                                }
//                            }
//                        }
//
//                        if (valido) {
//                            User user = new User(nombre,correo,password);
//                            user = dataSource.create(user);
//                            onSignupSuccess();
//                        }else{
//                            onSignupFailed();
//                        }
//                        progressDialog.dismiss();
//                    }
//                }, 3000);



    }


    public void onSignupSuccess() {
        _signupButton.setEnabled(true);
        setResult(RESULT_OK, null);
        Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
        startActivityForResult(intent,0);
        finish();
    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), getString(R.string.fail_signup), Toast.LENGTH_LONG).show();

        _signupButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String name = _nameText.getText().toString();
        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();
        String reEnterPassword = _reEnterPasswordText.getText().toString();

        if(name.isEmpty() || name.length() < 4) {
            _nameText.setError(getString(R.string.error_name));
            valid = false;
        }else {
            _nameText.setError(null);
        }

        if(email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError(getString(R.string.error_email));
            valid = false;
        }else {
            _emailText.setError(null);
        }

        if(password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError(getString(R.string.error_password));
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        if(reEnterPassword.isEmpty() || reEnterPassword.length() < 4 || reEnterPassword.length() > 10 || !(reEnterPassword.equals(password))) {
            if(!(reEnterPassword.equals(password))){
                _reEnterPasswordText.setError(getString(R.string.error_re_password));
            }else{
                _reEnterPasswordText.setError(getString(R.string.error_password));
            }
            valid = false;
        }else {
            _reEnterPasswordText.setError(null);
        }

        return valid;
    }
}