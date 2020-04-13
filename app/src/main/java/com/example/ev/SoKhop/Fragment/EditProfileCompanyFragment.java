package com.example.ev.SoKhop.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ev.SoKhop.Activity.InfomationActivity;
import com.example.ev.SoKhop.Api.HttpUpdateProfileCompany;
import com.example.ev.SoKhop.Base.BaseFragment;
import com.example.ev.SoKhop.Interface.IFragmentToActivity;
import com.example.ev.SoKhop.Network.DataLoader;
import com.example.ev.SoKhop.R;
import com.example.ev.SoKhop.Utils.Constants;
import com.example.ev.SoKhop.Utils.PhotoPicker;
import com.example.ev.SoKhop.Utils.Pref;
import com.example.ev.SoKhop.Utils.UpLoadImage;

import java.util.HashMap;

/**
 * Created by MSI on 10/11/2016.
 */

public class EditProfileCompanyFragment extends BaseFragment {
    private InfomationActivity activity;

    public EditProfileCompanyFragment() {
    }
    private Pref p;
    private TextView txtInfoEmail,txtInfoHotline;
    private EditText txtInfoQuocGia,txtInfoLinhVuc,txtInfoThoiGianLamViec,txtInfoDiaChi,txtInfoWebsite,
            txtInfoMangXaHoi,txtInfoGioiThieu,txtInfoTrangPhuc,txtInfoPhucLoi,txtInfoNoiQuy,txtInfoPhapNhan,txtInfoEmailLienHe,txtInfoPhoneLienHe,txtInfoChucVu,txtInfoNguoiDaiDien,
            txtInfoMaSoThue,txtInfoThongTinLienHe,txtInfoStatus,txtInfoTenCongTy;
    private TextView txtUploadLogo;
    private ImageView imgLogo;
    private PhotoPicker mPhotoPicker;
    HashMap<String, DataLoader> mLoaders;
    private Handler handler = new Handler();
    private IFragmentToActivity mCallback;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_edit_profile_company, container, false);
//        activity = (InfomationActivity)getActivity();
        p = new Pref(getActivity());
        showToolBarItem();
        mPhotoPicker = new PhotoPicker(this,mActivity);
        txtInfoQuocGia = (EditText)rootView.findViewById(R.id.txtInfoQuocGia);
        txtInfoEmailLienHe = (EditText)rootView.findViewById(R.id.txtInfoEmailLienHe);
        txtInfoPhoneLienHe = (EditText)rootView.findViewById(R.id.txtInfoPhoneLienHe);
        txtInfoChucVu = (EditText)rootView.findViewById(R.id.txtInfoChucVu);
        txtInfoNguoiDaiDien = (EditText)rootView.findViewById(R.id.txtInfoNguoiDaiDien);
        txtInfoTenCongTy = (EditText)rootView.findViewById(R.id.txtInfoTenCongTy);
        txtInfoLinhVuc = (EditText)rootView.findViewById(R.id.txtInfoLinhVuc);
        txtInfoThoiGianLamViec = (EditText)rootView.findViewById(R.id.txtInfoThoiGianLamViec);
        txtInfoDiaChi = (EditText)rootView.findViewById(R.id.txtInfoDiaChi);
        txtInfoHotline = (TextView)rootView.findViewById(R.id.txtInfoHotline);
        txtInfoEmail = (TextView) rootView.findViewById(R.id.txtInfoEmail);
        txtInfoWebsite = (EditText)rootView.findViewById(R.id.txtInfoWebsite);
        txtInfoMangXaHoi = (EditText)rootView.findViewById(R.id.txtInfoMangXaHoi);
        txtInfoGioiThieu = (EditText)rootView.findViewById(R.id.txtInfoGioiThieu);
        txtUploadLogo = (TextView) rootView.findViewById(R.id.txtUploadLogo);
        txtInfoTrangPhuc = (EditText)rootView.findViewById(R.id.txtInfoTrangPhuc);
        txtInfoPhucLoi = (EditText)rootView.findViewById(R.id.txtInfoPhucLoi);
        txtInfoNoiQuy = (EditText)rootView.findViewById(R.id.txtInfoNoiQuy);
        txtInfoPhapNhan = (EditText)rootView.findViewById(R.id.txtInfoPhapNhan);
        txtInfoMaSoThue = (EditText)rootView.findViewById(R.id.txtInfoMaSoThue);
