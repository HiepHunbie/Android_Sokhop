package com.example.ev.SoKhop.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.ev.SoKhop.Adapter.MyPagesAdapter;
import com.example.ev.SoKhop.Base.BaseFragment;
import com.example.ev.SoKhop.Dialog.DialogCall;
import com.example.ev.SoKhop.Interface.IActivityToFragment;
import com.example.ev.SoKhop.Interface.IFragmentToActivity;
import com.example.ev.SoKhop.Network.DataLoader;
import com.example.ev.SoKhop.R;
import com.example.ev.SoKhop.Utils.Constants;
import com.example.ev.SoKhop.Utils.PhotoPicker;
import com.example.ev.SoKhop.Utils.Pref;
import com.example.ev.SoKhop.Utils.UpLoadImage;

import java.util.HashMap;

/**
 * Created by MSI on 10/7/2016.
 */

public class MyPagesToChucFragment extends BaseFragment implements IActivityToFragment {
    private ListView listView;
    private PhotoPicker mPhotoPicker;
    HashMap<String, DataLoader> mLoaders;
    private String names[] = {
//            mActivity.getString(R.string.thongtintochuc),
//            mActivity.getString(R.string.thuvien),
//            mActivity.getString(R.string.tuyendung),
//            mActivity.getString(R.string.nhansu)
            "Thông Tin Tổ Chức",
            "Thư Viện",
            "Tuyển Dụng",
            "Nhân Sự"
    };
    private Integer imageid[] = {
            R.drawable.ic_thongtin_tochuc,
            R.drawable.ic_photo_tochuc,
            R.drawable.ic_tuyendung_tochuc,
            R.drawable.ic_nhansu_tochuc
    };
    private ImageView avatar,imgCover;
    private boolean isChangingAvatar = false;
    private Handler handler = new Handler();
    private IFragmentToActivity mCallback;
    private TextView txtUserName,txtTitle;
    private ProgressBar progressCover,progressAvatar;
    public MyPagesToChucFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_mypages_tochuc, container, false);
        listView = (ListView) rootView.findViewById(R.id.lvMyPages);
        MyPagesAdapter customList = new MyPagesAdapter(mActivity, names,imageid);
        mPhotoPicker = new PhotoPicker(this,mActivity);
        listView.setAdapter(customList);
        avatar = (ImageView) rootView.findViewById(R.id.imageView);
        imgCover = (ImageView) rootView.findViewById(R.id.imgCover);
        avatar.setImageResource(R.drawable.avatar);
        txtUserName = (TextView)rootView.findViewById(R.id.txtUserName);
        txtTitle = (TextView)rootView.findViewById(R.id.txtTitle);
        txtUserName.setShadowLayer(15,0,0, R.color.toolbar);
        txtTitle.setShadowLayer(15,0,0,R.color.toolbar);
        progressCover = (ProgressBar)rootView.findViewById(R.id.progressCover);
        progressAvatar = (ProgressBar)rootView.findViewById(R.id.progressAvatar);
        p = new Pref(mActivity);
        final String avatarPath = p.getString(Pref.com_avatar_url,null);
        final String coverPath = p.getString(Pref.com_cover_url,null);
        if(avatarPath!=null){
            if(avatarPath.length()>4){
                Glide.with(mActivity).load(avatarPath).listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        progressAvatar.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        progressAvatar.setVisibility(View.GONE);
                        return false;
                    }
                }).into(avatar);
            }else {
                progressAvatar.setVisibility(View.GONE);
                avatar.setImageResource(R.drawable.avatar);
            }
        }
        if(coverPath!=null) {
            if (coverPath.length() > 4) {
                Glide.with(mActivity).load(coverPath).listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        progressCover.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        progressCover.setVisibility(View.GONE);
                        return false;
                    }
                }).into(imgCover);
            } else {
                progressCover.setVisibility(View.GONE);
                imgCover.setImageResource(R.drawable.cover);
            }
        }
        avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                imgCover.setClickable(true);
