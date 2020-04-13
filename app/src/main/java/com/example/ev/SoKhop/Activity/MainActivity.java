package com.example.ev.SoKhop.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Handler;
import android.os.StrictMode;
import android.provider.Settings;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.ev.SoKhop.Adapter.DrawerItemCustomAdapter;
import com.example.ev.SoKhop.Api.HttpDeleteOnesignalId;
import com.example.ev.SoKhop.Api.HttpGetCompanyProfile;
import com.example.ev.SoKhop.Api.HttpGetCountNotification;
import com.example.ev.SoKhop.Api.HttpGetEducation;
import com.example.ev.SoKhop.Api.HttpGetExperices;
import com.example.ev.SoKhop.Api.HttpGetImageAlbums;
import com.example.ev.SoKhop.Api.HttpGetJobExcludes;
import com.example.ev.SoKhop.Api.HttpGetJobExpect;
import com.example.ev.SoKhop.Api.HttpGetLanguage;
import com.example.ev.SoKhop.Api.HttpGetLocation;
import com.example.ev.SoKhop.Api.HttpGetPositionJobs;
import com.example.ev.SoKhop.Api.HttpGetPositionTimeline;
import com.example.ev.SoKhop.Api.HttpGetUserProfile;
import com.example.ev.SoKhop.Api.HttpLogout;
import com.example.ev.SoKhop.Api.HttpPostRegisterDeviceNotification;
import com.example.ev.SoKhop.Api.HttpUpdateAvatar;
import com.example.ev.SoKhop.Api.HttpUpdateAvatarCompany;
import com.example.ev.SoKhop.Api.HttpUpdateCoverCompany;
import com.example.ev.SoKhop.Api.HttpUpdateCoverImage;
import com.example.ev.SoKhop.Api.core.API;
import com.example.ev.SoKhop.Api.core.HttpResponseCode;
import com.example.ev.SoKhop.Base.BaseFragment;
import com.example.ev.SoKhop.Base.PermissionActivity;
import com.example.ev.SoKhop.Base.SoKhopApplication;
import com.example.ev.SoKhop.Database.DatabaseHelper;
import com.example.ev.SoKhop.Dialog.DialogCall;
import com.example.ev.SoKhop.Fragment.AddNewRecruitmentFragment;
import com.example.ev.SoKhop.Fragment.DetailRecruitmentFragment;
import com.example.ev.SoKhop.Fragment.EditExpectJobFragment;
import com.example.ev.SoKhop.Fragment.ExpectJobFragment;
import com.example.ev.SoKhop.Fragment.EditProfileCompanyFragment;
import com.example.ev.SoKhop.Fragment.EditProfileUserFragment;
import com.example.ev.SoKhop.Fragment.EducationFragment;
import com.example.ev.SoKhop.Fragment.ExpFragment;
import com.example.ev.SoKhop.Fragment.HistoryActvityFragment;
import com.example.ev.SoKhop.Fragment.HistoryDealFragment;
import com.example.ev.SoKhop.Fragment.ImageAlbumFragment;
import com.example.ev.SoKhop.Fragment.JobsFragment;
import com.example.ev.SoKhop.Fragment.LibraryFragment;
import com.example.ev.SoKhop.Fragment.ListUserSokhopFragment;
import com.example.ev.SoKhop.Fragment.MyPagesFragment;
import com.example.ev.SoKhop.Fragment.MessageFragment;
import com.example.ev.SoKhop.Fragment.InfoFragment;
import com.example.ev.SoKhop.Fragment.MyPagesToChucFragment;
import com.example.ev.SoKhop.Fragment.OtherUserPageFragment;
import com.example.ev.SoKhop.Fragment.ProfileFragment;
import com.example.ev.SoKhop.Fragment.ProfileToChucFragment;
import com.example.ev.SoKhop.Fragment.QuanLyTinTuyenDungFragment;
import com.example.ev.SoKhop.Fragment.QuanLyUngVienFragment;
import com.example.ev.SoKhop.Fragment.RecruitmentFragment;
import com.example.ev.SoKhop.Fragment.SearchAccountFragment;
import com.example.ev.SoKhop.Fragment.SearchJobFragment;
import com.example.ev.SoKhop.Fragment.SearchUserFragment;
import com.example.ev.SoKhop.Fragment.SettingFragment;
import com.example.ev.SoKhop.Fragment.SoKhopToChucFragment;
import com.example.ev.SoKhop.Interface.IActivityToFragment;
import com.example.ev.SoKhop.Interface.IFragmentToActivity;
import com.example.ev.SoKhop.Model.DataModel;
import com.example.ev.SoKhop.Model.EducationModel;
import com.example.ev.SoKhop.Model.ExcludesModel;
import com.example.ev.SoKhop.Model.ExpectJobModel;
import com.example.ev.SoKhop.Model.ExperiencesModel;
import com.example.ev.SoKhop.Model.Image;
import com.example.ev.SoKhop.Model.LanguageModel;
import com.example.ev.SoKhop.Model.LocationModel;
import com.example.ev.SoKhop.Model.PositionJobModel;
import com.example.ev.SoKhop.Model.PositionTimelineModel;
import com.example.ev.SoKhop.Model.RecruitmentModel;
import com.example.ev.SoKhop.Network.DataLoader;
import com.example.ev.SoKhop.Network.RequestResponse;
import com.example.ev.SoKhop.R;
import com.example.ev.SoKhop.Utils.ConnectivityReceiver;
import com.example.ev.SoKhop.Utils.Constants;
import com.example.ev.SoKhop.Utils.PhotoPicker;
import com.example.ev.SoKhop.Utils.Pref;
import com.example.ev.SoKhop.Utils.ProgresBar;
import com.example.ev.SoKhop.Utils.SenUpdate;
import com.example.ev.SoKhop.Utils.UpLoadImage;
import com.onesignal.OSNotification;
import com.onesignal.OSNotificationAction;
import com.onesignal.OSNotificationOpenResult;
import com.onesignal.OneSignal;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends PermissionActivity implements IFragmentToActivity,DataLoader.DataLoaderListener,ConnectivityReceiver.ConnectivityReceiverListener {

    private String[] mNavigationDrawerItemTitles;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    public static Toolbar toolbar;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    android.support.v7.app.ActionBarDrawerToggle mDrawerToggle;
    private LinearLayout layout_drawer ;
    private TextView txtSetting,txtSaveToolBar,txtLogout,txtUserName,txtTitle,txtBackToolBar;
    private Button btnCreate,btnSearchToolBar,btnDelete;
    private ImageView imgViewAvatar,imgCover;
    public PhotoPicker mPhotoPicker;
    private boolean isChangingAvatar = false;
    private ImageButton btnMyPages,btnJobs,btnFindJobs,btnSearch;
    private ImageButton btnMyPagesToChuc,btnInfoToChuc,btnMessToChuc,btnSoKhopTochuc;
    public HashMap<String, DataLoader> mLoaders;
    public Handler handler = new Handler();
    public Pref p;
    public IActivityToFragment iActivityToFragment;
    public static boolean isCaNhan = false;
    private LinearLayout bottom_tochuc,bottom_canhan;
    public DatabaseHelper databaseHelper;
    private List<Image> arrImage;
    private List<EducationModel> educationModels;
    private List<ExperiencesModel> experiencesModels;
    private List<PositionTimelineModel> positionTimelineModels;
    private List<PositionJobModel> positionJobModels;
    private List<LocationModel> locationModels;
    private List<LanguageModel> languageModels;
    private List<ExpectJobModel> expectJobModels;
    private List<RecruitmentModel> recruitmentModels;
    private List<ExcludesModel> excludesModels;
    private int pos_id,province_id;
    private String pos_name,pos_value,province_name,province_value,district,street,number;
    private ImageView btnUpDown;
    private EditText edtSearch;
    private LinearLayout layout_search;
    private boolean isShow = false;
    private ArrayList<String> arrComOrUser = new ArrayList<>();
    private ArrayList<String> arrPosition = new ArrayList<>();
    private ArrayList<String> arrExp = new ArrayList<>();
    private ArrayList<String> arrSkill = new ArrayList<>();
    private ArrayList<String> arrTimeType = new ArrayList<>();
    private ArrayList<String> arrLocation = new ArrayList<>();
    private ArrayList<String> arrSalary = new ArrayList<>();
    private ArrayList<String> arrProvinceId = new ArrayList<>();
    private ArrayList<String> arrPosId = new ArrayList<>();
    private Spinner spComOrUser,spExp,spTimeType,spLocation;
    private AutoCompleteTextView spPosition,spSkill;
    private ImageView imgSearch;
    private String jca_name,jca_value,jca_tags,jca_description,jca_created_at,jca_updated_at,lang_name,lang_code,lang_created_at,lang_updated_at;
    private String jca_id,lang_id;
    private String owner_id = "";
    private String owner_avatar = "";
    private String owner_name = "";
    private String owner_website = "";
    private String owner_phone = "";
    private static boolean activityStarted;
    private String onesignal_uid = "";
    private String gcm_id = "";
    private String deviceId;
    private ArrayAdapter<String> adapterPosition,adapterTimeType,adapterLocation,adapterSalary,adapterExp,adapterComOrUser;
    public SoKhopApplication soKhopApplication;
    private BaseFragment fragment;
    public static int mSelectedItem=0;
    private DrawerItemCustomAdapter adapter;
    private ProgressBar progressCover,progressAvatar,progressWait;
    public static TextView mTitleToolBar;
    private RelativeLayout layout_wait_loaddata;
    private LinearLayout layout_custom_fragment;
    private DataModel[] drawerItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.toolbar));
        }
        mPhotoPicker = new PhotoPicker(MainActivity.this);
        p = new Pref(MainActivity.this);
        p.putString(Pref.USER_ID,p.getString(Pref.USER_ID_REAL,null));
        p.putString(Pref.BOOLEAN_IS_ME,"0");

        deviceId = Settings.Secure.getString(getContentResolver(),
                Settings.Secure.ANDROID_ID);
        // Logging set to help debug issues, remove before releasing your app.
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.DEBUG, OneSignal.LOG_LEVEL.WARN);

        OneSignal.startInit(this)
                .setNotificationOpenedHandler(new ExampleNotificationOpenedHandler())
                .setNotificationReceivedHandler(new ExampleNotificationReceivedHandler())
                .autoPromptLocation(true)
                .init();
        OneSignal.idsAvailable(new OneSignal.IdsAvailableHandler() {
            @Override
            public void idsAvailable(String userId, String registrationId) {
                onesignal_uid = userId;
                if (registrationId != null)
                    gcm_id = registrationId;
            }
        });

        registerNotificationDevice(onesignal_uid,onesignal_uid,deviceId,"1");

