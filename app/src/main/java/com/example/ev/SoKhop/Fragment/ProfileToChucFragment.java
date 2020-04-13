package com.example.ev.SoKhop.Fragment;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ev.SoKhop.Activity.InfomationActivity;
import com.example.ev.SoKhop.Base.BaseFragment;
import com.example.ev.SoKhop.Dialog.DialogCall;
import com.example.ev.SoKhop.R;
import com.example.ev.SoKhop.Utils.Pref;

/**
 * Created by MSI on 10/7/2016.
 */

public class ProfileToChucFragment extends BaseFragment {
    private InfomationActivity activity;

    public ProfileToChucFragment() {
    }
    private Pref p;
    private TextView txtInfoQuocGia,txtInfoLinhVuc,txtInfoThoiGianLamViec,txtInfoDiaChi,txtInfoHotline,txtInfoEmail,txtInfoWebsite,
            txtInfoMangXaHoi,txtInfoGioiThieu,txtInfoLogo,txtInfoTrangPhuc,txtInfoPhucLoi,txtInfoNoiQuy,txtInfoPhapNhan,
            txtInfoMaSoThue,txtInfoThongTinLienHe,txtInfoEmailLienHe,txtInfoPhoneLienHe,txtInfoChucVu,txtInfoNguoiDaiDien;
    private ImageView imgLogo;
    private LinearLayout layout_quocgia,layout_linhvuc,layout_thoigianlamviec,layout_diachi,layout_hotline,layout_email,layout_website,
            layout_mangxahoi,layout_gioithieu,layout_logo,layout_trangphuc,layout_phucloi,layout_noiquy,layout_phapnhan,layout_masothue,layout_lienhe;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_profile_tochuc, container, false);
//        activity = (InfomationActivity)getActivity();
        p = new Pref(getActivity());
        showToolBarItem();
        imgLogo = (ImageView)rootView.findViewById(R.id.imgLogo);
        txtInfoQuocGia = (TextView)rootView.findViewById(R.id.txtInfoQuocGia);
        txtInfoEmailLienHe = (TextView)rootView.findViewById(R.id.txtInfoEmailLienHe);
        txtInfoPhoneLienHe = (TextView)rootView.findViewById(R.id.txtInfoPhoneLienHe);
        txtInfoChucVu = (TextView)rootView.findViewById(R.id.txtInfoChucVu);
        txtInfoNguoiDaiDien = (TextView)rootView.findViewById(R.id.txtInfoNguoiDaiDien);
        txtInfoLinhVuc = (TextView)rootView.findViewById(R.id.txtInfoLinhVuc);
        txtInfoThoiGianLamViec = (TextView)rootView.findViewById(R.id.txtInfoThoiGianLamViec);
        txtInfoDiaChi = (TextView)rootView.findViewById(R.id.txtInfoDiaChi);
        txtInfoHotline = (TextView)rootView.findViewById(R.id.txtInfoHotline);
        txtInfoEmail = (TextView)rootView.findViewById(R.id.txtInfoEmail);
        txtInfoWebsite = (TextView)rootView.findViewById(R.id.txtInfoWebsite);
        txtInfoMangXaHoi = (TextView)rootView.findViewById(R.id.txtInfoMangXaHoi);
        txtInfoGioiThieu = (TextView)rootView.findViewById(R.id.txtInfoGioiThieu);
        txtInfoTrangPhuc = (TextView)rootView.findViewById(R.id.txtInfoTrangPhuc);
        txtInfoPhucLoi = (TextView)rootView.findViewById(R.id.txtInfoPhucLoi);
        txtInfoNoiQuy = (TextView)rootView.findViewById(R.id.txtInfoNoiQuy);
        txtInfoPhapNhan = (TextView)rootView.findViewById(R.id.txtInfoPhapNhan);
        txtInfoMaSoThue = (TextView)rootView.findViewById(R.id.txtInfoMaSoThue);

        layout_quocgia = (LinearLayout)rootView.findViewById(R.id.layout_quocgia);
        layout_linhvuc = (LinearLayout)rootView.findViewById(R.id.layout_linhvuc);
        layout_thoigianlamviec = (LinearLayout)rootView.findViewById(R.id.layout_thoigianlamviec);
        layout_diachi = (LinearLayout)rootView.findViewById(R.id.layout_diachi);
        layout_hotline = (LinearLayout)rootView.findViewById(R.id.layout_hotline);
        layout_website = (LinearLayout)rootView.findViewById(R.id.layout_website);
        layout_mangxahoi = (LinearLayout)rootView.findViewById(R.id.layout_mangxahoi);
        layout_gioithieu = (LinearLayout)rootView.findViewById(R.id.layout_gioithieu);
        layout_logo = (LinearLayout)rootView.findViewById(R.id.layout_logo);
        layout_trangphuc = (LinearLayout)rootView.findViewById(R.id.layout_trangphuc);
        layout_phucloi = (LinearLayout)rootView.findViewById(R.id.layout_phucloi);
        layout_noiquy = (LinearLayout)rootView.findViewById(R.id.layout_noiquy);
        layout_phapnhan = (LinearLayout)rootView.findViewById(R.id.layout_phapnhan);
        layout_masothue = (LinearLayout)rootView.findViewById(R.id.layout_masothue);