//        txtInfoThongTinLienHe = (EditText)rootView.findViewById(R.id.txtInfoThongTinLienHe);
        txtInfoStatus = (EditText)rootView.findViewById(R.id.txtInfoStatus);
        imgLogo = (ImageView)rootView.findViewById(R.id.imgLogo);

        txtUploadLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p.putString(Pref.NUMBER_IMAGE_CHANGE,"2");
                mPhotoPicker.startPick();
            }
        });

        if(p.getString(Pref.com_name,null)!=null){
            txtInfoTenCongTy.setText(p.getString(Pref.com_name,null));
        }
        if(p.getString(Pref.com_status,null)!=null){
            txtInfoStatus.setText(p.getString(Pref.com_status,null));
        }
        if(p.getString(Pref.com_country,null)!=null){
            txtInfoQuocGia.setText(p.getString(Pref.com_country,null));
        }
        if(p.getString(Pref.com_categories,null)!=null){
            txtInfoLinhVuc.setText(p.getString(Pref.com_categories,null));
        }
        if(p.getString(Pref.com_time_working,null)!=null){
            txtInfoThoiGianLamViec.setText(p.getString(Pref.com_time_working,null));
        }
        if(p.getString(Pref.com_address,null)!=null){
            txtInfoDiaChi.setText(p.getString(Pref.com_address,null));
        }
        if(p.getString(Pref.com_phone,null)!=null){
            txtInfoHotline.setText(p.getString(Pref.com_phone,null));
        }
        if(p.getString(Pref.com_email,null)!=null){
            txtInfoEmail.setText(p.getString(Pref.com_email,null));
        }
        if(p.getString(Pref.com_website,null)!=null){
            txtInfoWebsite.setText(p.getString(Pref.com_website,null));
        }
//        if(Pref.upb_website!=null){
//            txtInfoMangXaHoi.setText(p.getString(Pref.upb_website,null));
//        }
//        if(Pref.PREF_KEY_AVATAR!=null){
//            txtNgoaiHinh.setText(p.getString(Pref.PREF_KEY_AVATAR,null));
//        }
        if(p.getString(Pref.com_introduce,null)!=null){
            txtInfoGioiThieu.setText(p.getString(Pref.com_introduce,null));
        }
        if(p.getString(Pref.com_logo_url,null)!=null){
            Glide.with(mActivity).load(p.getString(Pref.com_logo_url,null)).into(imgLogo);
        }
        if(p.getString(Pref.com_clothes,null)!=null){
            txtInfoTrangPhuc.setText(p.getString(Pref.com_clothes,null));
        }
        if(p.getString(Pref.com_allowance,null)!=null){
            txtInfoPhucLoi.setText(p.getString(Pref.com_allowance,null));
        }
        if(p.getString(Pref.com_rules,null)!=null){
            txtInfoNoiQuy.setText(p.getString(Pref.com_rules,null));
        }
