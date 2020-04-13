package com.example.ev.SoKhop.Network;

import android.content.Context;
import android.util.Log;

import com.example.ev.SoKhop.Base.BaseData;
import com.example.ev.SoKhop.Utils.Common;
import com.example.ev.SoKhop.Utils.ErrorUtils;
import com.example.ev.SoKhop.Utils.Pref;
import com.example.ev.SoKhop.Utils.Utils;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.lang.ref.WeakReference;
import java.lang.reflect.Type;

/**
 * Loads data from network. Supports response caching, and parsing json data to java objects 
 *
 */
public class DataLoader {

	private ServiceConnection.TYPE_HTTP mTypeHttp = ServiceConnection.TYPE_HTTP.FORM_DATA;
	private ServiceConnection.TYPE_PARSE mTypeParse = ServiceConnection.TYPE_PARSE.OBJECT;

    public DataLoader(ServiceConnection.TYPE_PARSE mTypeParse) {
		super();
		this.mTypeParse = mTypeParse;
	}


    
	public ServiceConnection.TYPE_PARSE getTypeParse() {
		return mTypeParse;
	}
	public void setTypeParse(ServiceConnection.TYPE_PARSE mTypeParse) {
		this.mTypeParse = mTypeParse;
	}
	public ServiceConnection.TYPE_HTTP getTypeHttp() {
		return mTypeHttp;
	}
	public void setTypeHttp(ServiceConnection.TYPE_HTTP mTypeHttp) {
		this.mTypeHttp = mTypeHttp;
	}

	String id;
    public void setId(String id) {
        this.id = id;
    }
    public String getId() {
        return id;
    }

    public String dataHistory = "";
    public String urlDebugHistory = "";

	public interface DataLoaderListener {
		void onStart(DataLoader sender);
		void onCompleted(DataLoader sender, RequestResponse response, Object result,int fragment);
		void onCompleted(DataLoader sender, RequestResponse response);
		void onCompletedDUpImage(DataLoader sender, RequestResponse response, Object result,int fragment);
        //void onNoNetworkEvent();
	}

	private WeakReference<Context> mContext;

	protected Context getContext() {
		return mContext.get();
	}

	private DataLoaderListener mDataLoaderListener;
	private ResponseCache mCache;

	public void setDataLoaderListener(DataLoaderListener l) {
		mDataLoaderListener = l;
	}

	public DataLoader(Context context) {
		mContext = new WeakReference<Context>(context);
		File cacheDir = context.getDir("cache", 0);
		mCache = new ResponseCache(cacheDir);
	}


    public Object readFromCache(Type resultType, String url, String... params) {
        return null;
    }

	protected void postOnStartLoading() {
		if (mDataLoaderListener != null)
			mDataLoaderListener.onStart(DataLoader.this);
	}

	/**
	 * Sets timeout value of local cached response.
	 *
	 * @param timeout timeout value in milliseconds. Negative value means never timeout.
	 */
	public void setResponseCacheTimeout(long timeout) {
		mCache.setCacheExpireTime(timeout);
	}

    private String mCurrentUrl;
    public String getCurrentUrl() {
        return mCurrentUrl;
    }

	private Thread mLoadingThread;
	
	public void uploadImagesByPost(final Type resultType, final String url, final RequestParams param, final Context context,final int imageView, final int isFragment) {
		uploadImagesByPostOrGet(resultType, url, ServiceConnection.POST, param,context,imageView,isFragment);
	}
	
	public void uploadImagesByGet(final Type resultType, final String url, final RequestParams param, final Context context,final int imageView, final int isFragment) {
		uploadImagesByPostOrGet(resultType, url, ServiceConnection.GET, param,context,imageView,isFragment);
	}
	
	public void startLoadDataByPost(final Type resultType, final String url, final Context context,final int imageView, final int isFragment, final String... params) {
//		startLoadDataByPostOrPut(resultType, ServiceConnection.POST, url, true,context,imageView,isFragment, 0, params);
	}
	
	public void startLoadDataByPut(final Type resultType, final String url, final Context context,final int imageView, final int isFragment, final String... params) {
//		startLoadDataByPostOrPut(resultType, ServiceConnection.PUT, url, true,context,imageView,isFragment, 0, params);
	}

	

	public void startLoadDataByPostNoCache(final Type resultType, final String url, final Context context,final int imageView, final int isFragment, final String... params) {
//		startLoadDataByPostOrPut(resultType, ServiceConnection.POST, url, false,context,imageView,isFragment, 0, params);
	}
	
	public void startLoadDataByPutNoCache(final Type resultType, final String url, final Context context,final int imageView, final int isFragment, final String... params) {
//		startLoadDataByPostOrPut(resultType, ServiceConnection.PUT, url, false,context,imageView,isFragment, 0, params);
	}



    boolean mUseDefaultParams = true;
    public void setUseDefaultParams(boolean use) {
        mUseDefaultParams = use;
    }
    
