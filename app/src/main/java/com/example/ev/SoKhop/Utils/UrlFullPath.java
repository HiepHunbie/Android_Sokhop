package com.example.ev.SoKhop.Utils;

import android.content.Context;
import android.util.Log;

public class UrlFullPath {
	private UrlBuilder builder;

	public static class UrlBuilder {
		private String path = null;
		private String baseURL = null;
		private String[] params = null;
		private boolean hasAccessToken = false;
		private Context context;
		
		public UrlBuilder setBaseURL(String value) {
			baseURL = value;
			return this;
		}
		
		public UrlBuilder setPath(String value) {
			path = value;
			return this;
		}
		
		public UrlBuilder setQuery(final String... value) {
			params = value;
			return this;
		}
		
		public UrlBuilder setHasAccessToken(boolean value, Context context) {
			hasAccessToken = value;
			this.context = context;
			return this;
		}

		public UrlFullPath build() {
			return new UrlFullPath(this);
		}
	}
	
	public String getFullUrl(Context context) {
		StringBuilder sb = new StringBuilder(builder.baseURL);
		if (builder.path != null) {
			if (sb.charAt(sb.length() - 1) != '/') {
				sb.append("/");
			}
			sb.append(builder.path);
		}
		if (builder.params != null) {
			for (int i = 0, n = builder.params.length; i < n; i += 2) {
				if (i == 0) {
					sb.append("?" + builder.params[i] + "=" + builder.params[i + 1]);
				} else {
					sb.append("&" + builder.params[i] + "=" + builder.params[i + 1]);
				}
			}
		}
		if (builder.hasAccessToken && builder.context != null) {
			Pref pref = new Pref(context);
			String accessToken = pref.getString(Pref.PREF_KEY_TOKEN, "");
			Log.d("accessToken","Ss"+accessToken);
			if (accessToken.length() > 0) {
				sb.append(( builder.params != null && builder.params.length > 0 ) ? "&" : "?");
				sb.append("access_token=" + accessToken);
			}
		}
		return sb.toString();
	}

	private UrlFullPath(UrlBuilder builder) {
		this.builder = builder;
		if (this.builder.baseURL == null) {
			throw new IllegalArgumentException("Must to set base URL");
		}
	}
}
