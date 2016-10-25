package com.truedev.loginandregister.activity;

/**
 * @author lakshaygirdhar
 * @version 1.0
 * @since 24/10/16
 *
 */

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.truedev.loginandregister.R;
import com.truedev.loginandregister.util.Constants;

import java.util.HashMap;

import in.truedev.androidframework.activity.BaseActivity;
import in.truedev.androidframework.util.PrefsUtils;

public class LoginActivity extends BaseActivity
{
    private EditText etUserName;
    private EditText etPassword;
    private Button btnlogin;
    private ProgressBar pbLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUserName = (EditText) findViewById(R.id.et_username);
        etPassword = (EditText) findViewById(R.id.et_password);
        btnlogin = (Button) findViewById(R.id.btn_login);
        Button btnRegister = (Button) findViewById(R.id.btnRegistration);
        pbLogin = (ProgressBar) findViewById(R.id.pbLogin);

        boolean isLoggedIn = PrefsUtils.getBooleanSharedPreference(LoginActivity.this, Constants.IS_LOGGED_IN, false);

        btnlogin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(validate())
                {
                    loginRequest();
                }
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(v.getContext(), RegistrationActivity.class);
                startActivity(intent);
            }
        });
    }

    public void loginRequest()
    {
        pbLogin.setVisibility(View.VISIBLE);
        btnlogin.setClickable(false);
        HashMap<String, String> params = new HashMap<>();
        params.put("mobile_no", etUserName.getText().toString());
        params.put("password", etPassword.getText().toString());

//        Call<LoginResponseModel> call = RetrofitRequest.loginUser(params);
//        call.enqueue(new Callback<LoginResponseModel>()
//        {
//            @Override
//            public void onResponse(Call<LoginResponseModel> call, Response<LoginResponseModel> response)
//            {
//                btnlogin.setClickable(true);
//                pbLogin.setVisibility(View.GONE);
//
//                if(response.isSuccessful() && null != response.body() && response.body().getCode().equals("200"))
//                {
//                    PrefsUtils.setStringSharedPreference(LoginActivity.this, Constants.USER_ID, fielduser.getText().toString());
//                    PrefsUtils.setStringSharedPreference(LoginActivity.this, Constants.BANK_USER_ID,
//                                                         response.body().getData().getBank_user_id());
//                    PrefsUtils.setBooleanSharedPreference(LoginActivity.this, Constants.IS_LOGGED_IN, true);
//                    Toast.makeText(LoginActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
//                    Log.d("Login", "onResponse: " + response.body().getData().getBank_user_id());
//                    Intent intent = new Intent(LoginActivity.this, AddStockActivity.class);
//                    startActivity(intent);
//                    finish();
//                }
//                else
//                {
//                    Toast.makeText(LoginActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<LoginResponseModel> call, Throwable t)
//            {
//                pbLogin.setVisibility(View.GONE);
//                btnlogin.setClickable(true);
//                Toast.makeText(LoginActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    private boolean validate()
    {
        if(TextUtils.isEmpty(etUserName.getText().toString()) || TextUtils.isEmpty(etPassword.getText().toString()))
        {
            Toast.makeText(this, getString(R.string.enter_user_n_pass), Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(etUserName.getText().toString().length() < 10)
        {
            Toast.makeText(this, getString(R.string.enter_valid_phone), Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}

