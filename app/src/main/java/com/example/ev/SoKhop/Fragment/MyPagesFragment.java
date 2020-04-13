package com.example.ev.SoKhop.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
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
import com.example.ev.SoKhop.Network.RequestResponse;
import com.example.ev.SoKhop.R;
import com.example.ev.SoKhop.Utils.Constants;
import com.example.ev.SoKhop.Utils.PhotoPicker;
import com.example.ev.SoKhop.Utils.Pref;
import com.example.ev.SoKhop.Utils.UpLoadImage;

import java.util.HashMap;

/**
 * Created by anupamchugh on 10/12/15.
 */
public class MyPagesFragment extends BaseFragment implements IActivityToFragment,DataLoader.DataLoaderListener{
    private ListView listView;
    private PhotoPicker mPhotoPicker;
    HashMap<String, DataLoader> mLoaders;
    private String names[] = {
//            mActivity.getString(R.string.thongtincanhan),
//            mActivity.getString(R.string.thuvien),
//            mActivity.getString(R.string.hocvan),
//            mActivity.getString(R.string.kinhnghiem_tab),
//            mActivity.getString(R.string.congviecmongmuon),
//            mActivity.getString(R.string.phattrienbanthan)
            "Thông Tin Cá Nhân",
            "Thư Viện",
            "Học Vấn",
            "Kinh Nghiệm",
            "Công Việc Mong Muốn",
            "Phát Triển Bản Thân"
    };
    private Integer imageid[] = {
            R.drawable.ic_person,
            R.drawable.ic_photo,
            R.drawable.ic_hocvan,
            R.drawable.ic_kinhnghiem,
            R.drawable.ic_cvmongmuon,
            R.drawable.ic_phattrienbanthan
    };
    private ImageView avatar,imgCover;
    private boolean isChangingAvatar = false;
    private Handler handler = new Handler();
    private IFragmentToActivity mCallback;
    private TextView txtUserName,txtTitle;
    private ProgressBar progressCover,progressAvatar;
    public MyPagesFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_mypages, container, false);
        listView = (ListView) rootView.findViewById(R.id.lvMyPages);
        MyPagesAdapter customList = new MyPagesAdapter(mActivity, names,imageid);
        mPhotoPicker = new PhotoPicker(this,mActivity);
        listView.setAdapter(customList);
        avatar = (ImageView) rootView.findViewById(R.id.imageView);
        imgCover = (ImageView) rootView.findViewById(R.id.imgCover);
        txtUserName = (TextView)rootView.findViewById(R.id.txtUserName);
        txtTitle = (TextView)rootView.findViewById(R.id.txtTitle);
        txtUserName.setShadowLayer(15,0,0,R.color.toolbar);
        txtTitle.setShadowLayer(15,0,0,R.color.toolbar);
        progressCover = (ProgressBar)rootView.findViewById(R.id.progressCover);
        progressAvatar = (ProgressBar)rootView.findViewById(R.id.progressAvatar);
        p = new Pref(mActivity);
        final String avatarPath = p.getString(Pref.PREF_KEY_AVATAR,null);
        final String coverPath = p.getString(Pref.PREF_KEY_COVER,null);