    private void uploadImagesByPostOrGet(final Type resultType, final String url, final int postOrGet, final RequestParams param, final Context context, final int imageView, final int isFragment) {
		mCurrentUrl = url;

		if (mLoadingThread != null && mLoadingThread.isAlive())
			cancelLoading();

		mCancelled = false;
		mLoadingThread = new Thread("DataLoaderThread") {
			@Override
			public void run() {
				if (getContext() == null)
					return;
				postOnStartLoading();
				RequestResponse response;
				try {

					final Context c = mContext.get();

//					if (c != null && !Utils.hasNetworkConnection(c)) {
//                        postLoadingCompleteNoNetwork(c);
//						return;
//					}
					ServiceConnection conn = new ServiceConnection(mTypeHttp, Common.BA_USR, Common.BA_PWD);
					response = conn.postOrPutFiles2(url, postOrGet, param);
					postLoadingComplete(url, response, resultType,context,imageView,isFragment);

				} catch (Exception e) {
					e.printStackTrace();
					response = new RequestResponse();
					postLoadingComplete(url, response, resultType,context,imageView,isFragment);
				}
			}
		};
		
		mLoadingThread.start();
	}

    
//	private void startLoadDataByPostOrPut(final Type resultType, final int type, final String url, final boolean useResponseCache, final Context context,final int imageView, final int isFragment,
//										  final long cacheExpireInterval, final String... params) {
//        mCurrentUrl = url;
//
//		if (mLoadingThread != null && mLoadingThread.isAlive())
//			cancelLoading();
//
//		mCancelled = false;
//		mLoadingThread = new Thread("DataLoaderThread") {
//			@Override
//			public void run() {
//				if (getContext() == null)
//					return;
//
//				postOnStartLoading();
//
//				// just a hack
//                mUseDefaultParams = !url.contains("access_token=");
//
//				final String[] defaultParams = mUseDefaultParams ? Common.getDefaultAPIParams(getContext()) : new String[] { };
//                final int defParamLength = defaultParams == null ? 0 : defaultParams.length;
//
//
//				BasicNameValuePair[] ps = null;
//				if (params != null) {
//					ps = new BasicNameValuePair[(params.length + defParamLength) / 2];
//                    if (defaultParams != null) {
//                        for (int i = 0; i < defParamLength; i += 2) {
//                            BasicNameValuePair p = new BasicNameValuePair(defaultParams[i], defaultParams[i + 1]);
//                            ps[i / 2] = p;
//                        }
//                    }
//					final int start = defParamLength / 2;
//					for (int i = 0, n = params.length; i < n; i += 2) {
//						BasicNameValuePair p = new BasicNameValuePair(params[i], params[i + 1]);
//						ps[i / 2 + start] = p;
//					}
//				} else {
//                    if (defaultParams != null) {
//                        ps = new BasicNameValuePair[defParamLength / 2];
//                        for (int i = 0; i < defParamLength; i += 2) {
//                            BasicNameValuePair p = new BasicNameValuePair(defaultParams[i], defaultParams[i + 1]);
//                            ps[i / 2] = p;
//                        }
//                    }
//				}
//
//				String cachedJson = null;
//				final Context c = mContext.get();
//
//				if (useResponseCache || (c != null && !Utils.hasNetworkConnection(c))) {
//                    if (c != null && !Utils.hasNetworkConnection(c)) {
//                        cachedJson = mCache.getCachedResponse(url, Integer.MAX_VALUE, (Object[]) params);
//                    }
//                    else {
//                        if (cacheExpireInterval > 0)
//                            cachedJson = mCache.getCachedResponse(url, cacheExpireInterval, (Object[]) params);
//                        else cachedJson = mCache.getCachedResponse(url, (Object[]) params);
//                    }
//                }
//
//				if (cachedJson == null) {
//
////                    if (c != null && !Utils.hasNetworkConnection(c)) {
////                        postLoadingCompleteNoNetwork(c);
////                        return;
////                    }
//
//					RequestResponse response = null;
//					try {
//						ServiceConnection conn = new ServiceConnection(mTypeHttp, Common.BA_USR, Common.BA_PWD);
//						response = conn.postOrPut2(url, type, ps);
//						postLoadingComplete(url, response, resultType,context,imageView,isFragment);
//
//					} catch (Exception e) {
//						e.printStackTrace();
//						postLoadingComplete(url, response, resultType,context,imageView,isFragment);
//					}
//					if (response.isSuccess())
//						mCache.cacheResponse(url, response.getData(), (Object[]) params);
//
//				} else {
//                    try {
//                        postLoadingComplete(url, RequestResponse.newRequestResponse(true, cachedJson), resultType,context,imageView,isFragment);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                        mCache.deleteCachedResponse(url, params);
//						postLoadingComplete(url, RequestResponse.newRequestResponse(false, null), resultType,context,imageView,isFragment);
//                    }
//				}
//			}
//		};
//		mLoadingThread.start();
//	}