//
//        if (   activityStarted
//                && getIntent() != null
//                && (getIntent().getFlags() & Intent.FLAG_ACTIVITY_REORDER_TO_FRONT) != 0) {
//            finish();
//            return;
//        }

        activityStarted = true;
        progressCover = (ProgressBar)findViewById(R.id.progressCover);
        progressAvatar = (ProgressBar)findViewById(R.id.progressAvatar);
        bottom_tochuc = (LinearLayout)findViewById(R.id.bottom_tochuc);
        bottom_canhan = (LinearLayout)findViewById(R.id.bottom_canhan);
        arrImage = new ArrayList<>();
        educationModels = new ArrayList<>();
        experiencesModels = new ArrayList<>();
        positionTimelineModels = new ArrayList<>();
        positionJobModels = new ArrayList<>();
        locationModels = new ArrayList<>();
        languageModels = new ArrayList<>();
        expectJobModels = new ArrayList<>();
        recruitmentModels = new ArrayList<>();
        excludesModels = new ArrayList<>();
        databaseHelper = new DatabaseHelper(MainActivity.this);
        imgSearch = (ImageView) findViewById(R.id.imgSearch);
        spComOrUser = (Spinner) findViewById(R.id.spComOrUser);
        spPosition = (AutoCompleteTextView) findViewById(R.id.spPosition);
        spExp = (Spinner)findViewById(R.id.spExp);
        spSkill = (AutoCompleteTextView) findViewById(R.id.spSkill);
        spTimeType = (Spinner)findViewById(R.id.spTimeType);
        spLocation = (Spinner)findViewById(R.id.spLocation);
        mTitle = mDrawerTitle = getTitle();
        mNavigationDrawerItemTitles= getResources().getStringArray(R.array.navigation_drawer_items_array);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        layout_drawer = (LinearLayout)findViewById(R.id.layout_drawer);
        txtSetting = (TextView)findViewById(R.id.imgSetting);
        txtLogout = (TextView)findViewById(R.id.imgLogout);
        btnCreate = (Button)findViewById(R.id.btnCreate);
        btnDelete = (Button)findViewById(R.id.btnDelete);
        txtBackToolBar = (TextView)findViewById(R.id.txtBackToolBar);
        btnUpDown = (ImageView)findViewById(R.id.btnUpDown);
        edtSearch = (EditText) findViewById(R.id.edtSearch);
        layout_search = (LinearLayout) findViewById(R.id.layout_search);
        btnSearchToolBar = (Button)findViewById(R.id.btnSearchToolBar);
        imgViewAvatar = (ImageView)findViewById(R.id.imageViewAvatar);
//        imgViewAvatar.setImageResource(R.drawable.avatar);
        imgCover = (ImageView)findViewById(R.id.imgCover);
        btnMyPages = (ImageButton)findViewById(R.id.btnMyPages);
        btnJobs = (ImageButton)findViewById(R.id.btnJobs);
        btnFindJobs = (ImageButton)findViewById(R.id.btnFindJobs);
        btnSearch = (ImageButton)findViewById(R.id.btnSearch);
        btnMyPagesToChuc = (ImageButton)findViewById(R.id.btnMyPagesToChuc);
        btnInfoToChuc = (ImageButton)findViewById(R.id.btnInfoToChuc);
        btnMessToChuc = (ImageButton)findViewById(R.id.btnMessToChuc);
        btnMessToChuc.setAlpha(0.5f);
        btnSoKhopTochuc = (ImageButton)findViewById(R.id.btnSoKhopTochuc);
        txtSaveToolBar = (TextView)findViewById(R.id.txtSaveToolBar);
        txtUserName = (TextView)findViewById(R.id.txtUserName);
        txtTitle = (TextView)findViewById(R.id.txtTitle);
        layout_wait_loaddata = (RelativeLayout)findViewById(R.id.layout_wait_loaddata);
        layout_wait_loaddata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        layout_custom_fragment = (LinearLayout)findViewById(R.id.layout_custom_fragment);
        databaseHelper.deleteAllJobSearch();
        txtUserName.setShadowLayer(15,0,0,R.color.toolbar);
        txtTitle.setShadowLayer(15,0,0,R.color.toolbar);
        btnSoKhopTochuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isCaNhan = false;
                p.putString(Pref.USER_ID,p.getString(Pref.USER_ID_REAL,null));
                p.putString(Pref.BOOLEAN_IS_ME,"0");
                selectItem(25);
            }
        });

        btnMyPagesToChuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p.putString(Pref.USER_ID,p.getString(Pref.USER_ID_REAL,null));
                p.putString(Pref.BOOLEAN_IS_ME,"0");
                selectItem(0);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getCompanyProfile(p.getString(Pref.PREF_KEY_TOKEN,null),p.getString(Pref.USER_ID,null),0,true);
                        getImageAlbums(p.getString(Pref.PREF_KEY_TOKEN,null),p.getString(Pref.USER_ID,null),999);
                        getEducationInfo(p.getString(Pref.PREF_KEY_TOKEN,null),p.getString(Pref.USER_ID,null),999);
                        getExpericesInfo(p.getString(Pref.PREF_KEY_TOKEN,null),p.getString(Pref.USER_ID,null),999);
                        getPositionTimeline(p.getString(Pref.PREF_KEY_TOKEN,null),p.getString(Pref.USER_ID,null));
                        getExpectJob(p.getString(Pref.PREF_KEY_TOKEN,null),p.getString(Pref.USER_ID,null),999);
                    }
                },0);
                isCaNhan = false;
                bottom_tochuc.setVisibility(View.VISIBLE);
                bottom_canhan.setVisibility(View.GONE);
            }
        });

        btnInfoToChuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isCaNhan = false;
                p.putString(Pref.USER_ID,p.getString(Pref.USER_ID_REAL,null));
                p.putString(Pref.BOOLEAN_IS_ME,"0");
                selectItem(2);
            }
        });

        btnFindJobs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p.putString(Pref.USER_ID,p.getString(Pref.USER_ID_REAL,null));
                p.putString(Pref.BOOLEAN_IS_ME,"0");
                selectItem(16);
            }
        });
        imgViewAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p.putString(Pref.NUMBER_IMAGE_CHANGE,"0");
                imgCover.setClickable(false);
                mPhotoPicker.startPick();
                isChangingAvatar = true;
            }
        });
        imgCover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p.putString(Pref.NUMBER_IMAGE_CHANGE,"1");
                imgViewAvatar.setClickable(false);
                mPhotoPicker.startPick();
                isChangingAvatar = false;
            }
        });
        txtSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSelectedItem = 999;
                adapter.notifyDataSetChanged();
                selectItem(5);
            }
        });

        txtLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogCall.ShowDialogLogout(MainActivity.this, getString(R.string.logout), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int id = v.getId();
                        if (id == R.id.btnOk) {
                            final ProgressDialog progressDialog = ProgresBar.loadingProgress(MainActivity.this);
                            progressDialog.show();
                            HttpDeleteOnesignalId httpDeleteNotification = new HttpDeleteOnesignalId(MainActivity.this,p.getString(Pref.PREF_KEY_TOKEN,null));
                            httpDeleteNotification.request(onesignal_uid, new API.APIDelegate() {
                                @Override
                                public void onRequestFinished(HttpResponseCode httpResponseCode, JSONObject jsonResponse) {
                                    if (httpResponseCode == HttpResponseCode.SUCCESS) {
                                    }
                                    HttpLogout api = new HttpLogout(MainActivity.this,p.getString(Pref.PREF_KEY_TOKEN,null));
                                    api.request(new API.APIDelegate() {
                                        @Override
                                        public void onRequestFinished(HttpResponseCode httpResponseCode, JSONObject jsonResponse) {
                                            if (httpResponseCode == HttpResponseCode.SUCCESS) {
                                                progressDialog.dismiss();
                                                p.putString(Pref.PREF_KEY_TOKEN,"");
                                                p.putString(Pref.TYPE_SEARCH_TITLE,"");
                                                p.putString(Pref.TYPE_SEARCH_POS,"");
                                                p.putString(Pref.TYPE_SEARCH_POS_ID,"");
                                                p.putString(Pref.TYPE_SEARCH_TIME_TYPE,"");
                                                p.putString(Pref.TYPE_SEARCH_TIME_TYPE_ID,"");
                                                p.putString(Pref.TYPE_SEARCH_LOCATION,"");
                                                p.putString(Pref.TYPE_SEARCH_LOCATION_ID,"");
                                                p.putString(Pref.TYPE_SEARCH_SALARY,"");
                                                p.putString(Pref.TYPE_SEARCH_EXP,"");
                                                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                                                startActivity(intent);
                                                finish();
                                            }else {
                                                progressDialog.dismiss();
                                            }
                                        }
                                    });
                                }
                            });
                        }else if (id == R.id.btnCancel) {

                        }
                    }
                });
            }
        });
        drawerItem = new DataModel[5];

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        mTitleToolBar = (TextView) toolbar.findViewById(R.id.toolbar_title);
        toolbar.setTitleTextColor(Color.parseColor("#ffffff"));
        toolbar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.toolbar)));
        mTitle = getTitle();

        adapter = new DrawerItemCustomAdapter(this, R.layout.list_view_item_row, drawerItem);
        mDrawerList.setAdapter(adapter);
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        setupDrawerToggle();
        if(p.getString(Pref.TYPE_USER,null).equals("1")){
            getUserProfile(p.getString(Pref.PREF_KEY_TOKEN,null),p.getString(Pref.USER_ID,null),0,true);
            isCaNhan = true;
            bottom_tochuc.setVisibility(View.GONE);
            bottom_canhan.setVisibility(View.VISIBLE);
        }else {
            getCompanyProfile(p.getString(Pref.PREF_KEY_TOKEN,null),p.getString(Pref.USER_ID,null),0,true);
            isCaNhan = false;
            bottom_tochuc.setVisibility(View.VISIBLE);
            bottom_canhan.setVisibility(View.GONE);
        }

        selectItem(0);
        if(isCaNhan){
            drawerItem[0] = new DataModel(R.drawable.ic_profile, getResources().getString(R.string.mypagesTab),"");
        }else {
            drawerItem[0] = new DataModel(R.drawable.ic_user_tochuc, getResources().getString(R.string.mypagesTab_tochuc),"");
        }
        drawerItem[1] = new DataModel(R.drawable.ic_messenger,  getResources().getString(R.string.messageTab),"");
        drawerItem[2] = new DataModel(R.drawable.ic_notification,  getResources().getString(R.string.infoTab),"");
        drawerItem[3] = new DataModel(R.drawable.ic_timeline,  getResources().getString(R.string.historyActivityTab),"");
        drawerItem[4] = new DataModel(R.drawable.ic_list,  getResources().getString(R.string.historyDealTab),"");
        getCountNotification(p.getString(Pref.PREF_KEY_TOKEN,null));
        btnMyPages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p.putString(Pref.USER_ID,p.getString(Pref.USER_ID_REAL,null));
                p.putString(Pref.BOOLEAN_IS_ME,"0");
                selectItem(0);
                if(p.getString(Pref.TYPE_USER,null).equals("1")){
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            getUserProfile(p.getString(Pref.PREF_KEY_TOKEN,null),p.getString(Pref.USER_ID,null),0,true);
                            getImageAlbums(p.getString(Pref.PREF_KEY_TOKEN,null),p.getString(Pref.USER_ID,null),999);
                            getEducationInfo(p.getString(Pref.PREF_KEY_TOKEN,null),p.getString(Pref.USER_ID,null),999);
                            getExpericesInfo(p.getString(Pref.PREF_KEY_TOKEN,null),p.getString(Pref.USER_ID,null),999);
                            getPositionTimeline(p.getString(Pref.PREF_KEY_TOKEN,null),p.getString(Pref.USER_ID,null));
                            getExpectJob(p.getString(Pref.PREF_KEY_TOKEN,null),p.getString(Pref.USER_ID,null),999);
                        }
                    },0);
                    isCaNhan = true;
                    bottom_tochuc.setVisibility(View.GONE);
                    bottom_canhan.setVisibility(View.VISIBLE);
                }else {

                }
            }
        });
        btnJobs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p.putString(Pref.USER_ID,p.getString(Pref.USER_ID_REAL,null));
                p.putString(Pref.BOOLEAN_IS_ME,"0");
                p.putString(Pref.JOB_TAB,"0");
                selectItem(6);
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p.putString(Pref.USER_ID,p.getString(Pref.USER_ID_REAL,null));
                p.putString(Pref.BOOLEAN_IS_ME,"0");
                if(spComOrUser.getSelectedItemPosition()==0){
                    selectItem(23);
                }else if(spComOrUser.getSelectedItemPosition()==1){
                    selectItem(24);
                }else {
                    selectItem(27);
                }
            }
        });

        arrComOrUser.add(getString(R.string.bandangtimkiemvieclam));
        arrComOrUser.add(getString(R.string.bandangtimkiemtuyendung));
        arrComOrUser.add(getString(R.string.bandangtimkiemnguoidungkhac));
        adapterComOrUser = new ArrayAdapter<String>(MainActivity.this,R.layout.spinner_item, arrComOrUser);
        spComOrUser.setAdapter(adapterComOrUser);

        if(p.getString(Pref.TYPE_SEARCH,null)!=null){
            if(p.getString(Pref.TYPE_SEARCH,null).equals("1")){
                spComOrUser.setSelection(0);
            }else if(p.getString(Pref.TYPE_SEARCH,null).equals("2")){
                spComOrUser.setSelection(1);
            }else{
                spComOrUser.setSelection(2);
            }
        }

        spComOrUser.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){
                    p.putString(Pref.TYPE_SEARCH,"1");
                    addSpinnerSearch(1);
                }else if(position == 1){
                    p.putString(Pref.TYPE_SEARCH,"2");
                    addSpinnerSearch(2);
                }else if(position == 2){
                    p.putString(Pref.TYPE_SEARCH,"3");
                    addSpinnerSearch(3);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        imgSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(spComOrUser.getSelectedItemPosition()==0){
                    String province_id = getString(R.string.defaut_search);
                    if(spSkill.getText().toString().length()==0){
                        province_id =  getString(R.string.defaut_search_0);
                    }else {
                        for (int i = 0;i<arrLocation.size();i++){
                            if(arrLocation.get(i).equals(spSkill.getText().toString())){
                                province_id = arrProvinceId.get(i);
                            }
                        }
                    }
                    String pos_id = getString(R.string.defaut_search);
                    if(spPosition.getText().toString().length()==0){
                        pos_id =  getString(R.string.defaut_search_0);
                    }else {
                        for (int i = 0;i<arrPosition.size();i++){
                            if(arrPosition.get(i).equals(spPosition.getText().toString())){
                                pos_id = arrPosId.get(i);
                            }
                        }
                    }
                    String time_type = "";
                    if(spExp.getSelectedItem().equals(getString(R.string.fulltime))){
                        time_type = "2";
                    }else if(spExp.getSelectedItem().equals(getString(R.string.parttime))){
                        time_type = "1";
                    }
                    String salary = "";
                    if(spTimeType.getSelectedItemPosition()==0){
                        salary = "<1000000";
                    }else  if(spTimeType.getSelectedItemPosition()==1){
                        salary = "1000000-5000000";
                    }else if(spTimeType.getSelectedItemPosition()==2){
                        salary = "5000000-8000000";
                    }else if(spTimeType.getSelectedItemPosition()==3){
                        salary = "8000000-15000000";
                    }else if(spTimeType.getSelectedItemPosition()==4){
                        salary = "15000000-30000000";
                    }else if(spTimeType.getSelectedItemPosition()==5){
                        salary = ">30000000";
                    }else {
                        salary = "";
                    }
                    p.putString(Pref.TYPE_SEARCH_TITLE,edtSearch.getText().toString());
                    p.putString(Pref.TYPE_SEARCH_POS,spPosition.getText().toString().replace(getString(R.string.spniner_position),""));
                    p.putString(Pref.TYPE_SEARCH_POS_ID,pos_id);
                    p.putString(Pref.TYPE_SEARCH_TIME_TYPE,spExp.getSelectedItem().toString().replace(getString(R.string.spniner_timeType),""));
                    p.putString(Pref.TYPE_SEARCH_TIME_TYPE_ID,time_type);
                    p.putString(Pref.TYPE_SEARCH_LOCATION,spSkill.getText().toString().replace(getString(R.string.spniner_location),""));
                    p.putString(Pref.TYPE_SEARCH_LOCATION_ID,province_id);
                    p.putString(Pref.TYPE_SEARCH_SALARY,salary.replace(getString(R.string.spniner_salary),""));
                    selectItem(23);
//                    getJobsSearch(p.getString(Pref.PREF_KEY_TOKEN,null),province_id,pos_id,time_type,salary,23,edtSearch.getText().toString(),"1");
                }else  if(spComOrUser.getSelectedItemPosition()==1){
                    String province_id = getString(R.string.defaut_search);
                    if(spSkill.getText().toString().length()==0){
                        province_id =  getString(R.string.defaut_search_0);
                    }else {
                        for (int i = 0;i<arrLocation.size();i++){
                            if(arrLocation.get(i).equals(spSkill.getText().toString())){
                                province_id = arrProvinceId.get(i);
                            }
                        }
                    }
                    String pos_id = getString(R.string.defaut_search);
                    if(spPosition.getText().toString().length()==0){
                        pos_id =  getString(R.string.defaut_search_0);
                    }else {
                    for (int i = 0;i<arrPosition.size();i++){
                        if(arrPosition.get(i).equals(spPosition.getText().toString())){
                            pos_id = arrPosId.get(i);
                        }
                    }
                    }
                    String time_type = "";
                    if(spTimeType.getSelectedItem().equals(getString(R.string.fulltime))){
                        time_type = "2";
                    }else if(spTimeType.getSelectedItem().equals(getString(R.string.parttime))){
                        time_type = "1";
                    }
                    p.putString(Pref.TYPE_SEARCH_TITLE,edtSearch.getText().toString());
                    p.putString(Pref.TYPE_SEARCH_POS,spPosition.getText().toString().replace(getString(R.string.spniner_position),""));
                    p.putString(Pref.TYPE_SEARCH_POS_ID,pos_id);
                    p.putString(Pref.TYPE_SEARCH_EXP,spExp.getSelectedItem().toString().replace(getString(R.string.spniner_exp),""));
                    p.putString(Pref.TYPE_SEARCH_LOCATION,spSkill.getText().toString().replace(getString(R.string.spniner_location),""));
                    p.putString(Pref.TYPE_SEARCH_LOCATION_ID,province_id);
                    p.putString(Pref.TYPE_SEARCH_TIME_TYPE,spTimeType.getSelectedItem().toString().replace(getString(R.string.spniner_timeType),""));
                    p.putString(Pref.TYPE_SEARCH_TIME_TYPE_ID,time_type);
                    selectItem(24);
                }else {
                    p.putString(Pref.TYPE_SEARCH_TITLE,edtSearch.getText().toString());
                    edtSearch.setText(p.getString(Pref.TYPE_SEARCH_TITLE,null));
                    selectItem(27);
                }

            }
        });

        edtSearch.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                isShow = true;
                layout_search.setVisibility(View.VISIBLE);
                btnUpDown.setImageResource(R.drawable.ic_fillter_up);
                return false;
            }
        });
        checkConnection();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                getImageAlbums(p.getString(Pref.PREF_KEY_TOKEN,null),p.getString(Pref.USER_ID,null),999);
                getEducationInfo(p.getString(Pref.PREF_KEY_TOKEN,null),p.getString(Pref.USER_ID,null),999);
                getExpericesInfo(p.getString(Pref.PREF_KEY_TOKEN,null),p.getString(Pref.USER_ID,null),999);
                getPositionTimeline(p.getString(Pref.PREF_KEY_TOKEN,null),p.getString(Pref.USER_ID,null));
                getPositionJobs(p.getString(Pref.PREF_KEY_TOKEN,null));
                getLocation(p.getString(Pref.PREF_KEY_TOKEN,null));
                getLanguage(p.getString(Pref.PREF_KEY_TOKEN,null));
                getExpectJob(p.getString(Pref.PREF_KEY_TOKEN,null),p.getString(Pref.USER_ID,null),999);
                getExcludeJob(p.getString(Pref.PREF_KEY_TOKEN,null));
            }
        },0);