//        avatar.setImageResource(R.drawable.avatar);
//        if(p.getString(Pref.BOOLEAN_IS_ME,null)!=null){
//            if(p.getString(Pref.BOOLEAN_IS_ME,null).equals("1")){
//                avatar.setClickable(false);
//                imgCover.setClickable(false);
//            }else {
//                avatar.setClickable(true);
//                imgCover.setClickable(true);
//            }
//        }else {
//            avatar.setClickable(true);
//            imgCover.setClickable(true);
//        }

        avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                        mActivity.p.putString(Pref.NUMBER_IMAGE_CHANGE,"0");
                        imgCover.setClickable(true);
                        mPhotoPicker.startPick();
                        isChangingAvatar = true;
                    }
                }else {
                    mActivity.p.putString(Pref.NUMBER_IMAGE_CHANGE,"0");
                    imgCover.setClickable(true);
                    mPhotoPicker.startPick();
                    isChangingAvatar = true;
                }
            }
        });
        imgCover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                        mActivity.p.putString(Pref.NUMBER_IMAGE_CHANGE,"1");
                        avatar.setClickable(false);
                        mPhotoPicker.startPick();
                        isChangingAvatar = false;
                    }
                }else {
                    mActivity.p.putString(Pref.NUMBER_IMAGE_CHANGE,"1");
                    avatar.setClickable(false);
                    mPhotoPicker.startPick();
                    isChangingAvatar = false;
                }
            }
        });
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Glide.with(mActivity).load(avatarPath).into(avatar);
//                Glide.with(mActivity).load(coverPath).into(imgCover);
//            }
//        }, 1000);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(i == 0){
//                    Intent intent = new Intent(mActivity, InfomationActivity.class);
//                    intent.putExtra(InfomationActivity.ITEM, "0");
//                    startActivity(intent);
                    mActivity.selectItem(7);
                }else if(i == 1){
//                    Intent intent = new Intent(mActivity, InfomationActivity.class);
//                    intent.putExtra(InfomationActivity.ITEM, "1");
//                    startActivity(intent);
                    mActivity.selectItem(8);
                }else if(i == 2){
//                    Intent intent = new Intent(mActivity, InfomationActivity.class);
//                    intent.putExtra(InfomationActivity.ITEM, "2");
//                    startActivity(intent);
                    mActivity.selectItem(9);
                }else if(i == 3){
//                    Intent intent = new Intent(mActivity, InfomationActivity.class);
//                    intent.putExtra(InfomationActivity.ITEM, "3");
//                    startActivity(intent);
                    mActivity.selectItem(10);
                }else if(i == 4){
//                    Intent intent = new Intent(mActivity, InfomationActivity.class);
//                    intent.putExtra(InfomationActivity.ITEM, "4");
//                    startActivity(intent);
                    mActivity.selectItem(11);
                }

            }
        });

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
        txtUserName.setText(p.getString(Pref.upb_full_name,null));
        txtTitle.setText(p.getString(Pref.upb_status,null));
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
                UpLoadImage.uploadImage(photoPath, mActivity, mLoaders, 1, Constants.AVATAR,0);
                progressAvatar.setVisibility(View.VISIBLE);
            } else {
                UpLoadImage.uploadImage(photoPath, mActivity, mLoaders, 2,Constants.COVER,0);
                progressCover.setVisibility(View.VISIBLE);
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
    public void showToolBarItem() {
        if (mActivity.p.getString(Pref.BOOLEAN_IS_ME, null) != null) {
            if (mActivity.p.getString(Pref.BOOLEAN_IS_ME, null).equals("0")) {
                mActivity.showItemToolBar(false, false, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int id = v.getId();
                        if (id == R.id.btnCreate) {
                        }
                    }
                });
            } else {
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
    }

    @Override
    public void onStart(DataLoader sender) {

    }

    @Override
    public void onCompleted(DataLoader sender, RequestResponse response, Object result, int fragment) {

    }

//    @Override
//    public void onCompleted(DataLoader sender, RequestResponse response, Object result) {
//        if(response.getStatusCode()==200){
//            if (isChangingAvatar) {
//                handler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        final String imagepath = p.getString(Pref.PREF_KEY_AVATAR, null);
//                        mActivity.getImageAlbums(p.getString(Pref.PREF_KEY_TOKEN,null),p.getString(Pref.USER_ID,null),999);
//                        if (imagepath != null) {
//                            Glide.with(mActivity).load(imagepath).into(avatar);
//                            if(p.getString(Pref.TYPE_USER,null).equals("1")) {
//                                SenUpdate.updateAvatar(p.getString(Pref.PREF_KEY_TOKEN, null), imagepath, mActivity);
//                            }else {
//                                SenUpdate.updateAvatarCompany(p.getString(Pref.PREF_KEY_TOKEN, null), imagepath, mActivity);
//                            }
//                            mCallback.refreshAvatarActivity();
//                        }
//                    }
//                }, 0);
//            } else {
//                handler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        final String imagepath = p.getString(Pref.PREF_KEY_COVER, null);
//                        mActivity.getImageAlbums(p.getString(Pref.PREF_KEY_TOKEN,null),p.getString(Pref.USER_ID,null),999);
//                        if (imagepath != null) {
//                            Glide.with(mActivity).load(imagepath).into(imgCover);
//                            if(p.getString(Pref.TYPE_USER,null).equals("1")) {
//                                SenUpdate.updateCoverImage(p.getString(Pref.PREF_KEY_TOKEN, null), imagepath, mActivity);
//                            }else {
//                                SenUpdate.updateCoverImageCompany(p.getString(Pref.PREF_KEY_TOKEN, null), imagepath, mActivity);
//                            }
//                            mCallback.refreshCoverImageActivity();
//                        }
//                    }
//                }, 0);
//            }
//        }else {
//            DialogCall.showResponse(mActivity, getString(R.string.upload_image_fail), new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                }
//            });
//        }
//    }

    @Override
    public void onCompleted(DataLoader sender, RequestResponse response) {

    }

    @Override
    public void onCompletedDUpImage(DataLoader sender, RequestResponse response, Object result, int fragment) {

    }
}
