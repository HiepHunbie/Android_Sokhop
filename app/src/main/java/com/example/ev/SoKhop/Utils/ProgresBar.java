package com.example.ev.SoKhop.Utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;

import com.example.ev.SoKhop.R;

/**
 * Created by MSI on 11/18/2016.
 */

public class ProgresBar {
    public static ProgressDialog loadingProgress(Context context){
        final ProgressDialog progressDialog = new ProgressDialog(context,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setCancelable(false);
        progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        progressDialog.setIndeterminateDrawable(context.getResources().getDrawable(R.drawable.progressbar_custom));
        progressDialog.setMessage(context.getString(R.string.loading));
        return progressDialog;
    }

}
