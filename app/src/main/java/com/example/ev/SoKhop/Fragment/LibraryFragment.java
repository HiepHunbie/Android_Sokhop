package com.example.ev.SoKhop.Fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

import com.example.ev.SoKhop.Activity.MainActivity;
import com.example.ev.SoKhop.Adapter.ListLibraryAdapter;
import com.example.ev.SoKhop.Api.HttpAddNewAlbum;
import com.example.ev.SoKhop.Api.HttpDeleteAlbum;
import com.example.ev.SoKhop.Api.core.API;
import com.example.ev.SoKhop.Api.core.HttpResponseCode;
import com.example.ev.SoKhop.Base.BaseFragment;
import com.example.ev.SoKhop.Database.DatabaseHelper;
import com.example.ev.SoKhop.Dialog.DialogCall;
import com.example.ev.SoKhop.Model.Image;
import com.example.ev.SoKhop.R;
import com.example.ev.SoKhop.Utils.Pref;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GMAN on 9/27/2016.
 */

public class LibraryFragment extends BaseFragment{
    private GridView listView;

    ArrayList<Integer> listIdDelete = new ArrayList<>();
    private ArrayList<String> nameAlbum = new ArrayList<>();
    private ArrayList<String> totalImageAlbum = new ArrayList<>();
    private ArrayList<String> urlImageAlbum = new ArrayList<>();
    private ArrayList<Integer> album_id = new ArrayList<>();
    private ArrayList<String> album_name_select = new ArrayList<>();
    private List<Image> arrImage;
    private DatabaseHelper databaseHelper;
    private ListLibraryAdapter customList;

    public LibraryFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_library, container, false);
        arrImage = new ArrayList<>();
        databaseHelper = new DatabaseHelper(mActivity);
        arrImage = databaseHelper.getAllImage();
        int stTotalImageAlbum = 999;
        for(int i = 0;i<arrImage.size();i++){
            if(arrImage.get(i).getImg_album_id()!=stTotalImageAlbum){
                stTotalImageAlbum = arrImage.get(i).getImg_album_id();
                nameAlbum.add(arrImage.get(i).getAlb_name());
                urlImageAlbum.add(arrImage.get(i).getImg_file_url());
                totalImageAlbum.add(String.valueOf(arrImage.get(i).getTotal_img_same_al()));
                album_id.add(arrImage.get(i).getImg_album_id());
            }
//            if(arrImage.get(i).getTotal_img_same_al()!=stTotalImageAlbum){
//                stTotalImageAlbum = arrImage.get(i).getTotal_img_same_al();
//                totalImageAlbum.add(String.valueOf(arrImage.get(i).getTotal_img_same_al()));
//            }

        }
        showToolBarItem();
        listView = (GridView) rootView.findViewById(R.id.lvLibrary);
        customList = new ListLibraryAdapter(mActivity, nameAlbum,totalImageAlbum,urlImageAlbum);
        listView.setAdapter(customList);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position!=0){
                    p.putInt(Pref.album_id,album_id.get(position-1));
                    p.putString(Pref.album_name,nameAlbum.get(position-1));
                    mActivity.selectItem(15);
                }else if(position == 0){
                    if (mActivity.p.getString(Pref.BOOLEAN_IS_ME, null) != null) {
                        if (mActivity.p.getString(Pref.BOOLEAN_IS_ME, null).equals("1")) {

                        } else {
                            ShowDialogAddNewAlbum(mActivity, getString(R.string.add_new_album));
                        }
                    }
                }
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
        customList.setListener(new ListLibraryAdapter.OnCheckListener() {
            @Override
            public void onCheck(int position, String name) {
                listIdDelete.add(album_id.get(position));
                album_name_select.add(name);
            }

            @Override
            public void onUncheck(int position, String name) {
                for (int i = 0;i<listIdDelete.size();i++){
                    if(listIdDelete.get(i)==Integer.valueOf(album_id.get(position))){
                        listIdDelete.remove(i);
                    }
                }
                for (int i = 0;i<album_name_select.size();i++){
                    if(album_name_select.get(i).equals(nameAlbum.get(position))){
                        album_name_select.remove(i);
                    }
                }
            }
        });

        return rootView;
    }

    public static void ShowDialogAddNewAlbum(final MainActivity mContext, String titleStr){
        final AlertDialog builder = new AlertDialog.Builder(mContext).create();
        builder.show();
        builder.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE|WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        builder.setCanceledOnTouchOutside(false);
        builder.setCancelable(false);

        Window window = builder.getWindow();
        window.setContentView(R.layout.dialog_add_new_album);

        TextView TitleTv = (TextView) window.findViewById(R.id.dialog_title);
        TitleTv.setText(titleStr);

        final EditText new_album_name = (EditText)window.findViewById(R.id.edtNewAlbum) ;
        final Button btnCancel = (Button) window.findViewById(R.id.btnCancel);
        Button btnOk = (Button) window.findViewById(R.id.btnOk);
        final TextView txtError = (TextView)window.findViewById(R.id.txtError);

        btnOk.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                if(new_album_name.getText().toString().length()>0){
                    addNewAlbum(mActivity.p.getString(Pref.PREF_KEY_TOKEN,null),new_album_name.getText().toString(),mActivity);
                    builder.dismiss();
                    txtError.setVisibility(View.GONE);
                }else {
                    txtError.setVisibility(View.VISIBLE);
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                builder.dismiss();
                txtError.setVisibility(View.GONE);
            }
        });

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
                HttpDeleteAlbum httpDeleteAlbum = new HttpDeleteAlbum(mActivity, token);
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
                                    databaseHelper.deleteAlbum(listIdDelete.get(i) + "");

                                }

                                for (int i = 0; i < album_name_select.size(); i++) {
                                    nameAlbum.remove(album_name_select.get(i));
                                    urlImageAlbum.remove(album_name_select.get(i));
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
    public static void addNewAlbum(String token, String album_name, final Activity activity){
        HttpAddNewAlbum api = new HttpAddNewAlbum(activity,token);
        api.request(album_name,new API.APIDelegate() {
            @Override
            public void onRequestFinished(HttpResponseCode httpResponseCode, JSONObject jsonResponse) {
                if (httpResponseCode == HttpResponseCode.SUCCESS) {
                    mActivity.getImageAlbums(mActivity.p.getString(Pref.PREF_KEY_TOKEN,null),mActivity.p.getString(Pref.USER_ID,null),8);
                }else {
                }
            }
        });
    }
}
