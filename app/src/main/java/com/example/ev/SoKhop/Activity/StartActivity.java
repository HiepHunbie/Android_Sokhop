package com.example.ev.SoKhop.Activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.example.ev.SoKhop.Dialog.DialogCall;
import com.example.ev.SoKhop.R;
import com.example.ev.SoKhop.Utils.Pref;

/**
 * Created by GMAN on 9/26/2016.
 */

public class StartActivity extends Activity {

    private Button btnCaNhan,btnToChuc;
    private com.example.ev.SoKhop.Utils.AutoResizeTextView txtDangNhap;
    private SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        mSharedPreferences = getSharedPreferences(Pref.MY_PREFERENCES,0);

        btnCaNhan = (Button)findViewById(R.id.btnCaNhanLogin);
        btnToChuc = (Button)findViewById(R.id.btnToChucLogin);
        txtDangNhap = (com.example.ev.SoKhop.Utils.AutoResizeTextView)findViewById(R.id.txtDangNhap);


        txtDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnCaNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this,SignUpPersonActivity.class);
                startActivity(intent);
                mSharedPreferences.edit().putString(Pref.TYPE_USER,"1").commit();
                finish();
            }
        });
        btnToChuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //// TODO: 12/12/2016 Hạn chế chỉ cho đăng kí tài khoản tổ chức ở website
                DialogCall.showPopupLoginCompany(StartActivity.this, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int id = v.getId();
                        if (id == R.id.imgClose) {

                        }
                        if (id == R.id.img_ok) {
                            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.sokhop.vn"));
                            startActivity(browserIntent);
                        }
                    }
                });
//                Intent intent = new Intent(StartActivity.this,SignUpCompanyActivity.class);
//                startActivity(intent);
//                mSharedPreferences.edit().putString(Pref.TYPE_USER,"2").commit();
//                finish();
            }
        });
    }
}