//                mPhotoPicker.startPick();
//                isChangingAvatar = true;
                if(p.getString(Pref.BOOLEAN_IS_ME,null)!=null){
                    if(p.getString(Pref.BOOLEAN_IS_ME,null).equals("1")){
                        DialogCall.showImageFull(mActivity, avatarPath, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                int id = v.getId();
                                if (id == R.id.imgClose) {

                                }
                            }
                        });
                    }else {
                        p.putString(Pref.NUMBER_IMAGE_CHANGE,"0");
                        imgCover.setClickable(true);
                        mPhotoPicker.startPick();
                        isChangingAvatar = true;
                    }
                }else {
                    p.putString(Pref.NUMBER_IMAGE_CHANGE,"0");
                    imgCover.setClickable(true);
                    mPhotoPicker.startPick();
                    isChangingAvatar = true;
                }
            }
        });
        imgCover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                avatar.setClickable(false);
//                mPhotoPicker.startPick();
//                isChangingAvatar = false;
                if(p.getString(Pref.BOOLEAN_IS_ME,null)!=null){
                    if(p.getString(Pref.BOOLEAN_IS_ME,null).equals("1")){
                        DialogCall.showImageFull(mActivity, coverPath, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                int id = v.getId();
                                if (id == R.id.imgClose) {

                                }
                            }
                        });
                    }else {
                        p.putString(Pref.NUMBER_IMAGE_CHANGE,"1");
                        avatar.setClickable(false);
                        mPhotoPicker.startPick();
                        isChangingAvatar = false;
                    }
                }else {
                    p.putString(Pref.NUMBER_IMAGE_CHANGE,"1");
                    avatar.setClickable(false);
                    mPhotoPicker.startPick();
                    isChangingAvatar = false;
                }
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(i == 0){
//                    Intent intent = new Intent(mActivity, InfomationActivity.class);
//                    intent.putExtra(InfomationActivity.ITEM, "5");
//                    startActivity(intent);
                    mActivity.selectItem(12);
                }else if(i == 1){
//                    Intent intent = new Intent(mActivity, InfomationActivity.class);
//                    intent.putExtra(InfomationActivity.ITEM, "1");
//                    startActivity(intent);
                    mActivity.selectItem(8);
                }else if(i == 2){
//                    Intent intent = new Intent(mActivity, InfomationActivity.class);
//                    intent.putExtra(InfomationActivity.ITEM, "2");
//                    startActivity(intent);
                    mActivity.selectItem(16);
                }else if(i == 3){
//                    Intent intent = new Intent(mActivity, InfomationActivity.class);
//                    intent.putExtra(InfomationActivity.ITEM, "3");
//                    startActivity(intent);
//                    mActivity.selectItem(10);
                }

            }
        });
        txtUserName.setText(p.getString(Pref.com_name,null));
        txtTitle.setText(p.getString(Pref.com_status,null));
        showToolBarItem();
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
            if (isChangingAvatar) {
                progressAvatar.setVisibility(View.VISIBLE);
                UpLoadImage.uploadImage(photoPath, mActivity, mLoaders, 1, Constants.AVATAR,0);
            } else {
                progressCover.setVisibility(View.VISIBLE);
                UpLoadImage.uploadImage(photoPath, mActivity, mLoaders, 2,Constants.COVER,0);
            }
            super.onActivityResult(requestCode, resultCode, data);
        }
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

    public void onRefresh() {
        Toast.makeText(getActivity(), "Fragment 1: Refresh called.",
                Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onResume() {
        super.onResume();
        avatar.setClickable(true);
        imgCover.setClickable(true);

    }

    @Override
    public void refreshCoverImageFragment() {
        final String imagepath = p.getString(Pref.PREF_KEY_COVER, null);
        if (imagepath != null) {
            Glide.with(mActivity).load(imagepath).into(imgCover);
        }
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