//        getRecruitment(p.getString(Pref.PREF_KEY_TOKEN,null),999);

        if (   activityStarted
                && getIntent() != null
                && (getIntent().getFlags() & Intent.FLAG_ACTIVITY_REORDER_TO_FRONT) != 0) {
            finish();
            return;
        }

        activityStarted = true;
    }


    public void resetDataNavigation(String numberNotifi){
        if(isCaNhan){
            drawerItem[0] = new DataModel(R.drawable.ic_profile, getResources().getString(R.string.mypagesTab),"");
        }else {
            drawerItem[0] = new DataModel(R.drawable.ic_user_tochuc, getResources().getString(R.string.mypagesTab_tochuc),"");
        }
        drawerItem[1] = new DataModel(R.drawable.ic_messenger,  getResources().getString(R.string.messageTab),"");
        drawerItem[2] = new DataModel(R.drawable.ic_notification,  getResources().getString(R.string.infoTab),numberNotifi);
        drawerItem[3] = new DataModel(R.drawable.ic_timeline,  getResources().getString(R.string.historyActivityTab),"");
        drawerItem[4] = new DataModel(R.drawable.ic_list,  getResources().getString(R.string.historyDealTab),"");
        adapter.notifyDataSetChanged();
    }

    private void registerNotificationDevice(String onesignal_uid, String gcm_id, String device_id, String device_type){
        HttpPostRegisterDeviceNotification httpPostRegisterDeviceNotification = new HttpPostRegisterDeviceNotification(this,p.getString(Pref.PREF_KEY_TOKEN,null));
        httpPostRegisterDeviceNotification.request(onesignal_uid, gcm_id, device_id, device_type, new API.APIDelegate() {
            @Override
            public void onRequestFinished(HttpResponseCode httpResponseCode, JSONObject jsonResponse) {
                if (httpResponseCode == HttpResponseCode.SUCCESS) {
                }
            }
        });
    }


    private void addSpinnerSearch(int isJob){
        if(adapterPosition!=null){
            adapterPosition.clear();
            adapterPosition.notifyDataSetChanged();
        }
        if(adapterTimeType!=null){
            adapterTimeType.clear();
            adapterTimeType.notifyDataSetChanged();
        }
        if(adapterLocation!=null){
            adapterLocation.clear();
            adapterLocation.notifyDataSetChanged();
        }
        if(adapterSalary!=null){
            adapterSalary.clear();
            adapterSalary.notifyDataSetChanged();
        }
        if(adapterExp!=null){
            adapterExp.clear();
            adapterExp.notifyDataSetChanged();
        }
        if(positionJobModels.size()>0){
            positionJobModels.clear();
        }
        if(locationModels.size()>0){
            locationModels.clear();
        }
        if(arrPosition.size()>0){
            arrPosition.clear();
        }
        if(arrPosId.size()>0){
            arrPosId.clear();
        }
        if(locationModels.size()>0){
            locationModels.clear();
        }
        if(arrLocation.size()>0){
            arrLocation.clear();
        }
        if(arrProvinceId.size()>0){
            arrProvinceId.clear();
        }
        if(arrTimeType.size()>0){
            arrTimeType.clear();
        }
        if(arrSalary.size()>0){
            arrSalary.clear();
        }
        if(arrExp.size()>0){
            arrExp.clear();
        }
        positionJobModels = new ArrayList<>();
        locationModels = new ArrayList<>();

        positionJobModels = this.databaseHelper.getAllPositionJobs();
        for(int i =0;i<positionJobModels.size();i++){
            arrPosition.add(positionJobModels.get(i).getPos_value());
            arrPosId.add(String.valueOf(positionJobModels.get(i).getPos_id()));
        }

        locationModels = this.databaseHelper.getAllLocation();
        for(int i =0;i<locationModels.size();i++){
            arrLocation.add(locationModels.get(i).getLocat_value());
            arrProvinceId.add(String.valueOf(locationModels.get(i).getLocat_id()));
        }
        arrTimeType.add(getString(R.string.fulltime));
        arrTimeType.add(getString(R.string.parttime));
//        arrTimeType.add(getString(R.string.theoduan));
//        arrTimeType.add(getString(R.string.theomuavu));
        arrSalary.add(getString(R.string.duoi1trieu));
        arrSalary.add(getString(R.string.tu1den3));
        arrSalary.add(getString(R.string.tu5den8));
        arrSalary.add(getString(R.string.tu8den15));
        arrSalary.add(getString(R.string.tu15den30));
        arrSalary.add(getString(R.string.tren30trieu));
        arrExp.add(getString(R.string.khong_yeu_cau));
        arrExp.add(getString(R.string.mot_nam));
        arrExp.add(getString(R.string.hai_nam));
        arrExp.add(getString(R.string.ba_nam));
        arrExp.add(getString(R.string.bon_nam));
        arrExp.add(getString(R.string.nam_nam));
        arrExp.add(getString(R.string.hon_nam_nam));

        arrTimeType.add(getString(R.string.spniner_timeType));
        arrExp.add(getString(R.string.spniner_exp));
        arrSalary.add(getString(R.string.spniner_salary));
//        arrProvinceId.add("");
//        arrLocation.add(getString(R.string.spniner_location));
//        arrPosId.add("");
//        arrPosition.add(getString(R.string.spniner_position));

        adapterPosition = new ArrayAdapter<String>(MainActivity.this,R.layout.spinner_item, arrPosition);
        adapterTimeType = new ArrayAdapter<String>(MainActivity.this,R.layout.spinner_item, arrTimeType);
        adapterLocation = new ArrayAdapter<String>(MainActivity.this,R.layout.spinner_item, arrLocation);
        adapterSalary = new ArrayAdapter<String>(MainActivity.this,R.layout.spinner_item, arrSalary);
        adapterExp = new ArrayAdapter<String>(MainActivity.this,R.layout.spinner_item, arrExp);

        if(isJob == 1){
            spPosition.setVisibility(View.VISIBLE);
            spExp.setVisibility(View.VISIBLE);
            spSkill.setVisibility(View.VISIBLE);
            spTimeType.setVisibility(View.VISIBLE);
            spLocation.setVisibility(View.GONE);
            spPosition.setAdapter(adapterPosition);
            spExp.setAdapter(adapterTimeType);
            spSkill.setAdapter(adapterLocation);
            spTimeType.setAdapter(adapterSalary);
//            spPosition.setSelection(adapterPosition.getCount()-1);
            spExp.setSelection(adapterTimeType.getCount()-1);
//            spSkill.setSelection(adapterLocation.getCount()-1);
            spTimeType.setSelection(adapterSalary.getCount()-1);
//            addDataToSpinner(spKinhNghiem,arrExp);
        }else if(isJob == 2){
            spPosition.setVisibility(View.VISIBLE);
            spExp.setVisibility(View.VISIBLE);
            spSkill.setVisibility(View.VISIBLE);
            spTimeType.setVisibility(View.VISIBLE);
            spLocation.setVisibility(View.GONE);
            spPosition.setAdapter(adapterPosition);
            spExp.setAdapter(adapterExp);
            spSkill.setAdapter(adapterLocation);
            spTimeType.setAdapter(adapterTimeType);
//            spPosition.setSelection(adapterPosition.getCount()-1);
            spExp.setSelection(adapterExp.getCount()-1);
//            spSkill.setSelection(adapterLocation.getCount()-1);
            spTimeType.setSelection(adapterTimeType.getCount()-1);
//            addDataToSpinner(spKinhNghiem,arrExp);
        }else {
            spPosition.setVisibility(View.GONE);
            spExp.setVisibility(View.GONE);
            spSkill.setVisibility(View.GONE);
            spTimeType.setVisibility(View.GONE);
            spLocation.setVisibility(View.GONE);
        }

    }

    public void getUserProfile(String token, String user_id, final int pos,final boolean isMe){
        layout_wait_loaddata.setVisibility(View.VISIBLE);
        HttpGetUserProfile api = new HttpGetUserProfile(MainActivity.this,token,user_id);
        api.request(new API.APIDelegate() {
            @Override
            public void onRequestFinished(HttpResponseCode httpResponseCode, JSONObject jsonResponse) {
                if (httpResponseCode == HttpResponseCode.SUCCESS) {
                    Log.d("avatarPath","ss"+jsonResponse);
                    try {
                        JSONObject data = jsonResponse.getJSONObject("data");
                        String avatar = data.getString("upb_avatar_url").replace("null","");
                        String cover = data.getString("upb_cover_url").replace("null","");
                        p.putString(Pref.PREF_KEY_AVATAR,avatar);
                        p.putString(Pref.PREF_KEY_COVER,cover);
                        p.putString(Pref.upb_id,data.getString("upb_id").replace("null",""));
                        p.putString(Pref.upb_gender,data.getString("upb_gender").replace("null",""));
                        p.putString(Pref.upb_dob,data.getString("upb_dob").replace("null",""));
                        p.putString(Pref.upb_first_name,data.getString("upb_first_name").replace("null",""));
                        p.putString(Pref.upb_middle_name,data.getString("upb_middle_name").replace("null",""));
                        p.putString(Pref.upb_last_name,data.getString("upb_last_name").replace("null",""));
                        p.putString(Pref.upb_full_name,data.getString("upb_full_name").replace("null",""));
                        p.putString(Pref.upb_status,data.getString("upb_status").replace("null",""));
                        p.putString(Pref.upb_fate,data.getString("upb_fate").replace("null",""));
                        p.putString(Pref.upb_appearance,data.getString("upb_appearance").replace("null",""));
                        p.putString(Pref.upb_phone,data.getString("upb_phone").replace("null",""));
                        p.putString(Pref.upb_email,data.getString("upb_email").replace("null",""));
                        p.putString(Pref.upb_address,data.getString("upb_address").replace("null",""));
                        p.putString(Pref.upb_address_temp,data.getString("upb_address_temp").replace("null",""));
                        p.putString(Pref.upb_social_networks,data.getString("upb_social_networks").replace("null",""));
                        p.putString(Pref.upb_height,data.getString("upb_height").replace("null",""));
                        p.putString(Pref.upb_weight,data.getString("upb_weight").replace("null",""));
                        p.putString(Pref.upb_measurements,data.getString("upb_measurements").replace("null",""));
                        p.putString(Pref.upb_hobbies,data.getString("upb_hobbies").replace("null",""));
                        p.putString(Pref.upb_skills,data.getString("upb_skills").replace("null",""));
                        p.putString(Pref.upb_speaks,data.getString("upb_speaks").replace("null",""));
                        p.putString(Pref.upb_soft_skills,data.getString("upb_soft_skills").replace("null",""));
                        p.putString(Pref.upb_speaks_url,data.getString("upb_speaks_url").replace("null",""));
                        p.putString(Pref.upb_sings,data.getString("upb_sings").replace("null",""));
                        p.putString(Pref.upb_sings_url,data.getString("upb_sings_url").replace("null",""));
                        p.putString(Pref.upb_special_skills,data.getString("upb_special_skills").replace("null",""));
                        p.putString(Pref.upb_website,data.getString("upb_website").replace("null",""));
                        p.putString(Pref.upb_marriage_status,data.getString("upb_marriage_status").replace("null",""));
                        p.putString(Pref.upb_description,data.getString("upb_description").replace("null",""));
                        p.putString(Pref.upb_image_url,data.getString("upb_image_url").replace("null",""));
                        p.putString(Pref.upb_video_url,data.getString("upb_video_url").replace("null",""));
                        p.putString(Pref.upb_created_at,data.getString("upb_created_at").replace("null",""));
                        p.putString(Pref.upb_updated_at,data.getString("upb_updated_at").replace("null",""));
                        if(isMe){
                            if(avatar.length()>5){
                                Glide.with(MainActivity.this).load(avatar).listener(new RequestListener<String, GlideDrawable>() {
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
                                }).into(imgViewAvatar);
                            }else {
                                imgViewAvatar.setImageResource(R.drawable.avatar);
                            }
                            if(cover.length()>5) {
                                Glide.with(MainActivity.this).load(cover).listener(new RequestListener<String, GlideDrawable>() {
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
                            }else {
                                imgCover.setImageResource(R.drawable.cover);
                            }
                            txtUserName.setText(p.getString(Pref.upb_full_name,null));
                            txtTitle.setText(p.getString(Pref.upb_status,null));
                        }
                        selectItem(pos);
                        layout_wait_loaddata.setVisibility(View.GONE);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }else {
                }
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        p.putString(Pref.USER_ID,p.getString(Pref.USER_ID_REAL,null));
        p.putString(Pref.BOOLEAN_IS_ME,"0");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        p.putString(Pref.USER_ID,p.getString(Pref.USER_ID_REAL,null));
        p.putString(Pref.BOOLEAN_IS_ME,"0");
    }

    public void getCompanyProfile(String token, String user_id, final int pos,final boolean isMe){
        layout_wait_loaddata.setVisibility(View.VISIBLE);
        HttpGetCompanyProfile api = new HttpGetCompanyProfile(MainActivity.this,token,user_id);
        api.request(new API.APIDelegate() {
            @Override
            public void onRequestFinished(HttpResponseCode httpResponseCode, JSONObject jsonResponse) {
                if (httpResponseCode == HttpResponseCode.SUCCESS) {
                    try {
                        JSONObject data = jsonResponse.getJSONObject("data");
                        String avatar = data.getString("com_avatar_url").replace("null","");
                        String cover = data.getString("com_cover_url").replace("null","");
                        p.putString(Pref.com_avatar_url,avatar);
                        p.putString(Pref.com_cover_url,cover);
                        p.putString(Pref.com_id,data.getString("com_id").replace("null",""));
                        p.putString(Pref.com_user_id,data.getString("com_user_id").replace("null",""));
                        p.putString(Pref.com_name,data.getString("com_name").replace("null",""));
                        p.putString(Pref.com_status,data.getString("com_status").replace("null",""));
                        p.putString(Pref.com_categories,data.getString("com_categories").replace("null",""));
                        p.putString(Pref.com_address,data.getString("com_address").replace("null",""));
                        p.putString(Pref.com_size,data.getString("com_size"));
                        p.putString(Pref.com_employee_count,data.getString("com_employee_count").replace("null",""));
                        p.putString(Pref.com_website,data.getString("com_website").replace("null",""));
                        p.putString(Pref.com_phone,data.getString("com_phone").replace("null",""));
                        p.putString(Pref.com_time_working,data.getString("com_time_working").replace("null",""));
                        p.putString(Pref.com_type,data.getString("com_type").replace("null",""));
                        p.putString(Pref.com_email,data.getString("com_email").replace("null",""));
                        p.putString(Pref.com_social_networks,data.getString("com_social_networks").replace("null",""));
                        p.putString(Pref.com_introduce,data.getString("com_introduce").replace("null",""));
                        p.putString(Pref.com_country,data.getString("com_country").replace("null",""));
                        p.putString(Pref.com_logo_url,data.getString("com_logo_url").replace("null",""));
                        p.putString(Pref.com_img_url,data.getString("com_img_url").replace("null",""));
                        p.putString(Pref.com_representation_name,data.getString("com_representation_name").replace("null",""));
                        p.putString(Pref.com_representation_email,data.getString("com_representation_email").replace("null",""));
                        p.putString(Pref.com_representation_phone,data.getString("com_representation_phone").replace("null",""));
                        p.putString(Pref.com_representation_img,data.getString("com_representation_img").replace("null",""));
                        p.putString(Pref.com_clothes,data.getString("com_clothes").replace("null",""));
                        p.putString(Pref.com_tax_code,data.getString("com_tax_code").replace("null",""));
                        p.putString(Pref.com_allowance,data.getString("com_allowance").replace("null",""));
                        p.putString(Pref.com_rules,data.getString("com_rules").replace("null",""));
                        p.putString(Pref.com_register_img,data.getString("com_register_img").replace("null",""));
                        p.putString(Pref.com_contact_name,data.getString("com_contact_name").replace("null",""));
                        p.putString(Pref.com_contact_phone,data.getString("com_contact_phone").replace("null",""));
                        p.putString(Pref.com_contact_email,data.getString("com_contact_email").replace("null",""));
                        p.putString(Pref.com_contact_address,data.getString("com_contact_address").replace("null",""));
                        p.putString(Pref.com_contact_position,data.getString("com_contact_position").replace("null",""));
                        p.putString(Pref.com_created_at,data.getString("com_created_at").replace("null",""));
                        p.putString(Pref.com_updated_at,data.getString("com_updated_at").replace("null",""));
                        if(isMe) {
                            if (avatar.length() > 4) {
                                Glide.with(MainActivity.this).load(avatar).listener(new RequestListener<String, GlideDrawable>() {
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
                                }).into(imgViewAvatar);
                            } else {
                                imgViewAvatar.setImageResource(R.drawable.avatar);
                            }
                            if (cover.length() > 4) {
                                Glide.with(MainActivity.this).load(cover).listener(new RequestListener<String, GlideDrawable>() {
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
                                imgCover.setImageResource(R.drawable.cover);
                            }
                            txtUserName.setText(p.getString(Pref.com_name,null));
                            txtTitle.setText(p.getString(Pref.com_status,null));
                        }
                        selectItem(pos);
                        layout_wait_loaddata.setVisibility(View.GONE);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }else {
                }
            }
        });
    }
    public void hideDeleteItem() {
        btnDelete.setVisibility(View.GONE);
    }

    public void displayDeleteItem(){
        btnDelete.setVisibility(View.VISIBLE);
    }

    public void hideTxtSaveToolBar(){
        txtSaveToolBar.setVisibility(View.INVISIBLE);
    }
    public void getImageAlbums(String token,String owner_id, final int pos){
        HttpGetImageAlbums api = new HttpGetImageAlbums(MainActivity.this,token,owner_id);
        api.request(new API.APIDelegate() {
            @Override
            public void onRequestFinished(HttpResponseCode httpResponseCode, JSONObject jsonResponse) {
                if (httpResponseCode == HttpResponseCode.SUCCESS) {
                    databaseHelper.deleteAllAlbum();
                    try {
                        JSONArray data = jsonResponse.getJSONArray("albums");
                        for(int i = 0;i<data.length();i++){
                            JSONObject jsonObject = data.getJSONObject(i);
                            String albumName = jsonObject.getString("alb_name").replace("null","");
                            int img_album_id = jsonObject.getInt("alb_id");
                            JSONArray jsonArray = jsonObject.getJSONArray("alb_images");
                            int total_img_same_al = jsonArray.length();
                            if(jsonArray.length()==0){
                                databaseHelper.insertAImage(img_album_id,0,0,0,0,
                                        0,total_img_same_al,null,null,null,null,null,albumName);
                                arrImage = databaseHelper.getAllImage();
                                if(pos != 999){
                                    selectItem(pos);
                                }
                            }
                            for (int j = 0;j<jsonArray.length();j++){
                                JSONObject json_image = jsonArray.getJSONObject(j);
//                                int img_album_id = json_image.getInt("img_album_id");
                                int img_height = json_image.getInt("img_height");
                                int img_id = json_image.getInt("img_id");
                                int img_owner_id = json_image.getInt("img_owner_id");
                                int img_position = 1;
                                int img_width = json_image.getInt("img_width");
                                String img_created_at = json_image.getString("img_created_at").replace("null","");
                                String img_tags = json_image.getString("img_tags").replace("null","");
                                String img_updated_at = json_image.getString("img_updated_at").replace("null","");
                                String img_uuid = json_image.getString("img_uuid").replace("null","");
                                JSONObject object_url_image = json_image.getJSONObject("img_file_url");
                                String img_file_url = object_url_image.getString("default").replace("null","");
                                databaseHelper.insertAImage(img_album_id,img_height,img_id,img_owner_id,img_position,
                                        img_width,total_img_same_al,img_created_at,img_tags,img_updated_at,img_uuid,img_file_url,albumName);
                                arrImage = databaseHelper.getAllImage();
                                if(pos != 999){
                                    selectItem(pos);
                                }
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }else {
                }
            }
        });
    }

    public void getExpectJob(String token,String user_id, final int pos){
        HttpGetJobExpect api = new HttpGetJobExpect(MainActivity.this,token,user_id);
        api.request(new API.APIDelegate() {
            @Override
            public void onRequestFinished(HttpResponseCode httpResponseCode, JSONObject jsonResponse) {
                if (httpResponseCode == HttpResponseCode.SUCCESS) {
                    databaseHelper.deleteAllExpectJob();
                    try {
                        JSONObject data = jsonResponse.getJSONObject("data");

                        if(data.length()>10) {
                            JSONArray locationArray = data.getJSONArray("locations");
                            for (int i = 0; i < locationArray.length(); i++) {
                                JSONObject locationObject = locationArray.getJSONObject(i);
                                if(locationObject.getString("province_id").replace("null","").length()>0){
                                    province_id = Integer.parseInt(locationObject.getString("province_id").replace("null",""));
                                }else {
                                    province_id = 0;
                                }
                                province_name = locationObject.getString("province_name").replace("null","");
                                province_value = locationObject.getString("province_value").replace("null","");
                                district = locationObject.getString("district").replace("null","");
                                street = locationObject.getString("street").replace("null","");
                                number = locationObject.getString("number").replace("null","");
                            }
                            JSONArray possArray = data.getJSONArray("positions");
                            for (int i = 0; i < possArray.length(); i++) {
                                JSONObject posObject = possArray.getJSONObject(i);
                                if(posObject.getString("id").replace("null","").length()>0){
                                    pos_id = Integer.parseInt(posObject.getString("id").replace("null",""));
                                }else {
                                    pos_id = 0;
                                }
                                pos_name = posObject.getString("name").replace("null","");
                                pos_value = posObject.getString("value").replace("null","");
                            }
                            int expect_job = data.getInt("expect_job");
                            int user_id = data.getInt("user_id");
                            String salary_basic = data.getString("salary_basic").replace("null","");
                            String bonus = data.getString("bonus").replace("null","");
                            String allowance = data.getString("allowance").replace("null","");
                            String objective = data.getString("objective").replace("null","");
                            String description = data.getString("description").replace("null","");
                            String time_type = data.getString("time_type").replace("null","");
                            databaseHelper.insertAExpectJob(expect_job, user_id, pos_id, province_id, salary_basic,
                                    bonus, allowance, objective, description, pos_name, pos_value, province_name, province_value, district, street, number, time_type);
                            expectJobModels = databaseHelper.getAllExpectJob();
                            if (pos != 999) {
                                selectItem(pos);
                            }
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }else {
                }
            }
        });
    }

    public void getPositionTimeline(String token,String id){
        HttpGetPositionTimeline api = new HttpGetPositionTimeline(MainActivity.this,token,id);
        api.request(new API.APIDelegate() {
            @Override
            public void onRequestFinished(HttpResponseCode httpResponseCode, JSONObject jsonResponse) {
                if (httpResponseCode == HttpResponseCode.SUCCESS) {
                    databaseHelper.deleteAllPositionTimeline();
                    try {
                        JSONArray data = jsonResponse.getJSONArray("data");
                        for(int i = 0;i<data.length();i++){
                            JSONObject jsonObject = data.getJSONObject(i);
                            int user_id = jsonObject.getInt("user_id");
                            int position_id = jsonObject.getInt("position_id");
                            String start_time = jsonObject.getString("start_time").replace("null","");
                            String end_time = jsonObject.getString("end_time").replace("null","");
                            String position_name = jsonObject.getString("position_name").replace("null","");
                            String position_value = jsonObject.getString("position_value").replace("null","");
                            String diff_time = jsonObject.getString("diff_time").replace("null","");
                            databaseHelper.insertAPositionTimeline(user_id,position_id,start_time,end_time,position_name,
                                    position_value,diff_time);
                            positionTimelineModels = databaseHelper.getAllPositionTimeline();
//                            if(pos != 999){
//                                selectItem(pos);
//                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }else {
                }
            }
        });
    }
    public void getPositionJobs(String token){
        HttpGetPositionJobs api = new HttpGetPositionJobs(MainActivity.this,token);
        api.request(new API.APIDelegate() {
            @Override
            public void onRequestFinished(HttpResponseCode httpResponseCode, JSONObject jsonResponse) {
                if (httpResponseCode == HttpResponseCode.SUCCESS) {
                    databaseHelper.deleteAllPositionJobs();
                    try {
                        JSONArray data = jsonResponse.getJSONArray("data");
                        for(int i = 0;i<data.length();i++){
                            JSONObject jsonObject = data.getJSONObject(i);
                            int pos_id = jsonObject.getInt("pos_id");
                            int pos_is_active = jsonObject.getInt("pos_is_active");
                            String pos_name = jsonObject.getString("pos_name").replace("null","");
                            String pos_value = jsonObject.getString("pos_value").replace("null","");
                            String pos_description = jsonObject.getString("pos_description").replace("null","");
                            String pos_created_at = jsonObject.getString("pos_created_at").replace("null","");
                            String pos_updated_at = jsonObject.getString("pos_updated_at").replace("null","");
                            databaseHelper.insertAPositionJobs(pos_id,pos_is_active,pos_name,pos_value,pos_description,pos_created_at,
                                    pos_updated_at);
                            positionJobModels = databaseHelper.getAllPositionJobs();
//                            if(pos != 999){
//                                selectItem(pos);
//                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }else {
                }
            }
        });
    }
    public void getLocation(String token){
        HttpGetLocation api = new HttpGetLocation(MainActivity.this,token);
        api.request(new API.APIDelegate() {
            @Override
            public void onRequestFinished(HttpResponseCode httpResponseCode, JSONObject jsonResponse) {
                if (httpResponseCode == HttpResponseCode.SUCCESS) {
                    databaseHelper.deleteAllLocation();
                    try {
                        JSONArray data = jsonResponse.getJSONArray("data");
                        for(int i = 0;i<data.length();i++){
                            JSONObject jsonObject = data.getJSONObject(i);
                            int locat_id = jsonObject.getInt("locat_id");
                            int locat_is_active = jsonObject.getInt("locat_is_active");
                            String locat_name = jsonObject.getString("locat_name").replace("null","");
                            String locat_value = jsonObject.getString("locat_value").replace("null","");
                            String locat_description = jsonObject.getString("locat_description").replace("null","");
                            String locat_created_at = jsonObject.getString("locat_created_at").replace("null","");
                            String locat_updated_at = jsonObject.getString("locat_updated_at").replace("null","");
                            databaseHelper.insertALocation(locat_id,locat_is_active,locat_name,locat_value,locat_description,
                                    locat_created_at,locat_updated_at);
                            locationModels = databaseHelper.getAllLocation();
//                            if(pos != 999){
//                                selectItem(pos);
//                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }else {
                }
            }
        });
    }
    public void getLanguage(String token){
        HttpGetLanguage api = new HttpGetLanguage(MainActivity.this,token);
        api.request(new API.APIDelegate() {
            @Override
            public void onRequestFinished(HttpResponseCode httpResponseCode, JSONObject jsonResponse) {
                if (httpResponseCode == HttpResponseCode.SUCCESS) {
                    databaseHelper.deleteAllLanguage();
                    try {
                        JSONArray data = jsonResponse.getJSONArray("data");
                        for(int i = 0;i<data.length();i++){
                            JSONObject jsonObject = data.getJSONObject(i);
                            int lang_id = jsonObject.getInt("lang_id");
                            String lang_name = jsonObject.getString("lang_name").replace("null","");
                            String lang_code = jsonObject.getString("lang_code").replace("null","");
                            String lang_created_at = jsonObject.getString("lang_created_at").replace("null","");
                            String lang_updated_at = jsonObject.getString("lang_updated_at").replace("null","");
                            databaseHelper.insertALanguage(lang_id,lang_name,lang_code,lang_created_at,
                                    lang_updated_at);
                            languageModels = databaseHelper.getAllLanguage();
//                            if(pos != 999){
//                                selectItem(pos);
//                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }else {
                }
            }
        });
    }
    public void getEducationInfo(String token,String id, final int pos){
        HttpGetEducation api = new HttpGetEducation(MainActivity.this,token,id);
        api.request(new API.APIDelegate() {
            @Override
            public void onRequestFinished(HttpResponseCode httpResponseCode, JSONObject jsonResponse) {
                if (httpResponseCode == HttpResponseCode.SUCCESS) {
                    databaseHelper.deleteAllEducation();
                    try {
                        JSONArray data = jsonResponse.getJSONArray("data");
                        for(int i = 0;i<data.length();i++){
                            JSONObject jsonObject = data.getJSONObject(i);
                            int schm_id = jsonObject.getInt("schm_id");
                            int upe_user_id = jsonObject.getInt("upe_user_id");
                            String upe_degree = jsonObject.getString("upe_degree");
                            int upe_id = jsonObject.getInt("upe_id");
                            String maj_name = jsonObject.getString("maj_name").replace("null","");
                            String sch_name = jsonObject.getString("sch_name").replace("null","");
                            String upe_video_url = jsonObject.getString("upe_video_url").replace("null","");
                            String upe_img_url = jsonObject.getString("upe_img_url").replace("null","");
                            String upe_start_time = jsonObject.getString("upe_start_time").replace("null","");
                            String upe_end_time = jsonObject.getString("upe_end_time").replace("null","");
                            String upe_description = jsonObject.getString("upe_description").replace("null","");
                            databaseHelper.insertAEducation(schm_id,upe_user_id,upe_degree,upe_id,maj_name,
                                    sch_name,upe_video_url,upe_img_url,upe_start_time,upe_end_time,upe_description);
                            educationModels = databaseHelper.getAllEducation();
                            if(pos != 999){
                                selectItem(pos);
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }else {
                }
            }
        });
    }

    public void getExpericesInfo(String token,String id, final int pos){
        HttpGetExperices api = new HttpGetExperices(MainActivity.this,token,id);
        api.request(new API.APIDelegate() {
            @Override
            public void onRequestFinished(HttpResponseCode httpResponseCode, JSONObject jsonResponse) {
                if (httpResponseCode == HttpResponseCode.SUCCESS) {
                    Log.d("experiencesModels","ss"+jsonResponse);
                    databaseHelper.deleteAllExperices();
                    try {
                        JSONArray data = jsonResponse.getJSONArray("data");
                        for(int i = 0;i<data.length();i++){
                            JSONObject jsonObject = data.getJSONObject(i);
                            int uex_user_id = jsonObject.getInt("uex_user_id");
                            int pos_id = jsonObject.getInt("pos_id");
                            int uex_company_id = jsonObject.getInt("uex_company_id");
                            int upos_year_of_working = jsonObject.getInt("upos_year_of_working");
                            String uex_skills = jsonObject.getString("uex_skills").replace("null","");
                            String pos_name = jsonObject.getString("pos_name").replace("null","");
                            String pos_value = jsonObject.getString("pos_value").replace("null","");
                            String pos_description = jsonObject.getString("pos_description").replace("null","");
                            String uex_company_name = jsonObject.getString("uex_company_name").replace("null","");
                            String uex_img_url = jsonObject.getString("uex_img_url").replace("null","");
                            String uex_video_url = jsonObject.getString("uex_video_url").replace("null","");
                            String uex_description = jsonObject.getString("uex_description").replace("null","");
                            String uex_start_time = jsonObject.getString("uex_start_time").replace("null","");
                            String uex_end_time = jsonObject.getString("uex_end_time").replace("null","");
                            String uex_id = jsonObject.getString("uex_id").replace("null","");
                            databaseHelper.insertAExperiences(uex_user_id,pos_id,uex_company_id,upos_year_of_working,uex_skills,
                                    pos_name,pos_value,pos_description,uex_company_name,uex_img_url,uex_video_url,uex_description,uex_start_time,uex_end_time,uex_id);
                            experiencesModels = databaseHelper.getAllExperices();
                            if(pos != 999){
                                selectItem(pos);
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }else {
                }
            }
        });
    }

    public void getExcludeJob(String token){
        HttpGetJobExcludes api = new HttpGetJobExcludes(MainActivity.this,token);
        api.request(new API.APIDelegate() {
            @Override
            public void onRequestFinished(HttpResponseCode httpResponseCode, JSONObject jsonResponse) {
                if (httpResponseCode == HttpResponseCode.SUCCESS) {
                    databaseHelper.deleteAllExcludesJob();
                    try {
                        JSONArray data = jsonResponse.getJSONArray("data");
                        for(int i = 0;i<data.length();i++){
                            JSONObject jsonObject = data.getJSONObject(i);
                            String jexca_id = jsonObject.getString("jexca_id").replace("null","");
                            String jexca_name = jsonObject.getString("jexca_name").replace("null","");
                            String jexca_description = jsonObject.getString("jexca_description").replace("null","");
                            String jexca_created_at = jsonObject.getString("jexca_created_at").replace("null","");
                            String jexca_updated_at = jsonObject.getString("jexca_updated_at").replace("null","");
                            databaseHelper.insertAExcludesJob(jexca_id,jexca_name,jexca_description,jexca_created_at,jexca_updated_at);
                        }
                        excludesModels = databaseHelper.getAllExcludesJob();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }else {
                }
            }
        });
    }

    @Override
    public void onStart(DataLoader sender) {

    }

    @Override
    public void onCompleted(DataLoader sender, RequestResponse response, Object result, final int fragment) {
        Log.d("getStatusCode","ss"+response.getStatusCode()+"ss"+fragment);
        if(response.getStatusCode()==200) {
            if (fragment == 1) {
                if (isChangingAvatar) {
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            getImageAlbums(p.getString(Pref.PREF_KEY_TOKEN, null), p.getString(Pref.USER_ID, null), 999);
                            final String imagepath = p.getString(Pref.PREF_KEY_AVATAR, null);
                            if (imagepath != null) {
                                Glide.with(MainActivity.this).load(imagepath).listener(new RequestListener<String, GlideDrawable>() {
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
                                }).into(imgViewAvatar);
                                if (isCaNhan) {
                                    SenUpdate.updateAvatar(p.getString(Pref.PREF_KEY_TOKEN, null), imagepath, MainActivity.this);
                                } else {
                                    SenUpdate.updateAvatarCompany(p.getString(Pref.PREF_KEY_TOKEN, null), imagepath, MainActivity.this);
                                }
                                if (iActivityToFragment != null) {
                                    iActivityToFragment.refreshCoverImageFragment();
                                }
                            }
                        }
                    }, 0);
                } else {
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            final String imagepath = p.getString(Pref.PREF_KEY_COVER, null);
                            getImageAlbums(p.getString(Pref.PREF_KEY_TOKEN, null), p.getString(Pref.USER_ID, null), 999);
                            if (imagepath != null) {
                                Glide.with(MainActivity.this).load(imagepath).listener(new RequestListener<String, GlideDrawable>() {
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
                                if (isCaNhan) {
                                    SenUpdate.updateCoverImage(p.getString(Pref.PREF_KEY_TOKEN, null), imagepath, MainActivity.this);
                                } else {
                                    SenUpdate.updateCoverImageCompany(p.getString(Pref.PREF_KEY_TOKEN, null), imagepath, MainActivity.this);
                                }
                                if (iActivityToFragment != null) {
                                    iActivityToFragment.refreshCoverImageFragment();
                                }
                            }
                        }
                    }, 0);
                }
            }else if(fragment == 999){

            } else {
                if(result.toString().equals("1")){
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            final String imagepath = p.getString(Pref.PREF_KEY_AVATAR, null);
                            if (imagepath != null) {
                                Glide.with(MainActivity.this).load(imagepath).into(imgViewAvatar);
                                if (isCaNhan) {
                                    HttpUpdateAvatar api = new HttpUpdateAvatar(MainActivity.this,p.getString(Pref.PREF_KEY_TOKEN, null));
                                    api.request(imagepath,new API.APIDelegate() {
                                        @Override
                                        public void onRequestFinished(HttpResponseCode httpResponseCode, JSONObject jsonResponse) {
                                            if (httpResponseCode == HttpResponseCode.SUCCESS) {
                                                handler.postDelayed(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        getImageAlbums(p.getString(Pref.PREF_KEY_TOKEN, null), p.getString(Pref.USER_ID, null), 999);
                                                        if (isCaNhan) {
                                                            getUserProfile(p.getString(Pref.PREF_KEY_TOKEN, null), p.getString(Pref.USER_ID, null), fragment, true);
                                                        } else {
                                                            getCompanyProfile(p.getString(Pref.PREF_KEY_TOKEN, null), p.getString(Pref.USER_ID, null), fragment, true);
                                                        }
                                                    }
                                                }, 1000);
                                            }
                                        }
                                    });
//                                    if(SenUpdate.updateAvatar(p.getString(Pref.PREF_KEY_TOKEN, null), imagepath, MainActivity.this)==200){
//                                        getImageAlbums(p.getString(Pref.PREF_KEY_TOKEN, null), p.getString(Pref.USER_ID, null), 999);
//                                        if(isCaNhan){
//                                            getUserProfile(p.getString(Pref.PREF_KEY_TOKEN,null),p.getString(Pref.USER_ID,null),fragment,true);
//                                        }else {
//                                            getCompanyProfile(p.getString(Pref.PREF_KEY_TOKEN,null),p.getString(Pref.USER_ID,null),fragment,true);
//                                        }
//                                    }else {
//                                        handler.postDelayed(new Runnable() {
//                                            @Override
//                                            public void run() {
//                                                getImageAlbums(p.getString(Pref.PREF_KEY_TOKEN, null), p.getString(Pref.USER_ID, null), 999);
//                                                if(isCaNhan){
//                                                    getUserProfile(p.getString(Pref.PREF_KEY_TOKEN,null),p.getString(Pref.USER_ID,null),fragment,true);
//                                                }else {
//                                                    getCompanyProfile(p.getString(Pref.PREF_KEY_TOKEN,null),p.getString(Pref.USER_ID,null),fragment,true);
//                                                }
//                                            }
//                                        },1000);
//                                    }
                                } else {
                                    HttpUpdateAvatarCompany api = new HttpUpdateAvatarCompany(MainActivity.this,p.getString(Pref.PREF_KEY_TOKEN, null));
                                    api.request(imagepath,new API.APIDelegate() {
                                        @Override
                                        public void onRequestFinished(HttpResponseCode httpResponseCode, JSONObject jsonResponse) {
                                            if (httpResponseCode == HttpResponseCode.SUCCESS) {
                                                handler.postDelayed(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        getImageAlbums(p.getString(Pref.PREF_KEY_TOKEN, null), p.getString(Pref.USER_ID, null), 999);
                                                        if(isCaNhan){
                                                            getUserProfile(p.getString(Pref.PREF_KEY_TOKEN,null),p.getString(Pref.USER_ID,null),fragment,true);
                                                        }else {
                                                            getCompanyProfile(p.getString(Pref.PREF_KEY_TOKEN,null),p.getString(Pref.USER_ID,null),fragment,true);
                                                        }
                                                    }
                                                },1000);
                                            }else {
                                            }

                                        }
                                    });
                                }
                                if (iActivityToFragment != null) {
                                    iActivityToFragment.refreshCoverImageFragment();
                                }
                            }
//                            getImageAlbums(p.getString(Pref.PREF_KEY_TOKEN, null), p.getString(Pref.USER_ID, null), 999);
//                            if(isCaNhan){
//                                getUserProfile(p.getString(Pref.PREF_KEY_TOKEN,null),p.getString(Pref.USER_ID,null),fragment,true);
//                            }else {
//                                getCompanyProfile(p.getString(Pref.PREF_KEY_TOKEN,null),p.getString(Pref.USER_ID,null),fragment,true);
//                            }
                        }
                    }, 0);
                }else if(result.toString().equals("2")){
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            final String imagepath = p.getString(Pref.PREF_KEY_COVER, null);
                            if (imagepath != null) {
                                Glide.with(MainActivity.this).load(imagepath).into(imgCover);
                                if (isCaNhan) {
                                    HttpUpdateCoverImage api = new HttpUpdateCoverImage(MainActivity.this,p.getString(Pref.PREF_KEY_TOKEN, null));
                                    api.request(imagepath,new API.APIDelegate() {
                                        @Override
                                        public void onRequestFinished(HttpResponseCode httpResponseCode, JSONObject jsonResponse) {
                                            if (httpResponseCode == HttpResponseCode.SUCCESS) {
                                                getImageAlbums(p.getString(Pref.PREF_KEY_TOKEN, null), p.getString(Pref.USER_ID, null), 999);
                                                if(isCaNhan){
                                                    getUserProfile(p.getString(Pref.PREF_KEY_TOKEN,null),p.getString(Pref.USER_ID,null),fragment,true);
                                                }else {
                                                    getCompanyProfile(p.getString(Pref.PREF_KEY_TOKEN,null),p.getString(Pref.USER_ID,null),fragment,true);
                                                }
                                            }else {
                                            }
                                        }
                                    });
                                } else {
                                    HttpUpdateCoverCompany api = new HttpUpdateCoverCompany(MainActivity.this,p.getString(Pref.PREF_KEY_TOKEN, null));
                                    api.request(imagepath,new API.APIDelegate() {
                                        @Override
                                        public void onRequestFinished(HttpResponseCode httpResponseCode, JSONObject jsonResponse) {
                                            if (httpResponseCode == HttpResponseCode.SUCCESS) {
                                                getImageAlbums(p.getString(Pref.PREF_KEY_TOKEN, null), p.getString(Pref.USER_ID, null), 999);
                                                if(isCaNhan){
                                                    getUserProfile(p.getString(Pref.PREF_KEY_TOKEN,null),p.getString(Pref.USER_ID,null),fragment,true);
                                                }else {
                                                    getCompanyProfile(p.getString(Pref.PREF_KEY_TOKEN,null),p.getString(Pref.USER_ID,null),fragment,true);
                                                }
                                            }else {
                                            }
                                        }
                                    });
                                }
                                if (iActivityToFragment != null) {
                                    iActivityToFragment.refreshCoverImageFragment();
                                }
                            }
//                            getImageAlbums(p.getString(Pref.PREF_KEY_TOKEN, null), p.getString(Pref.USER_ID, null), 999);
//                            if(isCaNhan){
//                                getUserProfile(p.getString(Pref.PREF_KEY_TOKEN,null),p.getString(Pref.USER_ID,null),fragment,true);
//                            }else {
//                                getCompanyProfile(p.getString(Pref.PREF_KEY_TOKEN,null),p.getString(Pref.USER_ID,null),fragment,true);
//                            }
                        }
                    }, 0);
                }
            }
        } else {
            DialogCall.showResponse(this, getString(R.string.upload_image_fail), new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    progressCover.setVisibility(View.GONE);
                    progressAvatar.setVisibility(View.GONE);
                }
            });
        }
    }

    @Override
    public void onCompleted(DataLoader sender, RequestResponse response) {

    }

    @Override
    public void onCompletedDUpImage(DataLoader sender, RequestResponse response, Object result, int fragment) {
        getImageAlbums(p.getString(Pref.PREF_KEY_TOKEN,null),p.getString(Pref.USER_ID,null),15);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        String photoPath = mPhotoPicker.onActivityResult(requestCode, resultCode, data);
        if(photoPath!=null) {
            if(photoPath.length() > 1) {
                final Pref p = new Pref(MainActivity.this);
                if (isChangingAvatar) {
                    UpLoadImage.uploadImage(photoPath, MainActivity.this, mLoaders, 1, Constants.AVATAR,1);
                    progressAvatar.setVisibility(View.VISIBLE);
                } else {
                    UpLoadImage.uploadImage(photoPath, MainActivity.this, mLoaders, 2,Constants.COVER,1);
                    progressCover.setVisibility(View.VISIBLE);
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    @Override
    protected void onResume() {
        super.onResume();
        // register connection status listener
        SoKhopApplication.getInstance().setConnectivityListener(this);
        getCountNotification(p.getString(Pref.PREF_KEY_TOKEN,null));
        imgViewAvatar.setClickable(true);
        imgCover.setClickable(true);
        String avatarPath = p.getString(Pref.PREF_KEY_AVATAR,null);
        String coverPath = p.getString(Pref.PREF_KEY_COVER,null);
        if(avatarPath!=null){
            if(avatarPath.length()>4){
                Glide.with(MainActivity.this).load(avatarPath).into(imgViewAvatar);
            }else {
                imgViewAvatar.setImageResource(R.drawable.avatar);
            }
        }
        if(coverPath!=null) {
            if(coverPath.length()>4) {
                Glide.with(MainActivity.this).load(coverPath).into(imgCover);
            }else {
                imgCover.setImageResource(R.drawable.cover);
            }
        }
    }

    @Override
    public void refreshAvatarActivity() {
        final Pref p = new Pref(MainActivity.this);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                final String imagepath = p.getString(Pref.PREF_KEY_AVATAR, null);
                if (imagepath != null) {
                    Glide.with(MainActivity.this).load(imagepath).into(imgViewAvatar);
                }
            }
        }, 1000);
    }
    @Override
    public void refreshCoverImageActivity() {
        final Pref p = new Pref(MainActivity.this);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                final String imagepath = p.getString(Pref.PREF_KEY_COVER, null);
                if (imagepath != null) {
                    Glide.with(MainActivity.this).load(imagepath).into(imgCover);
                }
            }
        }, 1000);
    }

    // Method to manually check connection status
    private void checkConnection() {
        boolean isConnected = ConnectivityReceiver.isConnected();
        if(!isConnected){
            showSnack(isConnected);
        }
    }
    // Showing the status in Snackbar
    private void showSnack(boolean isConnected) {
        String message;
        int color;
//        if (isConnected) {
//            message = "Good! Connected to Internet";
//            color = Color.WHITE;
//        } else {
//            message = "Sorry! Not connected to internet";
//            color = Color.RED;
//        }
        message = getString(R.string.no_internet);
        color = Color.RED;
        Snackbar snackbar = Snackbar
                .make(mDrawerLayout, message, Snackbar.LENGTH_LONG);
        snackbar.setActionTextColor(Color.RED);
        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(color);
        snackbar.show();
    }
    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        if(!isConnected){
            showSnack(isConnected);
        }
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            mSelectedItem = position;
            adapter.notifyDataSetChanged();
            if(position == 1||position == 3||position == 4){

            }else {
                if(position == 0){
                    p.putString(Pref.USER_ID,p.getString(Pref.USER_ID_REAL,null));
                    p.putString(Pref.BOOLEAN_IS_ME,"0");
                    if(p.getString(Pref.TYPE_USER,null).equals("1")){
                        getUserProfile(p.getString(Pref.PREF_KEY_TOKEN,null),p.getString(Pref.USER_ID,null),0,true);
                        getImageAlbums(p.getString(Pref.PREF_KEY_TOKEN,null),p.getString(Pref.USER_ID,null),999);
                        getEducationInfo(p.getString(Pref.PREF_KEY_TOKEN,null),p.getString(Pref.USER_ID,null),999);
                        getExpericesInfo(p.getString(Pref.PREF_KEY_TOKEN,null),p.getString(Pref.USER_ID,null),999);
                        getPositionTimeline(p.getString(Pref.PREF_KEY_TOKEN,null),p.getString(Pref.USER_ID,null));
                        getExpectJob(p.getString(Pref.PREF_KEY_TOKEN,null),p.getString(Pref.USER_ID,null),999);
                        isCaNhan = true;
                        bottom_tochuc.setVisibility(View.GONE);
                        bottom_canhan.setVisibility(View.VISIBLE);
                    }else {
                        getCompanyProfile(p.getString(Pref.PREF_KEY_TOKEN,null),p.getString(Pref.USER_ID,null),0,true);
                        getImageAlbums(p.getString(Pref.PREF_KEY_TOKEN,null),p.getString(Pref.USER_ID,null),999);
                        getEducationInfo(p.getString(Pref.PREF_KEY_TOKEN,null),p.getString(Pref.USER_ID,null),999);
                        getExpericesInfo(p.getString(Pref.PREF_KEY_TOKEN,null),p.getString(Pref.USER_ID,null),999);
                        getPositionTimeline(p.getString(Pref.PREF_KEY_TOKEN,null),p.getString(Pref.USER_ID,null));
                        getExpectJob(p.getString(Pref.PREF_KEY_TOKEN,null),p.getString(Pref.USER_ID,null),999);
                        isCaNhan = false;
                        bottom_tochuc.setVisibility(View.VISIBLE);
                        bottom_canhan.setVisibility(View.GONE);
                    }
                }
                selectItem(position);
            }
        }
    }

    public void selectItem(int position) {

        fragment = null;
        btnUpDown.setVisibility(View.GONE);
        edtSearch.setVisibility(View.GONE);
        layout_search.setVisibility(View.GONE);
        isShow = false;
        switch (position) {
            case 0:
                if(isCaNhan){
                    fragment = new MyPagesFragment();
                    selectedUser(true,false,false,false);
                }else {
                    fragment = new MyPagesToChucFragment();
                    selectedCompany(true,false,false,false);
                }
                break;
            case 1:
                fragment = new MessageFragment();
                break;
            case 2:
                if(isCaNhan){
                    selectedUser(true,false,false,false);
                }else {
                    selectedCompany(false,false,true,false);
                }
                fragment = new InfoFragment();
                break;
            case 3:
                fragment = new HistoryActvityFragment();
                break;
            case 4:
                fragment = new HistoryDealFragment();
                break;
            case 5:
                fragment = new SettingFragment();
                break;
            case 6:
                fragment = new JobsFragment();
                selectedUser(false,true,false,false);
                break;
            case 7:
                fragment = new ProfileFragment();
                selectedUser(true,false,false,false);
                break;
            case 8:
                fragment = new LibraryFragment();
                selectedUser(true,false,false,false);
                break;
            case 9:
                fragment = new EducationFragment();
                selectedUser(true,false,false,false);
                break;
            case 10:
                fragment = new ExpFragment();
                selectedUser(true,false,false,false);
                break;
            case 11:
                fragment = new ExpectJobFragment();
                selectedUser(true,false,false,false);
                break;
            case 12:
                fragment = new ProfileToChucFragment();
                selectedCompany(true,false,false,false);
                break;
            case 13:
                fragment = new EditProfileUserFragment();
                selectedUser(true,false,false,false);
                break;
            case 14:
                fragment = new EditProfileCompanyFragment();
                break;
            case 15:
                fragment = new ImageAlbumFragment();
                selectedUser(true,false,false,false);
                break;
            case 16:
                fragment = new RecruitmentFragment();
                selectedUser(false,false,true,false);
                break;
            case 17:
                fragment = new QuanLyTinTuyenDungFragment();
                selectedUser(false,false,true,false);
                break;
            case 18:
                fragment = new AddNewRecruitmentFragment();
                selectedUser(false,false,true,false);
                break;
            case 19:
                fragment = new EditExpectJobFragment();
                selectedUser(true,false,false,false);
                break;
            case 20:
                fragment = new DetailRecruitmentFragment();
                if(p.getString(Pref.BOOLEAN_IS_FROM_JOB,null)!=null) {
                    if (p.getString(Pref.BOOLEAN_IS_FROM_JOB, null).equals("2")) {
                        selectedUser(false, false, true, false);
                    } else {
                        selectedUser(false, true, false, false);
                    }
                }else {
                    selectedUser(false, false, true, false);
                }
                break;
            case 21:
                fragment = new OtherUserPageFragment();
                selectedUser(false,false,true,false);
                break;
            case 22:
                fragment = new QuanLyUngVienFragment();
                selectedUser(false,false,true,false);
                break;
            case 23:
                fragment = new SearchJobFragment();
                selectedUser(false,false,false,true);
                break;
            case 24:
                fragment = new SearchUserFragment();
                selectedUser(false,false,false,true);
                break;
            case 25:
                fragment = new SoKhopToChucFragment();
                selectedCompany(false,false,false,true);
                break;
            case 26:
                fragment = new ListUserSokhopFragment();
                selectedCompany(false,false,false,true);
                break;
            case 27:
                fragment = new SearchAccountFragment();
                selectedCompany(false,false,false,true);
                break;
            default:
                break;
        }

        if (fragment != null) {
            new Handler().post(new Runnable() {
                public void run() {
                    if (!isFinishing()) {
                        FragmentManager fragmentManager = getSupportFragmentManager();
                        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
                    }
                }
            });

            mDrawerList.setItemChecked(position, true);
            mDrawerList.setSelection(position);

            if(position == 0){
                if(isCaNhan){
                    mTitleToolBar.setText(R.string.trangcanhan);
//                    toolbar.setTitle(R.string.trangcanhan);
                }else {
                    mTitleToolBar.setText(R.string.trangtochuc);
//                    toolbar.setTitle(R.string.trangtochuc);
                }
            }else {
                mTitleToolBar.setText(mNavigationDrawerItemTitles[position]);
//                toolbar.setTitle(mNavigationDrawerItemTitles[position]);
            }
            mDrawerLayout.closeDrawer(layout_drawer);

        } else {
            Log.e("MainActivity", "Error in creating fragment");
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void selectedUser(boolean slMyPages,boolean slJob,boolean slRecruitment,boolean slSearch){
        btnMyPages.setSelected(slMyPages);
        btnJobs.setSelected(slJob);
        btnFindJobs.setSelected(slRecruitment);
        btnSearch.setSelected(slSearch);
    }
    private void selectedCompany(boolean slMyPages,boolean slMess,boolean slInfo,boolean slSoKhop){
        btnMyPagesToChuc.setSelected(slMyPages);
        btnMessToChuc.setSelected(slMess);
        btnInfoToChuc.setSelected(slInfo);
        btnSoKhopTochuc.setSelected(slSoKhop);
    }
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    void setupDrawerToggle(){
        mDrawerToggle = new android.support.v7.app.ActionBarDrawerToggle(this,mDrawerLayout,toolbar,R.string.app_name, R.string.app_name);
        //This is necessary to change the icon of the Drawer Toggle upon state change.
        mDrawerToggle.syncState();
    }

    public void showItemToolBar(boolean isCreate,boolean isSearch, final View.OnClickListener listener ){
        txtSaveToolBar.setVisibility(View.INVISIBLE);
        btnDelete.setVisibility(View.INVISIBLE);
        btnUpDown.setVisibility(View.INVISIBLE);
        edtSearch.setVisibility(View.INVISIBLE);
        txtBackToolBar.setVisibility(View.INVISIBLE);
        if(isCreate){
            btnCreate.setVisibility(View.VISIBLE);
            btnSearchToolBar.setVisibility(View.INVISIBLE);
        }else if(isSearch){
            btnSearchToolBar.setVisibility(View.VISIBLE);
            btnCreate.setVisibility(View.INVISIBLE);
        }else {
            btnSearchToolBar.setVisibility(View.INVISIBLE);
            btnCreate.setVisibility(View.INVISIBLE);
        }

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener != null){
                    listener.onClick(v);
                }
            }
        });
        btnSearchToolBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener != null){
                    listener.onClick(v);
                }
            }
        });
    }
    public void showSearchItem(final View.OnClickListener listener){
        btnUpDown.setVisibility(View.VISIBLE);
        edtSearch.setVisibility(View.VISIBLE);
        btnUpDown.setImageResource(R.drawable.ic_fillter);
        btnUpDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener != null){
                    listener.onClick(v);
                    if(isShow){
                        isShow = false;
                        layout_search.setVisibility(View.GONE);
                        btnUpDown.setImageResource(R.drawable.ic_fillter);
                    }else {
                        isShow = true;
                        layout_search.setVisibility(View.VISIBLE);
                        btnUpDown.setImageResource(R.drawable.ic_fillter_up);
                    }
                }
            }
        });
    }

    public void showDeleteItem(final View.OnClickListener listener){
        btnDelete.setVisibility(View.VISIBLE);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener != null){
                    listener.onClick(v);
                }
            }
        });
    }
    public void showBackItem(final View.OnClickListener listener){
        txtBackToolBar.setVisibility(View.VISIBLE);
        txtBackToolBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener != null){
                    listener.onClick(v);
                }
            }
        });
    }
    public void showSaveItem(boolean isDel,final View.OnClickListener listener){
        if(isDel){
            txtSaveToolBar.setText(getString(R.string.delete));
        }
        txtSaveToolBar.setVisibility(View.VISIBLE);
        txtSaveToolBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener != null){
                    listener.onClick(v);
                }
            }
        });
    }

    private ArrayAdapter<String> addDataToSpinner(ArrayList<String> list){
        ArrayAdapter<String> adp = new ArrayAdapter<String>(MainActivity.this,R.layout.spinner_item, list);
        adp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        return adp;
    }
    private class ExampleNotificationReceivedHandler implements OneSignal.NotificationReceivedHandler {
        @Override
        public void notificationReceived(OSNotification notification) {
            JSONObject data = notification.payload.additionalData;
            String customKey;
            getCountNotification(p.getString(Pref.PREF_KEY_TOKEN,null));
            if (data != null) {
                customKey = data.optString("customkey", null);
                if (customKey != null)
                    Log.i("OneSignalExample", "customkey set with value: " + customKey);
            }
        }
    }

    public void getCountNotification(String token){
        HttpGetCountNotification api = new HttpGetCountNotification(this,token);
        api.request(new API.APIDelegate() {
            @Override
            public void onRequestFinished(HttpResponseCode httpResponseCode, JSONObject jsonResponse) {
                if (httpResponseCode == HttpResponseCode.SUCCESS) {
                    try {
                        String numberNotifi = jsonResponse.getString("count");
                        Log.d("numberNotifi","ss"+numberNotifi);
                        resetDataNavigation(numberNotifi);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }else {
                }
            }
        });
    }
    private class ExampleNotificationOpenedHandler implements OneSignal.NotificationOpenedHandler {
        // This fires when a notification is opened by tapping on it.
        @Override
        public void notificationOpened(OSNotificationOpenResult result) {
            OSNotificationAction.ActionType actionType = result.action.type;
            JSONObject data = result.notification.payload.additionalData;
            String customKey;
            if (data != null) {
                customKey = data.optString("customkey", null);
                if (customKey != null)
                    Log.i("OneSignalExample", "customkey set with value: " + customKey);
            }

            if (actionType == OSNotificationAction.ActionType.ActionTaken)
                Log.i("OneSignalExample", "Button pressed with id: " + result.action.actionID);

            // The following can be used to open an Activity of your choice.

//             Intent intent = new Intent(getmApplication(), MainActivity.class);
//             intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_NEW_TASK);
//             startActivity(intent);

            // Add the following to your AndroidManifest.xml to prevent the launching of your main Activity
            //  if you are calling startActivity above.
         /*
            <application ...>
              <meta-data android:name="com.onesignal.NotificationOpened.DEFAULT" android:value="DISABLE" />
            </application>
         */
        }
    }
}
