package com.example.ev.SoKhop.Activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ev.SoKhop.Gson.GsonUser;
import com.example.ev.SoKhop.R;
import com.example.ev.SoKhop.Utils.ConnectivityReceiver;
import com.example.ev.SoKhop.Api.HttpSignupEmail;
import com.example.ev.SoKhop.Api.core.API;
import com.example.ev.SoKhop.Api.core.HttpResponseCode;
import com.example.ev.SoKhop.Utils.Pref;
import com.example.ev.SoKhop.Utils.ProgresBar;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by MSI on 10/3/2016.
 */

public class SignUpCompanyActivity extends Activity {

    private Button btnNext;
    private com.example.ev.SoKhop.Utils.AutoResizeTextView txtDangNhap;
    private TextView txtError;
    private EditText edtUserName,edtHotline,edtEmail,edtPassword,edtRetryPassword;
    private SharedPreferences mSharedPreferences;
    private GsonUser gsonUser = new GsonUser();
    private TextView txtErrorNLMatKhau,txtErrorMatKhau,txtErrorEmail,txtErrorHoline,txtErrorTenTC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_conpany);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

        mSharedPreferences = getSharedPreferences(Pref.MY_PREFERENCES,0);
        btnNext = (Button)findViewById(R.id.btnNext);
        txtDangNhap = (com.example.ev.SoKhop.Utils.AutoResizeTextView)findViewById(R.id.txtDangNhap);
        edtUserName = (EditText)findViewById(R.id.edtUserName);
        edtHotline = (EditText)findViewById(R.id.edtPhoneNumber);
        edtEmail = (EditText)findViewById(R.id.edtEmail);
        edtPassword = (EditText)findViewById(R.id.edtPassword);
        edtRetryPassword = (EditText)findViewById(R.id.edtRetryPassword);
        txtErrorNLMatKhau = (TextView)findViewById(R.id.txtErrorNLMatKhau);
        txtErrorMatKhau = (TextView)findViewById(R.id.txtErrorMatKhau);
        txtErrorEmail = (TextView)findViewById(R.id.txtErrorEmail);
        txtErrorHoline = (TextView)findViewById(R.id.txtErrorHoline);
        txtErrorTenTC = (TextView)findViewById(R.id.txtErrorTenTC);
        txtError = (TextView)findViewById(R.id.txtError);

        txtDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpCompanyActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isConnected = ConnectivityReceiver.isConnected();
                if(!isConnected){
                    txtError.setText(getString(R.string.no_internet));
                }else {
//                    if(edtUserName.getText().length()<=5){
//                        txtError.setText(getString(R.string.error_username));
//                    }else {
//                        if(edtHotline.getText().length()<=9){
//                            txtError.setText(getString(R.string.error_phone));
//                        }else {
//                            if (edtPassword.getText().length() <= 5) {
//                                txtError.setText(getString(R.string.error_password));
//                            } else {
//                                if (edtPassword.getText().toString().equals(edtRetryPassword.getText().toString())) {
//                                    registerNewUser(edtEmail.getText().toString(), edtPassword.getText().toString(), edtUserName.getText().toString(), edtHotline.getText().toString(),
//                                            mSharedPreferences.getString(Pref.TYPE_USER, null), edtRetryPassword.getText().toString(), null, null);
//                                } else {
//                                    txtError.setText(getString(R.string.error_retry_password));
//                                }
//                            }
//                        }
//                    }
                    registerNewUser(edtEmail.getText().toString(), edtPassword.getText().toString(), edtUserName.getText().toString(), edtHotline.getText().toString(),
                            mSharedPreferences.getString(Pref.TYPE_USER, null), edtRetryPassword.getText().toString(), null, null);
                }
            }
        });
    }

    private void registerNewUser(String email, final String password, String full_name, String phone_number, String type, final String retype_password, String website, String date_of_birth){
        final ProgressDialog progressDialog = ProgresBar.loadingProgress(SignUpCompanyActivity.this);
        progressDialog.show();
        HttpSignupEmail api = new HttpSignupEmail(SignUpCompanyActivity.this);
        api.request(email,password,full_name,phone_number,type,retype_password,website,date_of_birth,new API.APIDelegate() {
            @Override
            public void onRequestFinished(HttpResponseCode httpResponseCode, JSONObject jsonResponse) {
                if (httpResponseCode == HttpResponseCode.SUCCESS) {
                    //todo parse response
                        Gson gson = new Gson();
                        gsonUser = gson.fromJson(String.valueOf(jsonResponse), GsonUser.class);
                        mSharedPreferences.edit().putString(Pref.PREF_KEY_TOKEN,gsonUser.gsonUserDetail.token).commit();
                        mSharedPreferences.edit().putString(Pref.USER_ID, String.valueOf(gsonUser.gsonUserDetail.user_id)).commit();
                    mSharedPreferences.edit().putString(Pref.USER_ID_REAL, String.valueOf(gsonUser.gsonUserDetail.user_id)).commit();
                        mSharedPreferences.edit().putString(Pref.TYPE_USER,"2").commit();
                        Intent intent = new Intent(SignUpCompanyActivity.this,AvatarActivity.class);
                        startActivity(intent);
                        finish();
                    progressDialog.dismiss();
                    showError(false,false,false,false,false);
                }else {
                    try {
                        progressDialog.dismiss();
                        if (jsonResponse.toString().contains("validate_code")) {
                            String errorCode = jsonResponse.getString("validate_code");
                            Log.d("jsonResponse", "sss" + errorCode);
                            if (errorCode.equals("101")) {
                                txtError.setText(getString(R.string.email_saidinhdang));
                                showError(false, false, true, false, false);
                            } else if (errorCode.equals("102")) {
                                txtError.setText(getString(R.string.error_retry_password));
                                showError(false, false, false, true, true);
                            } else if (errorCode.equals("103")) {
                                txtError.setText(getString(R.string.error_password));
                                showError(false, false, false, true, false);
                            } else if (errorCode.equals("104")) {
                                txtError.setText(getString(R.string.error_username));
                                showError(true, false, false, false, false);
                            } else if (errorCode.equals("105")) {
                                txtError.setText(getString(R.string.error_phone));
                                showError(false, true, false, false, false);
                            } else if (errorCode.equals("106")) {
                            } else if (errorCode.equals("107")) {
                                txtError.setText(getString(R.string.ngaysinh_saidinhdang));
                                showError(false, false, false, false, false);
                            } else if (errorCode.equals("108")) {
                                txtError.setText(getString(R.string.khongketnoiduocserver));
                                showError(false, false, false, false, false);
                            } else if (errorCode.equals("109")) {
                                txtError.setText(getString(R.string.email_datontai));
                                showError(false, false, true, false, false);
                            }   else if (errorCode.equals("110")) {
                                txtError.setText(getString(R.string.sodienthoai_datontai));
                                showError(false, true, false, false, false);
                            }
                        }else {
                            txtError.setText(getString(R.string.error_signup));
                            showError(false, false, false, false, false);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private void showError(boolean error1,boolean error2,boolean error3,boolean error4,boolean error5){
        if(error1){
            txtErrorTenTC.setVisibility(View.VISIBLE);
        }else {
            txtErrorTenTC.setVisibility(View.INVISIBLE);
        }
        if(error2){
            txtErrorHoline.setVisibility(View.VISIBLE);
        }else {
            txtErrorHoline.setVisibility(View.INVISIBLE);
        }
        if(error3){
            txtErrorEmail.setVisibility(View.VISIBLE);
        }else {
            txtErrorEmail.setVisibility(View.INVISIBLE);
        }
        if(error4){
            txtErrorMatKhau.setVisibility(View.VISIBLE);
        }else {
            txtErrorMatKhau.setVisibility(View.INVISIBLE);
        }
        if(error5){
            txtErrorNLMatKhau.setVisibility(View.VISIBLE);
        }else {
            txtErrorNLMatKhau.setVisibility(View.INVISIBLE);
        }
    }
}
