package com.example.ev.SoKhop.Fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.ev.SoKhop.Adapter.ImageAlbumAdapter;
import com.example.ev.SoKhop.Api.HttpDeleteImage;
import com.example.ev.SoKhop.Api.core.API;
import com.example.ev.SoKhop.Api.core.HttpResponseCode;
import com.example.ev.SoKhop.Base.BaseFragment;
import com.example.ev.SoKhop.Database.DatabaseHelper;
import com.example.ev.SoKhop.Dialog.DialogCall;
import com.example.ev.SoKhop.Model.Image;
import com.example.ev.SoKhop.Network.DataLoader;
import com.example.ev.SoKhop.R;
import com.example.ev.SoKhop.Utils.PhotoPicker;
import com.example.ev.SoKhop.Utils.Pref;
import com.example.ev.SoKhop.Utils.UpLoadImage;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by MSI on 10/12/2016.
 */

public class ImageAlbumFragment extends BaseFragment {
    private GridView listView;
    private PhotoPicker mPhotoPicker;
    private Handler handler = new Handler();
    HashMap<String, DataLoader> mLoaders;
    private ArrayList<String> urlImageAlbum = new ArrayList<>();
    private ArrayList<String> urlImageAlbum_id = new ArrayList<>();
    private ArrayList<String> image_name_select = new ArrayList<>();
    private List<Image> arrImage;
    private DatabaseHelper databaseHelper;
    private ImageAlbumAdapter customList;
    ArrayList<Integer> listIdDelete = new ArrayList<>();
    private ProgressDialog progressDialog;

    public ImageAlbumFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_image_album, container, false);
        arrImage = new ArrayList<>();
        databaseHelper = new DatabaseHelper(mActivity);
        mPhotoPicker = new PhotoPicker(this,mActivity);
        int album_id = p.getInt(Pref.album_id,0);
        String album_name = p.getString(Pref.album_name,null);
        arrImage = databaseHelper.getImageInAlbum(album_id);
        for(int i = 0;i<arrImage.size();i++){
            urlImageAlbum.add(arrImage.get(i).getImg_file_url());
            urlImageAlbum_id.add(String.valueOf(arrImage.get(i).getImg_id()));
        }
        listView = (GridView) rootView.findViewById(R.id.lvLibrary);

        mActivity.mTitleToolBar.setText(album_name);
        customList = new ImageAlbumAdapter(mActivity,urlImageAlbum);
        listView.setAdapter(customList);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){
                    if (mActivity.p.getString(Pref.BOOLEAN_IS_ME, null) != null) {
                        if (mActivity.p.getString(Pref.BOOLEAN_IS_ME, null).equals("1")) {

                        } else {
                            p.putString(Pref.NUMBER_IMAGE_CHANGE,"2");
                            mPhotoPicker.startPick();
                        }
                    }
                }else {
                    DialogCall.showImageFull(mActivity, urlImageAlbum.get(position-1), new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int id = v.getId();
                            if (id == R.id.imgClose) {

                            }
                        }
                    });
                }
            }
        });

        customList.setListener(new ImageAlbumAdapter.OnCheckListener() {
            @Override
            public void onCheck(int position, String name) {
                listIdDelete.add(Integer.valueOf(urlImageAlbum_id.get(position)));
                image_name_select.add(name);
            }

            @Override
            public void onUncheck(int position, String name) {
                for (int i = 0;i<listIdDelete.size();i++){
                    if(listIdDelete.get(i)==Integer.valueOf(urlImageAlbum_id.get(position))){
                        listIdDelete.remove(i);
                    }
                }
                for (int i = 0;i<image_name_select.size();i++){
                    if(image_name_select.get(i).equals(urlImageAlbum.get(position))){
                        image_name_select.remove(i);
                    }
                }
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
                    mActivity.selectItem(8);
                    return true;
                }
                return false;
            }
        } );
        return rootView;
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
        mActivity.showDeleteItem(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = v.getId();
                if (id == R.id.btnDelete) {
                    customList.setCheckDelete(true);
                    customList.notifyDataSetChanged();
                    mActivity.hideDeleteItem();
                    showItemSave();
                }
            }
        });
    }

    private void showItemSave() {
        mActivity.showSaveItem(true,new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String token = p.getString(Pref.PREF_KEY_TOKEN, null);
                HttpDeleteImage httpDeleteAlbum = new HttpDeleteImage(mActivity, token);
                String ids = "";
                for (int i = 0; i < listIdDelete.size(); i++) {
                    ids += listIdDelete.get(i) + ",";
                }
                if (!ids.equals("")) {
                    httpDeleteAlbum.request(ids, new API.APIDelegate() {
                        @Override
                        public void onRequestFinished(HttpResponseCode httpResponseCode, JSONObject jsonResponse) {
                            if (httpResponseCode == HttpResponseCode.SUCCESS) {
                                for (int i = 0; i < listIdDelete.size(); i++) {
                                    databaseHelper.deleteImageById(listIdDelete.get(i) + "");

                                }

                                for (int i = 0; i < image_name_select.size(); i++) {
                                    urlImageAlbum.remove(image_name_select.get(i));
                                }

                                DialogCall.showResponse(mActivity, mActivity.getString(R.string.delete_success), new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        int id = v.getId();
                                        if (id == R.id.btnOk) {
                                            mActivity.displayDeleteItem();
                                            mActivity.hideTxtSaveToolBar();
                                            customList.setCheckDelete(false);
                                            customList.notifyDataSetChanged();
                                        }
                                    }
                                });
                            }else {
                                DialogCall.showResponse(mActivity, mActivity.getString(R.string.delete_error), new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        int id = v.getId();
                                        if (id == R.id.btnOk) {
                                            mActivity.displayDeleteItem();
                                            mActivity.hideTxtSaveToolBar();
                                        }
                                    }
                                });
                            }
                        }
                    });
                }else {
                    mActivity.displayDeleteItem();
                    mActivity.hideTxtSaveToolBar();
                    customList.setCheckDelete(false);
                    customList.notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        String photoPath = mPhotoPicker.onActivityResult(requestCode, resultCode, data);
        if(photoPath.length()>1){
//            progressDialog = ProgresBar.loadingProgress(mActivity);
//            progressDialog.show();
            UpLoadImage.uploadImage(photoPath,mActivity,mLoaders,4,p.getString(Pref.album_name,null),999);
//                handler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        progressDialog.dismiss();
//                        mActivity.getImageAlbums(p.getString(Pref.PREF_KEY_TOKEN,null),p.getString(Pref.USER_ID,null),15);
//                    }
//                },1500);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
