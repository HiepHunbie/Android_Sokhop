package com.example.ev.SoKhop.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.ev.SoKhop.Api.HttpChangePassword;
import com.example.ev.SoKhop.Api.HttpConfirmForgotPassword;
import com.example.ev.SoKhop.Api.HttpForgotPassword;
import com.example.ev.SoKhop.Api.HttpLoginFacebook;
import com.example.ev.SoKhop.Api.HttpLoginGoogle;
import com.example.ev.SoKhop.Dialog.DialogCall;
import com.example.ev.SoKhop.Gson.GsonUser;
import com.example.ev.SoKhop.R;
import com.example.ev.SoKhop.Utils.ConnectionDetector;
import com.example.ev.SoKhop.Utils.ConnectivityReceiver;
import com.example.ev.SoKhop.Api.HttpLoginEmail;
import com.example.ev.SoKhop.Api.core.API;
import com.example.ev.SoKhop.Api.core.HttpResponseCode;
import com.example.ev.SoKhop.Utils.Pref;
import com.example.ev.SoKhop.Utils.ProgresBar;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.Status;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by GMAN on 9/26/2016.
 */

public class LoginActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener{

    private static final String TAG = MainActivity.class.getSimpleName();
    private Button btnLogin,btnCancel;
    private TextView txtForgetPass,txtErrorLogin;
    private com.example.ev.SoKhop.Utils.AutoResizeTextView txtDangKy;
    private ImageButton btnGmail,btnFacebook;
    private static final int RC_SIGN_IN = 007;

