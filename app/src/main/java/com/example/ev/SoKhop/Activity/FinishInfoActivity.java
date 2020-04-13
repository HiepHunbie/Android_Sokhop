package com.example.ev.SoKhop.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.example.ev.SoKhop.R;

/**
 * Created by GMAN on 9/26/2016.
 */

public class FinishInfoActivity extends Activity {

    private Button btnDiscover;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish_info);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        btnDiscover = (Button)findViewById(R.id.btnDiscover);

        btnDiscover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FinishInfoActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