	private boolean mPaused = false;

	public synchronized void pause() {
		mPaused = true;
	}

	public synchronized void resume() {
        if (!mPaused)
            return;
		mPaused = false;
		try {
			notifyAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

    private boolean mCancelled = false;

	public synchronized void cancelLoading() {
		mCancelled = true;
		mPaused = false;
		notifyAll();
	}

	protected void postLoadingComplete(String url, RequestResponse response, Type resultType,Context context,final int isAvatar,final int isFragment) {
        Object result = null;
        Gson gson = new Gson();

        if (url != null && response != null && response.getData() != null) {
			Log.d("DataLoader", "URL: " + url + ", Response: " + response.getData());
			try {
				JSONObject jsonObject = new JSONObject(response.getData());
				JSONArray image_data  = jsonObject.getJSONArray("image_data");
				for (int i = 0;i<image_data.length();i++){
					JSONObject image_avatar  = image_data.getJSONObject(i);
					JSONObject file_url  = image_avatar.getJSONObject("file_url");
					String imagePath = file_url.getString("default");
					final Pref p = new Pref(context);
					if(isAvatar==1){
						p.putString(Pref.PREF_KEY_AVATAR,imagePath);
					}else if(isAvatar==2){
						p.putString(Pref.PREF_KEY_COVER,imagePath);
					}else if(isAvatar==3){
						p.putString(Pref.PREF_KEY_LOGO,imagePath);
					}

					if(isFragment == 999){
						mDataLoaderListener.onCompletedDUpImage(DataLoader.this, response, isAvatar,isFragment);
					}else {
						if(response.getStatusCode()==200) {
							mDataLoaderListener.onCompleted(DataLoader.this, response, isAvatar,isFragment);
						}else {
							mDataLoaderListener.onCompleted(DataLoader.this, response, isAvatar,isFragment);
						}
					}
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
        if (mTypeParse == ServiceConnection.TYPE_PARSE.OBJECT) {
            if (response.isSuccess())
                try {
                    result = gson.fromJson(response.getData(), resultType);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            else {
                try {
                    result = gson.fromJson(response.getData(), BaseData.class);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                if (result != null)
                    ErrorUtils.handleErrorCode(response.getStatusCode(), response.getData());
            }
        }

        synchronized (this) {
            if (mDataLoaderListener != null && !mCancelled) {
                boolean isPaused = false;
                while (mPaused && !mCancelled) {
                    isPaused = true;
                    try {
                        wait();
                    } catch (Exception e) {
                        e.printStackTrace();
                        return;
                    }
                }
                if (!mCancelled) {


                    //Log.d("DataLoader", "Url: " + url + ", Data response: " + response.getData());
                    // try to convert to object
                    try {
                        BaseData testObj = gson.fromJson(response.getData(), BaseData.class);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        final Context c = mContext.get();
                        if (c != null) {
//                            postLoadingCompleteError(c);
							Log.d("ERROR","ERROR");
                            return;
                        }
                    }


                    if (mTypeParse == ServiceConnection.TYPE_PARSE.OBJECT) {
                        if (result != null)
                            mDataLoaderListener.onCompleted(DataLoader.this, response, result,1);
                        else {
                            final Context c = mContext.get();

                            if (c != null && !Utils.hasNetworkConnection(c)) {
								Log.d("ERROR","ERROR");
                                return;
                            }
                        }
                    } else {
                        mDataLoaderListener.onCompleted(DataLoader.this, response);
                    }
                }

            }
        }


	}

//    protected synchronized void postLoadingCompleteNoNetwork(final Context c) {
//        if (c != null && c instanceof BaseActivity) {
//            final BaseActivity baseAct = (BaseActivity) c;
//            baseAct.runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    baseAct.hideAllProgressBar();
//                }
//            });
//        }
//
//        if (mScreen != null) {
//            final Screen scr = mScreen.get();
//            if (scr != null) {
//                scr.runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        scr.hideAllProgressBar();
//                    }
//                });
//            }
//        }
//
//    }

//    protected synchronized void postLoadingCompleteError(final Context c) {
//        if (c != null && c instanceof BaseActivity) {
//            final BaseActivity baseAct = (BaseActivity) c;
//            baseAct.runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    baseAct.hideAllProgressBar();
//                    ToastUtils.showLongText(baseAct, baseAct.getString(R.string.network_error_toast));
//                }
//            });
//        }
//
//        if (mScreen != null) {
//            final Screen scr = mScreen.get();
//            if (scr != null) {
//                scr.runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        scr.hideAllProgressBar();
//                    }
//                });
//            }
//        }
//
//    }

	public synchronized boolean isPaused() {
		return mPaused;
	}

    private String MOCK_DATA;
    public void setMockJSONData(String mockJson) {
        MOCK_DATA = mockJson;
    }
    
}
