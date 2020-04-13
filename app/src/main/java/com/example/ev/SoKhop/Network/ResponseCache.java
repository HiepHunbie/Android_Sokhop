package com.example.ev.SoKhop.Network;


import com.example.ev.SoKhop.Utils.Utils;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;

public class ResponseCache {
	public final static long DEFAULT_CACHE_EXPIRE_TIME = 10000; // 5 secs.
	private long mCacheExpireTime = DEFAULT_CACHE_EXPIRE_TIME;
	/**
	 * Sets expire time in milliseconds of local cached responses. 
	 * @param time Negative value means the cache will never expired
	 */
	public void setCacheExpireTime(long time) {
		mCacheExpireTime = time;
	}
	
	private File mCacheDir;
	public ResponseCache(File cacheDir) {
		mCacheDir = cacheDir;
	}
	public void cacheResponse(String url, String responseData, Object... params) {
		String name = getCacheFileName(url, params);
		if (name != null) {
            File cf = new File(mCacheDir, name);
			Utils.writeFile(cf, responseData);
		}
	}
    public void deleteCachedResponse(String url, Object... params) {
        String name = getCacheFileName(url, params);
        if (name != null) {
            File f = new File(mCacheDir, name);
            f.delete();
        }
    }
	public String getCachedResponse(String url, long cacheExpireTime, Object... params) {
		String name = getCacheFileName(url, params);
		if (name != null) {
			File f = new File(mCacheDir, name);
			if (f.exists()) {
				long lastUpdate = f.lastModified();
				Calendar c = Calendar.getInstance();
				if (c.getTimeInMillis() - lastUpdate < cacheExpireTime)
					return Utils.readFile(f).trim();
			}
		}
		return null;
	}
    public String getCachedResponse(String url, Object... params) {
        return getCachedResponse(url, mCacheExpireTime, params);
    }
	
	public static String getCacheFileName(String url, Object... params) {
		String theName = url;
		if (params != null) {
			for (int i=0, n=params.length; i<n; i+=2) {
				String p = "&";
				if (i==0) p = "?";
				String key = params[i].toString();
				String val = params[i+1] != null ? params[i+1].toString() : "null";
				theName += p + key + "=" + val;
			}
		}
        return md5(theName);
	}
    public static String md5(String s) {
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            for (int i=0; i<messageDigest.length; i++)
                hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
	
	static String encodeUrl(String url) {
		try {
			return URLEncoder.encode(url, "UTF-8");
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
}