//        if(Pref.upb_sings!=null){
//            txtInfoPhapNhan.setText(p.getString(Pref.upb_sings,null));
//        }
        if(p.getString(Pref.com_tax_code,null)!=null){
            txtInfoMaSoThue.setText(p.getString(Pref.com_tax_code,null));
        }
        if(p.getString(Pref.com_contact_name,null)!=null){
            txtInfoNguoiDaiDien.setText(p.getString(Pref.com_contact_name,null));
        }
        if(p.getString(Pref.com_contact_position,null)!=null){
            txtInfoChucVu.setText(p.getString(Pref.com_contact_position,null));
        }
        if(p.getString(Pref.com_contact_phone,null)!=null){
            txtInfoPhoneLienHe.setText(p.getString(Pref.com_contact_phone,null));
        }
        if(p.getString(Pref.com_contact_email,null)!=null){
            txtInfoEmailLienHe.setText(p.getString(Pref.com_contact_email,null));
        }
        rootView.setFocusableInTouchMode(true);
        rootView.requestFocus();
        rootView.setOnKeyListener( new View.OnKeyListener()
        {
            @Override
            public boolean onKey( View v, int keyCode, KeyEvent event )
            {
                if( keyCode == KeyEvent.KEYCODE_BACK )
                {
                    mActivity.selectItem(12);
                    return true;
                }
                return false;
            }
        } );
        return rootView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        String photoPath = mPhotoPicker.onActivityResult(requestCode, resultCode, data);
        if (photoPath.length() > 1) {
            final Pref p = new Pref(mActivity);
//        if(!isChangingAvatar){
//            UpLoadImage.uploadImage(photoPath,mActivity,mLoaders,true);
//            String imagepath = p.getString(Pref.PREF_KEY_AVATAR,null);
//            Glide.with(mActivity).load(imagepath).into(avatar);
//        }else {
//            UpLoadImage.uploadImage(photoPath,mActivity,mLoaders,false);
//            String imagepath = p.getString(Pref.PREF_KEY_AVATAR,null);
//            Glide.with(mActivity).load(imagepath).into(imgCover);
//        }
                UpLoadImage.uploadImage(photoPath, mActivity, mLoaders, 3, Constants.LOGO,14);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        final String imagepath = p.getString(Pref.PREF_KEY_LOGO, null);
                        if (imagepath != null) {
                            Glide.with(mActivity).load(imagepath).into(imgLogo);
//                            if(p.getString(Pref.TYPE_USER,null).equals("1")) {
//                                SenUpdate.updateAvatar(p.getString(Pref.PREF_KEY_TOKEN, null), imagepath, mActivity);
//                            }else {
//                                SenUpdate.updateAvatarCompany(p.getString(Pref.PREF_KEY_TOKEN, null), imagepath, mActivity);
//                            }
                            mCallback.refreshAvatarActivity();
                        }
                    }
                }, 2000);
            }
        super.onActivityResult(requestCode, resultCode, data);
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallback = (IFragmentToActivity) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement IFragmentToActivity");
        }
    }

    @Override
    public void onDetach() {
        mCallback = null;
        super.onDetach();
    }
    public void showToolBarItem(){
        mActivity.showItemToolBar(false, false, new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        mActivity.showSaveItem(false,new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = v.getId();
                if (id == R.id.txtSaveToolBar) {
                    HttpUpdateProfileCompany.updateProfileCompany(p.getString(Pref.PREF_KEY_TOKEN, null),p.getString(Pref.com_id, null),txtInfoTenCongTy.getText().toString(),
                            txtInfoDiaChi.getText().toString(),null, null,txtInfoWebsite.getText().toString(),
                            txtInfoHotline.getText().toString(),txtInfoThoiGianLamViec.getText().toString(),null
                            ,txtInfoEmail.getText().toString(),txtInfoGioiThieu.getText().toString(),txtInfoQuocGia.getText().toString()
                            ,p.getString(Pref.PREF_KEY_LOGO,null),null,null,null,null,null,txtInfoTrangPhuc.getText().toString()
                            ,txtInfoMaSoThue.getText().toString(),txtInfoPhucLoi.getText().toString(),txtInfoNoiQuy.getText().toString(),null
                            ,txtInfoNguoiDaiDien.getText().toString(),txtInfoPhoneLienHe.getText().toString(),
                            txtInfoEmailLienHe.getText().toString(),txtInfoDiaChi.getText().toString(),txtInfoChucVu.getText().toString(),
                            null,null,null,null,txtInfoStatus.getText().toString(),mActivity,p.getString(Pref.com_user_id, null));
                }
            }
        });
    }
}
