package com.example.ev.SoKhop.Dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ev.SoKhop.Activity.MainActivity;
import com.example.ev.SoKhop.R;

import java.util.ArrayList;

/**
 * Created by GMAN on 9/27/2016.
 */

public class DialogCall {

    public static String languageChecked;

    public static void ShowDialogLanguage(final Context mContext, String titleStr, String language, final View.OnClickListener listener){
        final AlertDialog builder = new AlertDialog.Builder(mContext).create();
        builder.show();
        builder.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE|WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        builder.setCanceledOnTouchOutside(false);
        builder.setCancelable(false);

        languageChecked = language;

        Window window = builder.getWindow();
        window.setContentView(R.layout.dialog_language);

        TextView TitleTv = (TextView) window.findViewById(R.id.dialog_title);
        TitleTv.setText(titleStr);

        final RadioButton rdoVietnamese = (RadioButton) window.findViewById(R.id.radioVietnamese);
        final RadioButton rdoEnglish = (RadioButton) window.findViewById(R.id.radioEnglish);
        Button btnCancel = (Button) window.findViewById(R.id.btnCancel);
        Button btnYes = (Button) window.findViewById(R.id.btnYes);

        btnYes.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                if(listener != null){
                    listener.onClick(arg0);
                }
                if(rdoVietnamese.isChecked()){
                    languageChecked = "Tiếng Việt";
                }else if(rdoEnglish.isChecked()){
                    languageChecked = "Tiếng Anh";
                }
                builder.dismiss();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                if(listener != null){
                    listener.onClick(arg0);
                }
                builder.dismiss();
            }
        });
        rdoVietnamese.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                if(listener != null){
                    listener.onClick(arg0);
                }
            }
        });
    }

    public static void ShowDialogPassWord(Context mContext, String titleStr, final View.OnClickListener listener){
        final AlertDialog builder = new AlertDialog.Builder(mContext).create();
        builder.show();
        builder.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE|WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        builder.setCanceledOnTouchOutside(false);
        builder.setCancelable(false);

        Window window = builder.getWindow();
        window.setContentView(R.layout.dialog_password);

        TextView TitleTv = (TextView) window.findViewById(R.id.dialog_title);
        TitleTv.setText(titleStr);

        EditText edtOlderPass = (EditText) window.findViewById(R.id.edtOlderPass);
        EditText edtNewPass = (EditText) window.findViewById(R.id.edtNewPass);
        EditText edtCheckNewPass = (EditText) window.findViewById(R.id.edtCheckNewPass);
        Button btnCancel = (Button) window.findViewById(R.id.btnCancel);
        Button btnChange = (Button) window.findViewById(R.id.btnChange);

        btnChange.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                if(listener != null){
                    listener.onClick(arg0);
                }
                builder.dismiss();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                if(listener != null){
                    listener.onClick(arg0);
                }
                builder.dismiss();
            }
        });

    }
    public static void ShowDialogAddNewHopeJobs(Context mContext, String titleStr,ArrayList<String> list, final View.OnClickListener listener){
        final AlertDialog builder = new AlertDialog.Builder(mContext).create();
        builder.show();
        builder.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE|WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        builder.setCanceledOnTouchOutside(false);
        builder.setCancelable(false);

        Window window = builder.getWindow();
        window.setContentView(R.layout.dialog_add_new_expect_jobs);

        TextView TitleTv = (TextView) window.findViewById(R.id.dialog_title);
        TitleTv.setText(titleStr);
        Spinner spPosition = (Spinner) window.findViewById(R.id.spPosition);

        Button btnCancel = (Button) window.findViewById(R.id.btnCancel);
        Button btnAdd = (Button) window.findViewById(R.id.btnAdd);
        EditText edtTown = (EditText)window.findViewById(R.id.edtTown);
        EditText edtStreet = (EditText)window.findViewById(R.id.edtStreet);
        EditText edtNumber = (EditText)window.findViewById(R.id.edtNumber);
        CheckBox cbUpLuongCoBan = (CheckBox)window.findViewById(R.id.cbUpLuongCoBan);
        EditText edtUpLuongCoBan = (EditText)window.findViewById(R.id.edtUpLuongCoBan);
        EditText edtBetweenLuongCoBanFrom = (EditText)window.findViewById(R.id.edtBetweenLuongCoBanFrom);
        EditText edtBetweenLuongCoBanTo = (EditText)window.findViewById(R.id.edtBetweenLuongCoBanTo);
        EditText edtDownLuongCoBan = (EditText)window.findViewById(R.id.edtDownLuongCoBan);
        EditText edtExactlyLuongCoBan = (EditText)window.findViewById(R.id.edtExactlyLuongCoBan);
        EditText edt_thuong = (EditText)window.findViewById(R.id.edt_thuong);
        EditText edt_phucap = (EditText)window.findViewById(R.id.edt_phucap);
        EditText edt_tongthunhap = (EditText)window.findViewById(R.id.edt_tongthunhap);

        ArrayAdapter<String> adp = new ArrayAdapter<String>(mContext,R.layout.spinner_item, list);
//        adp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spPosition.setAdapter(adp);

        btnAdd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                if(listener != null){
                    listener.onClick(arg0);
                }
                builder.dismiss();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                if(listener != null){
                    listener.onClick(arg0);
                }
                builder.dismiss();
            }
        });

    }


    public static void ShowDialogEducation(Context mContext, String titleStr, final View.OnClickListener listener){
        final AlertDialog builder = new AlertDialog.Builder(mContext).create();
        builder.show();
        builder.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE|WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        builder.setCanceledOnTouchOutside(false);
        builder.setCancelable(false);

        Window window = builder.getWindow();
        window.setContentView(R.layout.dialog_add_education);

        TextView TitleTv = (TextView) window.findViewById(R.id.dialog_title);
        TitleTv.setText(titleStr);

        Spinner spAddMonth = (Spinner) window.findViewById(R.id.spAddMonth);
        Spinner spAddYear = (Spinner) window.findViewById(R.id.spAddYear);
        Spinner spAddMonthTo = (Spinner) window.findViewById(R.id.spAddMonthTo);
        Spinner spAddYearTo = (Spinner) window.findViewById(R.id.spAddYearTo);
        Button btnCancel = (Button) window.findViewById(R.id.btnCancel);
        Button btnSave = (Button) window.findViewById(R.id.btnSave);

        ArrayAdapter<CharSequence> adapterMonth = ArrayAdapter.createFromResource(
                mContext, R.array.month, R.layout.spinner_item);
//        adapterMonth.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spAddMonth.setAdapter(adapterMonth);
        spAddMonthTo.setAdapter(adapterMonth);

        ArrayAdapter<CharSequence> adapterYear = ArrayAdapter.createFromResource(
                mContext, R.array.year, R.layout.spinner_item);
//        adapterYear.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spAddYear.setAdapter(adapterYear);
        spAddYearTo.setAdapter(adapterYear);

        btnSave.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                if(listener != null){
                    listener.onClick(arg0);
                }
                builder.dismiss();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                if(listener != null){
                    listener.onClick(arg0);
                }
                builder.dismiss();
            }
        });

    }

    public static void ShowDialogExp(Context mContext, String titleStr, final View.OnClickListener listener){
        final AlertDialog builder = new AlertDialog.Builder(mContext).create();
        builder.show();
        builder.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE|WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        builder.setCanceledOnTouchOutside(false);
        builder.setCancelable(false);

        Window window = builder.getWindow();
        window.setContentView(R.layout.dialog_add_exp);

        TextView TitleTv = (TextView) window.findViewById(R.id.dialog_title);
        TitleTv.setText(titleStr);

        Spinner spAddMonth = (Spinner) window.findViewById(R.id.spAddMonth);
        Spinner spAddYear = (Spinner) window.findViewById(R.id.spAddYear);
        Spinner spAddMonthTo = (Spinner) window.findViewById(R.id.spAddMonthTo);
        Spinner spAddYearTo = (Spinner) window.findViewById(R.id.spAddYearTo);
        Button btnCancel = (Button) window.findViewById(R.id.btnCancel);
        Button btnSave = (Button) window.findViewById(R.id.btnSave);

        ArrayAdapter<CharSequence> adapterMonth = ArrayAdapter.createFromResource(
                mContext, R.array.month, R.layout.spinner_item);
//        adapterMonth.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spAddMonth.setAdapter(adapterMonth);
        spAddMonthTo.setAdapter(adapterMonth);

        ArrayAdapter<CharSequence> adapterYear = ArrayAdapter.createFromResource(
                mContext, R.array.year, R.layout.spinner_item);
//        adapterYear.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spAddYear.setAdapter(adapterYear);
        spAddYearTo.setAdapter(adapterYear);

        btnSave.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                if(listener != null){
                    listener.onClick(arg0);
                }
                builder.dismiss();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                if(listener != null){
                    listener.onClick(arg0);
                }
                builder.dismiss();
            }
        });

    }

    public static void ShowDialogAddDesiredJob(Context mContext, String titleStr, final View.OnClickListener listener){
        final AlertDialog builder = new AlertDialog.Builder(mContext).create();
        builder.show();
        builder.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE|WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        builder.setCanceledOnTouchOutside(false);
        builder.setCancelable(false);

        Window window = builder.getWindow();
        window.setContentView(R.layout.dialog_add_desired_job);

        TextView TitleTv = (TextView) window.findViewById(R.id.dialog_title);
        TitleTv.setText(titleStr);

        Spinner spVitri = (Spinner) window.findViewById(R.id.spVitri);
        Button btnCancel = (Button) window.findViewById(R.id.btnCancel);
        Button btnSave = (Button) window.findViewById(R.id.btnSave);

        ArrayAdapter<CharSequence> adapterMonth = ArrayAdapter.createFromResource(
                mContext, R.array.vitri, R.layout.spinner_item);
//        adapterMonth.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spVitri.setAdapter(adapterMonth);


        btnSave.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                if(listener != null){
                    listener.onClick(arg0);
                }
                builder.dismiss();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                if(listener != null){
                    listener.onClick(arg0);
                }
                builder.dismiss();
            }
        });

    }


    public static void showImageFull(final Context mContext, String imagePath, final View.OnClickListener listener){
        final AlertDialog builder = new AlertDialog.Builder(mContext).create();
        builder.show();
        builder.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE|WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        builder.setCanceledOnTouchOutside(false);
        builder.setCancelable(false);

        Window window = builder.getWindow();
        window.setContentView(R.layout.dialog_show_full_image);

        ImageView imgClose = (ImageView) window.findViewById(R.id.imgClose);
        ImageView imgImage = (ImageView) window.findViewById(R.id.imgImage);
        Glide.with(mContext).load(imagePath).into(imgImage);
        imgClose.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                if(listener != null){
                    listener.onClick(arg0);
                }
                builder.dismiss();
            }
        });
    }

    public static void showPopupLoginCompany(final Context mContext, final View.OnClickListener listener){
        final AlertDialog builder = new AlertDialog.Builder(mContext).create();
        builder.show();
        builder.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE|WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        builder.setCanceledOnTouchOutside(false);
        builder.setCancelable(false);

        Window window = builder.getWindow();
        window.setContentView(R.layout.dialog_show_popup_signup_company);

        ImageView imgClose = (ImageView) window.findViewById(R.id.imgClose);
        Button img_ok = (Button) window.findViewById(R.id.img_ok);
        imgClose.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                if(listener != null){
                    listener.onClick(arg0);
                }
                builder.dismiss();
            }
        });
        img_ok.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                if(listener != null){
                    listener.onClick(arg0);
                }
                builder.dismiss();
            }
        });
    }

    public static void showResponse(final Context mContext, String titleStr, final View.OnClickListener listener){
        final AlertDialog builder = new AlertDialog.Builder(mContext).create();
        builder.show();
        builder.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE|WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        builder.setCanceledOnTouchOutside(false);
        builder.setCancelable(false);

        Window window = builder.getWindow();
        window.setContentView(R.layout.dialog_response);

        TextView txtMess = (TextView) window.findViewById(R.id.txtMess);
        txtMess.setText(titleStr);

        Button btnOk = (Button) window.findViewById(R.id.btnOk);

        btnOk.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                if(listener != null){
                    listener.onClick(arg0);
                }
                builder.dismiss();
            }
        });
    }

    public static void ShowDialogLogout(final MainActivity mContext, String titleStr, final View.OnClickListener listener){
        final AlertDialog builder = new AlertDialog.Builder(mContext).create();
        builder.show();
        builder.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE|WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        builder.setCanceledOnTouchOutside(false);
        builder.setCancelable(false);

        Window window = builder.getWindow();
        window.setContentView(R.layout.dialog_logout);

        TextView txtMess = (TextView) window.findViewById(R.id.txtMess);
        txtMess.setText(titleStr);
        Button btnCancel = (Button) window.findViewById(R.id.btnCancel);
        Button btnOk = (Button) window.findViewById(R.id.btnOk);

        btnOk.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                if(listener != null){
                    listener.onClick(arg0);
                }
                builder.dismiss();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                if(listener != null){
                    listener.onClick(arg0);
                }
                builder.dismiss();
            }
        });

    }
}