//        layout_lienhe = (LinearLayout)rootView.findViewById(R.id.layout_lienhe);
        layout_email = (LinearLayout)rootView.findViewById(R.id.layout_email);

        if(Pref.com_country!=null){
            if(p.getString(Pref.com_country,null).length()>0){
                layout_quocgia.setVisibility(View.VISIBLE);
                txtInfoQuocGia.setText(p.getString(Pref.com_country,null));
            }else {
                layout_quocgia.setVisibility(View.GONE);
            }
        }
        if(Pref.com_categories!=null){
            if(p.getString(Pref.com_categories,null).length()>0){
                layout_linhvuc.setVisibility(View.VISIBLE);
                txtInfoLinhVuc.setText(p.getString(Pref.com_categories,null));
            }else {
                layout_linhvuc.setVisibility(View.GONE);
            }
        }
        if(Pref.com_time_working!=null){
            if(p.getString(Pref.com_time_working,null).length()>0){
                layout_thoigianlamviec.setVisibility(View.VISIBLE);
                txtInfoThoiGianLamViec.setText(p.getString(Pref.com_time_working,null));
            }else {
                layout_thoigianlamviec.setVisibility(View.GONE);
            }
        }
        if(Pref.com_address!=null){
            if(p.getString(Pref.com_address,null).length()>0){
                layout_diachi.setVisibility(View.VISIBLE);
                txtInfoDiaChi.setText(p.getString(Pref.com_address,null));
            }else {
                layout_diachi.setVisibility(View.GONE);
            }
        }
        if(Pref.com_phone!=null){
            if(p.getString(Pref.com_phone,null).length()>0){
                layout_hotline.setVisibility(View.VISIBLE);
                txtInfoHotline.setText(p.getString(Pref.com_phone,null));
            }else {
                layout_hotline.setVisibility(View.GONE);
            }
        }
        if(Pref.com_email!=null){
            if(p.getString(Pref.com_email,null).length()>0){
                layout_email.setVisibility(View.VISIBLE);
                txtInfoEmail.setText(p.getString(Pref.com_email,null));
            }else {
                layout_email.setVisibility(View.GONE);
            }
        }
        if(Pref.com_website!=null){
            if(p.getString(Pref.com_website,null).length()>0){
                layout_website.setVisibility(View.VISIBLE);
                txtInfoWebsite.setText(p.getString(Pref.com_website,null));
            }else {
                layout_website.setVisibility(View.GONE);
            }
        }
//        if(Pref.upb_website!=null){
//            txtInfoMangXaHoi.setText(p.getString(Pref.upb_website,null));
//        }
//        if(Pref.PREF_KEY_AVATAR!=null){
//            txtNgoaiHinh.setText(p.getString(Pref.PREF_KEY_AVATAR,null));
//        }
        if(Pref.com_introduce!=null){
            if(p.getString(Pref.com_introduce,null).length()>0){
                layout_gioithieu.setVisibility(View.VISIBLE);
                txtInfoGioiThieu.setText(p.getString(Pref.com_introduce,null));
            }else {
                layout_gioithieu.setVisibility(View.GONE);
            }
        }
        if(Pref.com_logo_url!=null){
            if(p.getString(Pref.com_logo_url,null).length()>0){
                layout_logo.setVisibility(View.VISIBLE);
                Glide.with(mActivity).load(p.getString(Pref.com_logo_url,null)).into(imgLogo);
            }else {
                layout_logo.setVisibility(View.GONE);
            }
        }
        if(Pref.com_clothes!=null){
            if(p.getString(Pref.com_clothes,null).length()>0){
                layout_trangphuc.setVisibility(View.VISIBLE);
                txtInfoTrangPhuc.setText(p.getString(Pref.com_clothes,null));
            }else {
                layout_trangphuc.setVisibility(View.GONE);
            }

        }
        if(Pref.com_allowance!=null){
            if(p.getString(Pref.com_allowance,null).length()>0){
                layout_phucloi.setVisibility(View.VISIBLE);
                txtInfoPhucLoi.setText(p.getString(Pref.com_allowance,null));
            }else {
                layout_phucloi.setVisibility(View.GONE);
            }
        }
        if(Pref.com_rules!=null){
            if(p.getString(Pref.com_rules,null).length()>0){
                layout_noiquy.setVisibility(View.VISIBLE);
                txtInfoNoiQuy.setText(p.getString(Pref.com_rules,null));
            }else {
                layout_noiquy.setVisibility(View.GONE);
            }
        }
//        if(Pref.upb_sings!=null){
//            txtInfoPhapNhan.setText(p.getString(Pref.upb_sings,null));
//        }
        if(Pref.com_tax_code!=null){
            if(p.getString(Pref.com_tax_code,null).length()>0){
                layout_masothue.setVisibility(View.VISIBLE);
                txtInfoMaSoThue.setText(p.getString(Pref.com_tax_code,null));
            }else {
                layout_masothue.setVisibility(View.GONE);
            }
        }
//        if(Pref.com_contact_name!=null){
//            txtInfoThongTinLienHe.setText(p.getString(Pref.com_contact_name,null));
//        }
        if(Pref.com_contact_name!=null){
            txtInfoNguoiDaiDien.setText(p.getString(Pref.com_contact_name,null));
        }
        if(Pref.com_contact_position!=null){
            txtInfoChucVu.setText(p.getString(Pref.com_contact_position,null));
        }
        if(Pref.com_contact_phone!=null){
            txtInfoPhoneLienHe.setText(p.getString(Pref.com_contact_phone,null));
        }
        if(Pref.com_contact_email!=null){
            txtInfoEmailLienHe.setText(p.getString(Pref.com_contact_email,null));
        }

        imgLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogCall.showImageFull(mActivity, p.getString(Pref.com_logo_url,null), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int id = v.getId();
                        if (id == R.id.imgClose) {

                        }
                    }
                });
            }
        });
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
    public void showToolBarItem(){
        mActivity.showItemToolBar(true, false, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = v.getId();
                if (id == R.id.btnCreate) {
                    mActivity.selectItem(14);
                }
            }
        });
    }
}