    private GoogleApiClient mGoogleApiClient;
    private ProgressDialog mProgressDialog;
    private EditText edtUserName,edtPassword;
    private GsonUser gsonUser = new GsonUser();
    private SharedPreferences mSharedPreferences;
    private ConnectionDetector cd;
    private CallbackManager callbackManager;
    private String accessToken;
    private Handler handler = new Handler();
    private AccessTokenTracker accessTokenTracker;
    private TextView txtErrorUserName,txtErrorMatKhau;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        FacebookSdk.sdkInitialize(this.getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
//        if (BuildConfig.DEBUG) {
//            FacebookSdk.setIsDebugEnabled(true);
//            FacebookSdk.addLoggingBehavior(LoggingBehavior.INCLUDE_ACCESS_TOKENS);
//        }
//        printKeyHash(LoginActivity.this);
        mSharedPreferences = getSharedPreferences(Pref.MY_PREFERENCES, 0);
        cd = new ConnectionDetector(this);
        btnLogin = (Button) findViewById(R.id.btnNext);
        txtDangKy = (com.example.ev.SoKhop.Utils.AutoResizeTextView) findViewById(R.id.txtDangky);
        txtForgetPass = (TextView) findViewById(R.id.txtForgetPass);
        txtErrorUserName = (TextView) findViewById(R.id.txtErrorUserName);
        txtErrorMatKhau = (TextView) findViewById(R.id.txtErrorMatKhau);
        btnGmail = (ImageButton) findViewById(R.id.btnGmail);
        btnFacebook = (ImageButton) findViewById(R.id.btnFacebook);
        txtErrorLogin = (TextView) findViewById(R.id.txtErrorLogin);
        edtUserName = (EditText) findViewById(R.id.edtUserName);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestIdToken(getString(R.string.server_client_id))
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addOnConnectionFailedListener(this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .addScope(new Scope(Scopes.PROFILE))
                .build();
        txtDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, StartActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cd.isConnectingToInternet()) {
                    loginFacebook(LoginActivity.this);
                }
            }
        });

        btnGmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //// TODO: 11/8/2016 Chức năng đăng nhập bằng gmail sẽ làm sau
                showProgressDialog();
                signIn();
            }
        });
        txtForgetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowDialogForgetPassWord(LoginActivity.this, getString(R.string.title_forget_pass));
            }
        });
    }
    public void ShowDialogForgetPassWord(final Context mContext, String titleStr){
        final AlertDialog builder = new AlertDialog.Builder(mContext).create();
        builder.show();
        builder.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE|WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        builder.setCanceledOnTouchOutside(false);
        builder.setCancelable(false);

        Window window = builder.getWindow();
        window.setContentView(R.layout.dialog_forget_pass);

        TextView TitleTv = (TextView) window.findViewById(R.id.dialog_title);
        TitleTv.setText(titleStr);

        final EditText edtCheckUser = (EditText) window.findViewById(R.id.edtCheckUser);
        edtCheckUser.setText(edtUserName.getText().toString());
        Button btnCancel = (Button) window.findViewById(R.id.btnCancel);
        Button btnOk = (Button) window.findViewById(R.id.btnOk);

        btnOk.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                builder.dismiss();
                HttpForgotPassword api = new HttpForgotPassword(LoginActivity.this);
                api.request(edtCheckUser.getText().toString().toString(),new API.APIDelegate() {
                    @Override
                    public void onRequestFinished(HttpResponseCode httpResponseCode, JSONObject jsonResponse) {
                        if (httpResponseCode == HttpResponseCode.SUCCESS) {
                            ShowDialogConfirmForgetPassWord(LoginActivity.this, getString(R.string.title_forget_pass),edtCheckUser.getText().toString());
                        }else {
                            DialogCall.showResponse(mContext, getString(R.string.allow_new_pass_error), new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    int id = v.getId();
                                    if (id == R.id.btnOk) {
                                    }
                                }
                            });
                        }
                    }
                });
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                builder.dismiss();
            }
        });

    }

    public void ShowDialogConfirmForgetPassWord(final Context mContext, String titleStr, final String email){
        final AlertDialog builder = new AlertDialog.Builder(mContext).create();
        builder.show();
        builder.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE|WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        builder.setCanceledOnTouchOutside(false);
        builder.setCancelable(false);

        Window window = builder.getWindow();
        window.setContentView(R.layout.dialog_confirm_password);

        TextView TitleTv = (TextView) window.findViewById(R.id.dialog_title);
        TitleTv.setText(titleStr);
        TextView txtEmailReset = (TextView) window.findViewById(R.id.txtEmailReset);
        txtEmailReset.setText(email);

        final EditText edtToken = (EditText) window.findViewById(R.id.edtToken);
        Button btnCancel = (Button) window.findViewById(R.id.btnCancel);
        Button btnOk = (Button) window.findViewById(R.id.btnOk);

        btnOk.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                builder.dismiss();

                HttpConfirmForgotPassword api = new HttpConfirmForgotPassword(LoginActivity.this);
                api.request(email,edtToken.getText().toString().toString(),new API.APIDelegate() {
                    @Override
                    public void onRequestFinished(HttpResponseCode httpResponseCode, JSONObject jsonResponse) {
                        Log.d("HttpForgotPassword","ss"+jsonResponse);
                        if (httpResponseCode == HttpResponseCode.SUCCESS) {
                            try {
                                JSONObject data = jsonResponse.getJSONObject("data");
                                String email = data.getString("email");
                                String password = data.getString("password");
                                String token = data.getString("token");
                                ShowDialogAddNewPassWord(password,getString(R.string.change_pass),token);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
//                            DialogCall.showResponse(mContext, getString(R.string.allow_new_pass), new View.OnClickListener() {
//                                @Override
//                                public void onClick(View v) {
//                                    int id = v.getId();
//                                    if (id == R.id.btnOk) {
//                                    }
//                                }
//                            });
                        }else {
                            DialogCall.showResponse(mContext, getString(R.string.allow_new_pass_error), new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    int id = v.getId();
                                    if (id == R.id.btnOk) {
                                    }
                                }
                            });
                        }
                    }
                });
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                builder.dismiss();
            }
        });

    }
    private void loginFacebook(final Context context){
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code

                //// TODO: send Facebook token and receive server's respond.

                //current AccessToken
                GraphRequest request = GraphRequest.newMeRequest(
                        AccessToken.getCurrentAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(
                                    JSONObject object,
                                    GraphResponse response) {
                                // Application code
                                //                                    facebook_id = object.getString("id");
//                                    fullname = object.getString("name");
//                                    lastname = object.getString("last_name");
//                                    firstname = object.getString("first_name");
//
//                                    emailfb = object.getString("email");
//                                    birthday = object.getString("birthday");
//
//                                    if (object.has("education")) {
//                                        education = object.getString("education");
//                                        Log.d(TAG, "education: " + education);
//                                    } else {
//                                        education = "";
//                                    }
                                accessToken = String.valueOf(AccessToken.getCurrentAccessToken().getToken());
                                Log.d("accessToken","ss"+accessToken);


                                HttpLoginFacebook api = new HttpLoginFacebook(LoginActivity.this);
                                api.request(accessToken, new API.APIDelegate() {
                                    @Override
                                    public void onRequestFinished(HttpResponseCode httpResponseCode, JSONObject jsonResponse) {
                                        Log.d("avatarPath","ss"+jsonResponse);
                                        if (httpResponseCode == HttpResponseCode.SUCCESS) {
                                            try {
                                                JSONObject data = jsonResponse.getJSONObject("data");
                                                mSharedPreferences.edit().putString(Pref.PREF_KEY_TOKEN,data.getString("token")).commit();
                                                mSharedPreferences.edit().putString(Pref.USER_ID,data.getString("user_id")).commit();
                                                mSharedPreferences.edit().putString(Pref.USER_ID_REAL,data.getString("user_id")).commit();
                                                mSharedPreferences.edit().putString(Pref.TYPE_USER,data.getString("type")).commit();
                                                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                                                startActivity(intent);
                                                finish();
                                                String cookieString = "";
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                        } else {
                                        }
                                    }
                                });
                            }
                        });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,birthday,first_name,last_name,name,email ,education,link");
                request.setParameters(parameters);
                request.executeAsync();
            }

            @Override
            public void onCancel() {
                // App code

            }

            @Override
            public void onError(FacebookException exception) {
                // App code
                exception.printStackTrace();
            }
        });
        LoginManager.getInstance().logInWithReadPermissions(LoginActivity.this, Arrays.asList("public_profile", "user_birthday", "email", "user_education_history"));
    }
    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    private void signOut() {
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                    }
                });
    }
    public void loginOnClick(View view){
        final ProgressDialog progressDialog = ProgresBar.loadingProgress(LoginActivity.this);
        progressDialog.show();
        boolean isConnected = ConnectivityReceiver.isConnected();
        if(!isConnected){
            progressDialog.dismiss();
            txtErrorLogin.setText(getString(R.string.no_internet));
        }else {
//            if(!isEmailValid(edtUserName.getText().toString())){
//                progressDialog.dismiss();
//                txtErrorLogin.setText(getString(R.string.email_saidinhdang));
//                txtErrorUserName.setVisibility(View.VISIBLE);
//                txtErrorMatKhau.setVisibility(View.INVISIBLE);
//            }else {
                HttpLoginEmail api = new HttpLoginEmail(LoginActivity.this);
                api.request(edtUserName.getText().toString().trim(),edtPassword.getText().toString().trim(),new API.APIDelegate() {
                    @Override
                    public void onRequestFinished(HttpResponseCode httpResponseCode, JSONObject jsonResponse) {
                        if (httpResponseCode == HttpResponseCode.SUCCESS) {
                            progressDialog.dismiss();
                            Gson gson = new Gson();
                            gsonUser = gson.fromJson(String.valueOf(jsonResponse), GsonUser.class);
                            mSharedPreferences.edit().putString(Pref.PREF_KEY_TOKEN,gsonUser.gsonUserDetail.token).commit();
                            mSharedPreferences.edit().putString(Pref.USER_ID, String.valueOf(gsonUser.gsonUserDetail.user_id)).commit();
                            mSharedPreferences.edit().putString(Pref.USER_ID_REAL, String.valueOf(gsonUser.gsonUserDetail.user_id)).commit();
                            mSharedPreferences.edit().putString(Pref.TYPE_USER, String.valueOf(gsonUser.gsonUserDetail.type)).commit();
                            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                            startActivity(intent);
                            finish();
                            txtErrorUserName.setVisibility(View.INVISIBLE);
                            txtErrorMatKhau.setVisibility(View.INVISIBLE);
                        }else {
                            progressDialog.dismiss();
                            try {
                                Log.d("jsonResponse","ss"+jsonResponse);
                                if (jsonResponse.toString().contains("validate_code")) {
                                    String errorCode = jsonResponse.getString("validate_code");
                                    if (errorCode.equals("111")) {
                                        txtErrorLogin.setText(getString(R.string.error_password));
                                        txtErrorUserName.setVisibility(View.INVISIBLE);
                                        txtErrorMatKhau.setVisibility(View.VISIBLE);
                                    } else if (errorCode.equals("112")) {
                                        txtErrorLogin.setText(getString(R.string.taikhoankhongtontai));
                                        txtErrorUserName.setVisibility(View.VISIBLE);
                                        txtErrorMatKhau.setVisibility(View.INVISIBLE);
                                    } else if (errorCode.equals("113")) {
                                        txtErrorLogin.setText(getString(R.string.matkhau_sai));
                                        txtErrorUserName.setVisibility(View.INVISIBLE);
                                        txtErrorMatKhau.setVisibility(View.VISIBLE);
                                    } else if (errorCode.equals("114")) {
                                        txtErrorLogin.setText(getString(R.string.tuchoitruycap));
                                        txtErrorUserName.setVisibility(View.VISIBLE);
                                        txtErrorMatKhau.setVisibility(View.VISIBLE);
                                    }else if (errorCode.equals("117")) {
                                        txtErrorLogin.setText(getString(R.string.tendangnhap_sai));
                                        txtErrorUserName.setVisibility(View.VISIBLE);
                                        txtErrorMatKhau.setVisibility(View.INVISIBLE);
                                    }
                                }else {
                                    txtErrorLogin.setText(getString(R.string.error_login));
                                }
                                }catch(JSONException e){
                                    e.printStackTrace();
                                }
                        }
                    }
                });
            }

//        }

    }

    //Lấy keyhash Facebook...
    public static String printKeyHash(Activity context) {
        PackageInfo packageInfo;
        String key = null;
        try {
            //getting application package name, as defined in manifest
            String packageName = context.getApplicationContext().getPackageName();

            //Retriving package info
            packageInfo = context.getPackageManager().getPackageInfo(packageName,
                    PackageManager.GET_SIGNATURES);

            Log.e("Package Name=", context.getApplicationContext().getPackageName());

            for (Signature signature : packageInfo.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                key = new String(Base64.encode(md.digest(), 0));

                // String key = new String(Base64.encodeBytes(md.digest()));
                Log.e("Key Hash=", key);
            }
        } catch (PackageManager.NameNotFoundException e1) {
            Log.e("Name not found", e1.toString());
        }
        catch (NoSuchAlgorithmException e) {
            Log.e("No such an algorithm", e.toString());
        } catch (Exception e) {
            Log.e("Exception", e.toString());
        }

        return key;
    }

    private void handleSignInResult(GoogleSignInResult result) {
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            final GoogleSignInAccount acct = result.getSignInAccount();

            Log.e(TAG, "display name: " + acct.getDisplayName());

            String personName = acct.getDisplayName();
            String personPhotoUrl = acct.getPhotoUrl().toString();
            final String email = acct.getEmail();

            Log.e(TAG, "Name: " + personName + ", email: " + email
                    + ", Image: " + personPhotoUrl);
            String idToken = acct.getIdToken();
            Log.d("jsonResponse","ss"+idToken+"ss"+acct.getId());
//            txtEmail.setText(email);
//            Glide.with(getApplicationContext()).load(personPhotoUrl)
//                    .thumbnail(0.5f)
//                    .crossFade()
//                    .diskCacheStrategy(DiskCacheStrategy.ALL)
//                    .into(imgProfilePic);
            HttpLoginGoogle api = new HttpLoginGoogle(LoginActivity.this);
            api.request(idToken,new API.APIDelegate() {
                @Override
                public void onRequestFinished(HttpResponseCode httpResponseCode, JSONObject jsonResponse) {
                    Log.d("jsonResponse","ss"+jsonResponse);
                    if (httpResponseCode == HttpResponseCode.SUCCESS) {
//                        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
//                        startActivity(intent);
//                        finish();
                        hideProgressDialog();
                        Gson gson = new Gson();
                        gsonUser = gson.fromJson(String.valueOf(jsonResponse), GsonUser.class);
                        mSharedPreferences.edit().putString(Pref.PREF_KEY_TOKEN,gsonUser.gsonUserDetail.token).commit();
                        mSharedPreferences.edit().putString(Pref.USER_ID, String.valueOf(gsonUser.gsonUserDetail.user_id)).commit();
                        mSharedPreferences.edit().putString(Pref.USER_ID_REAL, String.valueOf(gsonUser.gsonUserDetail.user_id)).commit();
                        mSharedPreferences.edit().putString(Pref.TYPE_USER, String.valueOf(gsonUser.gsonUserDetail.type)).commit();
                        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                        startActivity(intent);
                        finish();
                    }else {
                        hideProgressDialog();
                        txtErrorLogin.setText(getString(R.string.error_login));
                    }
                }
            });
//
//            hideProgressDialog();
        } else {
            // Signed out, show unauthenticated UI.
            txtErrorLogin.setText(getString(R.string.error_login));
            hideProgressDialog();
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }
    private void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(LoginActivity.this);
            mProgressDialog.setMessage(getString(R.string.loading));
            mProgressDialog.setIndeterminate(true);
        }

        mProgressDialog.show();
    }

    private void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.hide();
        }
    }

    //// TODO: 9/30/2016 Check đã đăng nhập gmail hay chưa cho lần vào sau
    @Override
    public void onStart() {
        super.onStart();

        mGoogleApiClient.connect();
//        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(mGoogleApiClient);
//        if (opr.isDone()) {
//            // If the user's cached credentials are valid, the OptionalPendingResult will be "done"
//            // and the GoogleSignInResult will be available instantly.
//            Log.d(TAG, "Got cached sign-in");
//            GoogleSignInResult result = opr.get();
//            handleSignInResult(result);
//        } else {
//            // If the user has not previously signed in on this device or the sign-in has expired,
//            // this asynchronous branch will attempt to sign in the user silently.  Cross-device
//            // single sign-on will occur in this branch.
//            showProgressDialog();
//            opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
//                @Override
//                public void onResult(GoogleSignInResult googleSignInResult) {
//                    hideProgressDialog();
//                    handleSignInResult(googleSignInResult);
//                }
//            });
//        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!mGoogleApiClient.isConnected()) {
            mGoogleApiClient.connect();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    public static boolean isEmailValid(String email) {
        boolean isValid = false;

        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }
    public void ShowDialogAddNewPassWord(final String oldPass, String titleStr, final String token){
        final AlertDialog builder = new AlertDialog.Builder(LoginActivity.this).create();
        builder.show();
        builder.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE|WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        builder.setCanceledOnTouchOutside(false);
        builder.setCancelable(false);

        Window window = builder.getWindow();
        window.setContentView(R.layout.dialog_add_new_password);

        TextView TitleTv = (TextView) window.findViewById(R.id.dialog_title);
        TitleTv.setText(titleStr);

        final TextView txtError = (TextView)window.findViewById(R.id.txtError);
        final EditText edtNewPass = (EditText) window.findViewById(R.id.edtNewPass);
        final EditText edtCheckNewPass = (EditText) window.findViewById(R.id.edtCheckNewPass);
        Button btnCancel = (Button) window.findViewById(R.id.btnCancel);
        Button btnChange = (Button) window.findViewById(R.id.btnChange);

        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                if (!edtNewPass.getText().toString().equals(edtCheckNewPass.getText().toString())) {
                    txtError.setVisibility(View.VISIBLE);
                    txtError.setText(getString(R.string.error_retry_password));
                } else {
                    txtError.setVisibility(View.GONE);
                    builder.dismiss();
                    HttpChangePassword api = new HttpChangePassword(LoginActivity.this, token);
                    api.request(oldPass, edtNewPass.getText().toString(), edtCheckNewPass.getText().toString(), new API.APIDelegate() {
                        @Override
                        public void onRequestFinished(HttpResponseCode httpResponseCode, JSONObject jsonResponse) {
                            if (httpResponseCode == HttpResponseCode.SUCCESS) {
                                DialogCall.showResponse(LoginActivity.this, getString(R.string.change_pass_success), new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        int id = v.getId();
                                        if (id == R.id.btnOk) {
                                        }
                                    }
                                });
                            } else {
                                DialogCall.showResponse(LoginActivity.this, getString(R.string.change_pass_error), new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        int id = v.getId();
                                        if (id == R.id.btnOk) {
                                        }
                                    }
                                });
                            }
                        }
                    });
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                builder.dismiss();
            }
        });

    }

}
