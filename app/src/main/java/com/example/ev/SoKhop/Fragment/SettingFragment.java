package com.example.ev.SoKhop.Fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.example.ev.SoKhop.Api.HttpChangePassword;
import com.example.ev.SoKhop.Api.HttpUpdateInfoUser;
import com.example.ev.SoKhop.Api.core.API;
import com.example.ev.SoKhop.Api.core.HttpResponseCode;
import com.example.ev.SoKhop.Base.BaseFragment;
import com.example.ev.SoKhop.Dialog.DialogCall;
import com.example.ev.SoKhop.R;
import com.example.ev.SoKhop.Utils.Pref;
import com.onesignal.OneSignal;

import org.json.JSONObject;

/**
 * Created by GMAN on 9/26/2016.
 */

public class SettingFragment extends BaseFragment {

    private LinearLayout layout_language,layout_pass;
    private TextView txtSettingLanguage,txtSettingPass,txtSettingEmail;
    private Switch swtInfo;
    public SettingFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_settings, container, false);
        layout_language = (LinearLayout)rootView.findViewById(R.id.layout_language);
        layout_pass = (LinearLayout)rootView.findViewById(R.id.layout_pass);
        txtSettingLanguage = (TextView)rootView.findViewById(R.id.txtSettingLanguage);
        txtSettingPass = (TextView)rootView.findViewById(R.id.txtSettingPass);
        txtSettingEmail = (TextView)rootView.findViewById(R.id.txtSettingEmail);
        swtInfo = (Switch)rootView.findViewById(R.id.swtInfo);

        layout_language.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogCall.ShowDialogLanguage(mActivity, getString(R.string.setting_language), String.valueOf(txtSettingLanguage.getText()), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int id = v.getId();
                        if (id == R.id.btnYes) {
                        } else if (id == R.id.btnCancel) {
                        }
                    }
                });
            }
        });

        txtSettingPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowDialogPassWord(mActivity, getString(R.string.change_pass),p.getString(Pref.PREF_KEY_TOKEN,null));
            }
        });

        txtSettingEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mActivity.isCaNhan){
                    ShowDialogChangeEmail(mActivity, getString(R.string.change_email),p.getString(Pref.upb_email,null));
                }else {
                    ShowDialogChangeEmail(mActivity, getString(R.string.change_email),p.getString(Pref.com_email,null));
                }
            }
        });

        swtInfo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    OneSignal.setSubscription(true);
                }else {
                    OneSignal.setSubscription(false);
                }
                // do something, the isChecked will be
                // true if the switch is in the On position
            }
        });
        showToolBarItem();
        rootView.setFocusableInTouchMode(true);
        rootView.requestFocus();
        rootView.setOnKeyListener( new View.OnKeyListener()
        {
            @Override
            public boolean onKey( View v, int keyCode, KeyEvent event )
            {
                if( keyCode == KeyEvent.KEYCODE_BACK )
                {
                    mActivity.selectItem(0);
                    return true;
                }
                return false;
            }
        } );
        return rootView;
    }


    public void ShowDialogChangeEmail(final Context mContext, String titleStr, final String email){
        final AlertDialog builder = new AlertDialog.Builder(mContext).create();
        builder.show();
        builder.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE|WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        builder.setCanceledOnTouchOutside(false);
        builder.setCancelable(false);

        Window window = builder.getWindow();
        window.setContentView(R.layout.dialog_change_email);

        TextView TitleTv = (TextView) window.findViewById(R.id.dialog_title);
        TitleTv.setText(titleStr);
        TextView txtEmailReset = (TextView) window.findViewById(R.id.txtEmailReset);
        txtEmailReset.setText(email);

        final EditText edtNewEmail = (EditText) window.findViewById(R.id.edtNewEmail);
        Button btnCancel = (Button) window.findViewById(R.id.btnCancel);
        Button btnOk = (Button) window.findViewById(R.id.btnOk);

        btnOk.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                builder.dismiss();
                int res = HttpUpdateInfoUser.updateEmail(mActivity.p.getString(Pref.PREF_KEY_TOKEN,null), edtNewEmail.getText().toString());
                if(res == 200){
                    DialogCall.showResponse(mActivity, mActivity.getString(R.string.editEmailSuccess), new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int id = v.getId();
                            if (id == R.id.btnOk) {
                                if(mActivity.isCaNhan){
                                    p.putString(Pref.upb_email,edtNewEmail.getText().toString());
                                }else {
                                    p.putString(Pref.com_email,edtNewEmail.getText().toString());
                                }
                            }
                        }
                    });
                }else {
                    DialogCall.showResponse(mActivity, mActivity.getString(R.string.editEmailError), new View.OnClickListener() {
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

        btnCancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                builder.dismiss();
            }
        });

    }

    public void ShowDialogPassWord(Context mContext, String titleStr, final String token){
        final AlertDialog builder = new AlertDialog.Builder(mContext).create();
        builder.show();
        builder.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE|WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        builder.setCanceledOnTouchOutside(false);
        builder.setCancelable(false);

        Window window = builder.getWindow();
        window.setContentView(R.layout.dialog_password);

        TextView TitleTv = (TextView) window.findViewById(R.id.dialog_title);
        TitleTv.setText(titleStr);
        final TextView txtError = (TextView)window.findViewById(R.id.txtError);
        final EditText edtOlderPass = (EditText) window.findViewById(R.id.edtOlderPass);
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
                    HttpChangePassword api = new HttpChangePassword(mActivity, token);
                    api.request(edtOlderPass.getText().toString(), edtNewPass.getText().toString(), edtCheckNewPass.getText().toString(), new API.APIDelegate() {
                        @Override
                        public void onRequestFinished(HttpResponseCode httpResponseCode, JSONObject jsonResponse) {
                            if (httpResponseCode == HttpResponseCode.SUCCESS) {
                                DialogCall.showResponse(mActivity, getString(R.string.change_pass_success), new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        int id = v.getId();
                                        if (id == R.id.btnOk) {
                                        }
                                    }
                                });
                            } else {
                                DialogCall.showResponse(mActivity, getString(R.string.change_pass_error), new View.OnClickListener() {
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
    @Override
    public void onResume() {
        super.onResume();
    }

    public void showToolBarItem(){
        mActivity.showItemToolBar(false, false, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = v.getId();
                if (id == R.id.btnCreate) {
                }
            }
        });
    }
}
