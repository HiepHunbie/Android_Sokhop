/*
 * ServiceConnection.java
 * Created on Nov 28, 2012
 * 
 * (coffee) Copyright Nippon Telegraph and Telephone Corporation
 *     2012 All rights reserved.
 * 
 * This software is furnished under a contract and use, duplication, disclosure
 * and all other uses are restricted to the rights specified in the written
 * contract and memorandum between the contractor and Nippon Telegraph and
 * Telephone Corporation.
 */
package com.example.ev.SoKhop.Network;

import android.os.StrictMode;
import android.text.TextUtils;


import com.example.ev.SoKhop.Utils.ErrorUtils;
import com.example.ev.SoKhop.Utils.Utils;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.zip.GZIPInputStream;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class ServiceConnection {

	public static final int REQUEST_TIMEOUT = 20000;
	public static final int PUT = 0;
	public static final int POST = 1;
	public static final int DELETE = 2;
	public static final int GET = 3;
	public enum TYPE_HTTP {
		FORM_DATA, BODY
	}
	
	public enum TYPE_PARSE {
		OBJECT, STRING
	}
	
	private TYPE_HTTP mTypeHttp = TYPE_HTTP.FORM_DATA;

	public ServiceConnection() {
	}

	private boolean mUseGZIP = true;

	public void setUseGZIP(boolean use) {
		mUseGZIP = use;
	}

	private String mUsername = null;
	private String mPassword = null;

	public ServiceConnection(TYPE_HTTP typeHttp, String username, String password) {
		mTypeHttp = typeHttp;
		mUsername = username;
		mPassword = password;
	}

	boolean isGZipHttpResponse(HttpResponse response) {
		Header header = response.getEntity().getContentEncoding();
		if (header == null) {
			return false;
		}
		String value = header.getValue();
		return (!TextUtils.isEmpty(value) && value.contains("gzip"));
	}

	private TrustManager[] trustAllCerts = new TrustManager[]{
		new X509TrustManager() {
			public java.security.cert.X509Certificate[] getAcceptedIssuers() {
				return null;
			}
			public void checkClientTrusted(
					java.security.cert.X509Certificate[] certs, String authType) {
			}
			public void checkServerTrusted(
					java.security.cert.X509Certificate[] certs, String authType) {
			}
		}
	};

	public String getOrDelete(String serverPage, int type) throws IOException {
		String jsonString;
		DefaultHttpClient httpClient = new DefaultHttpClient();
		if (mUsername != null) {
			httpClient.getCredentialsProvider().setCredentials(
					new AuthScope(null, -1),
					new UsernamePasswordCredentials(mUsername, mPassword));
		}

		HttpContext localContext = new BasicHttpContext();
		HttpUriRequest uriRequest;
		if (type == GET) {
			uriRequest = new HttpGet(serverPage);
		} else if (type == DELETE) {
			uriRequest = new HttpDelete(serverPage);
		} else {
			throw new IllegalArgumentException("Illegal getOrDelete");
		}

		HttpResponse response = httpClient.execute(uriRequest, localContext);

		int code = -1;
		StatusLine sln = response.getStatusLine();
		if (sln != null)
			code = sln.getStatusCode();
		
		if (code != 200) {
		}
		HttpEntity entity = response.getEntity();
		jsonString = EntityUtils.toString(entity, "utf-8");

		int errorUtilsCode = ErrorUtils.handleErrorCode(code, jsonString);
		if (errorUtilsCode != ErrorUtils.ERROR_NONE) {
			return null;
		} else {
			return jsonString;
		}
	}

	private HttpURLConnection getDefaultHttpURLConnection(String serverPage) {
		try {
			URL url = new URL(serverPage);
			HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
			if (urlConnection instanceof HttpsURLConnection) {
				String keyStoreType = KeyStore.getDefaultType();
				KeyStore keyStore = KeyStore.getInstance(keyStoreType);
				keyStore.load(null, null);

				SSLContext context = SSLContext.getInstance("TLS");
				context.init(null, trustAllCerts, null);

				HttpsURLConnection https = (HttpsURLConnection) urlConnection;
				https.setDefaultSSLSocketFactory(context.getSocketFactory());
				https.setSSLSocketFactory(context.getSocketFactory());
			}

			if (mTypeHttp == TYPE_HTTP.FORM_DATA) {
				urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
				urlConnection.setRequestProperty("Accept", "application/json");
			} else {
				urlConnection.setRequestProperty("Content-Type", "application/json");
				urlConnection.setRequestProperty("Accept", "application/json");
			}

			return urlConnection;
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public RequestResponse getOrDelete2(String serverPage, int type) throws IOException {
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);

		HttpURLConnection urlConnection = getDefaultHttpURLConnection(serverPage);
		if (urlConnection == null) return null;
		if (type == GET) {
			urlConnection.setRequestMethod("GET");
		} else if (type == DELETE) {
			urlConnection.setRequestMethod("DELETE");
		} else {
			throw new IllegalArgumentException("Illegal getOrDelete");
		}
		RequestResponse response = new RequestResponse();
		try {
			int status = urlConnection.getResponseCode();
			InputStream in;
			if (status >= HttpURLConnection.HTTP_ACCEPTED) {
				response.setIsSuccess(false);
				response.setStatusCode(status);
				in = urlConnection.getErrorStream();
			} else {
				response.setIsSuccess(true);
				response.setStatusCode(status);
				in = urlConnection.getInputStream();
			}

			BufferedReader br = null;
			StringBuilder sb = new StringBuilder();
			String line;
			try {

				br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
				while ((line = br.readLine()) != null) {
					sb.append(line);
				}

			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (br != null) {
					try {
						br.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			response.setData(sb.toString());
			return response;
		} catch (UnknownHostException ex) {
			ex.printStackTrace();
		}
		response.setIsSuccess(false);
		return response;
	}

	public String postOrPut(String serverPage, int type, BasicNameValuePair... params)
			throws IllegalStateException, IOException {
		ArrayList<BasicNameValuePair> ps = new ArrayList<>();
		if (params != null && params.length > 0) {
			ps.addAll(Arrays.asList(params));
		}
		HttpResponse response = this.doPostOrPut(serverPage, type, ps);
		HttpEntity entity = response.getEntity();

		int code = -1;
		StatusLine sln = response.getStatusLine();
		if (sln != null)
			code = sln.getStatusCode();

		String ret;
		if (!isGZipHttpResponse(response)) {
			ret = Utils.readInputStream(entity.getContent());
		} else {
			GZIPInputStream gis = new GZIPInputStream(response.getEntity()
					.getContent());
			StringBuilder inputStringBuilder = new StringBuilder();
			BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader(gis, "UTF-8"));
			String line = bufferedReader.readLine();
			while (line != null) {
				inputStringBuilder.append(line);
				inputStringBuilder.append('\n');
				line = bufferedReader.readLine();
			}
			ret = inputStringBuilder.toString();
			bufferedReader.close();
			gis.close();
		}
		int errorUtilsCode = ErrorUtils.handleErrorCode(code, ret);
		if (errorUtilsCode != ErrorUtils.ERROR_NONE) {
			return null;
		} else {
			return ret;
		}
	}

	private DefaultHttpClient createDefaultHttpClient() {
		HttpParams params = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(params, REQUEST_TIMEOUT);
		HttpConnectionParams.setSoTimeout(params, REQUEST_TIMEOUT);
		return new DefaultHttpClient(params);
	}

	public String postOrPutJson(String serverPage, int type, String json)
			throws IOException {
		HttpResponse response;
		String ret = null;

		DefaultHttpClient httpclient = createDefaultHttpClient();

		if (mUsername != null) {
			httpclient.getCredentialsProvider().setCredentials(
					new AuthScope(null, -1),
					new UsernamePasswordCredentials(mUsername, mPassword));
		}

		HttpContext localContext = new BasicHttpContext();
		HttpPost httppost = new HttpPost(serverPage);

		if (mUseGZIP)
			httppost.setHeader("Accept-Encoding", "gzip, deflate");
		if (json != null && json.length() > 0) {
			httppost.setEntity(new StringEntity(json, "utf-8"));
		}
		response = httpclient.execute(httppost, localContext);

		int code = -1;
		StatusLine sln = response.getStatusLine();
		if (sln != null)
			code = sln.getStatusCode();
		HttpEntity entity = response.getEntity();
		try {
			if (!isGZipHttpResponse(response)) {
				ret = EntityUtils.toString(entity, "utf-8");
			} else {
				GZIPInputStream gis = new GZIPInputStream(response.getEntity()
						.getContent());
				StringBuilder inputStringBuilder = new StringBuilder();
				BufferedReader bufferedReader = new BufferedReader(
						new InputStreamReader(gis, "UTF-8"));
				String line = bufferedReader.readLine();
				while (line != null) {
					inputStringBuilder.append(line);
					inputStringBuilder.append('\n');
					line = bufferedReader.readLine();
				}
				ret = inputStringBuilder.toString();
				bufferedReader.close();
				gis.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		int errorUtilsCode = ErrorUtils.handleErrorCode(code, ret);
		if (errorUtilsCode != ErrorUtils.ERROR_NONE) {
			return null;
		} else {
			return ret;
		}
	}

	public RequestResponse postOrPut2(String serverPage, int type, BasicNameValuePair... params) throws IOException {
		List<NameValuePair> ps = new ArrayList<>();
		if (params != null && params.length > 0) {
			ps.addAll(Arrays.asList(params));
		}

		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);

		HttpURLConnection urlConnection = getDefaultHttpURLConnection(serverPage);
		if (urlConnection == null) return null;
		if (type == POST) {
			urlConnection.setRequestMethod("POST");
		} else if (type == PUT) {
			urlConnection.setRequestMethod("PUT");
		} else {
			throw new IllegalArgumentException("Illegal getOrDelete");
		}

		RequestResponse response = new RequestResponse();
		try {
			OutputStream os = urlConnection.getOutputStream();
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
			writer.write(getQuery(ps));
			writer.flush();
			writer.close();
			os.close();
			int status = urlConnection.getResponseCode();
			InputStream in;

			if (status >= HttpURLConnection.HTTP_ACCEPTED) {
				response.setIsSuccess(false);
				response.setStatusCode(status);
				in = urlConnection.getErrorStream();
			} else {
				response.setIsSuccess(true);
				response.setStatusCode(status);
				in = urlConnection.getInputStream();
			}

			BufferedReader br = null;
			StringBuilder sb = new StringBuilder();
			String line;
			try {

				br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
				while ((line = br.readLine()) != null) {
					sb.append(line);
				}

			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (br != null) {
					try {
						br.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			response.setData(sb.toString());
			return response;
		} catch (UnknownHostException ex) {
			ex.printStackTrace();
		}
		response.setIsSuccess(false);
		return response;
	}

	private String getQuery(List<NameValuePair> params) throws UnsupportedEncodingException
	{
		if (mTypeHttp == TYPE_HTTP.FORM_DATA) {
			StringBuilder result = new StringBuilder();
			boolean first = true;

			for (NameValuePair pair : params) {
				if (first)
					first = false;
				else
					result.append("&");

				result.append(URLEncoder.encode(pair.getName(), "UTF-8"));
				result.append("=");
				result.append(URLEncoder.encode(pair.getValue(), "UTF-8"));
			}
			//Log.d("ServiceConnection", "getQuery: " + result.toString());
			return result.toString();
		} else {
			JSONObject jso = new JSONObject();
			if (params != null) {
				for (NameValuePair nameValuePair : params) {
					try {
						jso.put(nameValuePair.getName(), nameValuePair.getValue());
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}

			}
			//Log.d("ServiceConnection", "getQuery: " + jso.toString());
			return jso.toString();
		}
	}

	private HttpResponse doPostOrPut(String serverPage, int type, List<BasicNameValuePair> params) {
		HttpResponse response = null;

		try {
			DefaultHttpClient httpclient = createDefaultHttpClient();
			if (mUsername != null) {
				httpclient.getCredentialsProvider().setCredentials(
						new AuthScope(null, -1),
						new UsernamePasswordCredentials(mUsername, mPassword));
			}

			HttpContext localContext = new BasicHttpContext();
			HttpEntityEnclosingRequestBase httpRequest;
			if (type == POST) {
				httpRequest = new HttpPost(serverPage);
			} else if (type == PUT) {
				httpRequest = new HttpPut(serverPage);
			} else {
				throw new IllegalArgumentException("not valid type");
			}
			if (mUseGZIP)
				httpRequest.setHeader("Accept-Encoding", "gzip, deflate");

			if (params != null && params.size() > 0) {
				if (mTypeHttp == TYPE_HTTP.FORM_DATA) {
					HttpEntity entity = new UrlEncodedFormEntity(params, "utf-8");
					httpRequest.setEntity(entity);
				} else {
	                String postJSON = generateJSON(params);
	                httpRequest.setHeader("Content-Type", "application/json");
	                httpRequest.setHeader("Accept", "application/json");
	                httpRequest.setEntity(new StringEntity(postJSON, "utf-8"));
				}
			}

			response = httpclient.execute(httpRequest, localContext);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return response;
	}
	
	private String generateJSON(List<BasicNameValuePair> params) {
		JSONObject jso = new JSONObject();
        if (params != null) {
        	for (BasicNameValuePair basicNameValuePair : params) {
        		try {
					jso.put(basicNameValuePair.getName(), basicNameValuePair.getValue());
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}

        }
        return jso.toString();
    }

	public HttpResponse postOrPutFiles(String serverPage, final int type, RequestParams param) {
		HttpResponse response = null;
		try {
			DefaultHttpClient httpclient = createDefaultHttpClient();
			if (mUsername != null) {
				httpclient.getCredentialsProvider().setCredentials(
						new AuthScope(null, -1),
						new UsernamePasswordCredentials(mUsername, mPassword));
			}

			HttpContext localContext = new BasicHttpContext();
			HttpEntityEnclosingRequestBase httpRequest;
			if (type == POST) {
				httpRequest = new HttpPost(serverPage);
			} else if (type == PUT) {
				httpRequest = new HttpPut(serverPage);
			} else {
				throw new IllegalArgumentException("not valid type");
			}
			if (mUseGZIP)
				httpRequest.setHeader("Accept-Encoding", "gzip, deflate");

			if (param != null) {
				httpRequest.setEntity(param.createMultipartEntity());
			}

			response = httpclient.execute(httpRequest, localContext);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return response;
	}

	public RequestResponse postOrPutFiles2(String serverPage, final int type, RequestParams param)throws IOException {

		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);

		HttpURLConnection urlConnection = getDefaultHttpURLConnection(serverPage);
		if (urlConnection == null) return null;
		if (type == POST) {
			urlConnection.setRequestMethod("POST");
		} else if (type == PUT) {
			urlConnection.setRequestMethod("PUT");
		} else {
			throw new IllegalArgumentException("Illegal getOrDelete");
		}

		urlConnection.setUseCaches(false);
		urlConnection.setDoOutput(true); // indicates POST method
		urlConnection.setDoInput(true);
		HttpEntity entity = param.createMultipartEntity();

		Header header = entity.getContentType();
		urlConnection.setRequestProperty(header.getName(), header.getValue());
		OutputStream outputStream = urlConnection.getOutputStream();
		entity.writeTo(outputStream);

		RequestResponse response = new RequestResponse();
		try {
			int status = urlConnection.getResponseCode();
			InputStream in;

			if (status >= HttpURLConnection.HTTP_ACCEPTED) {
				response.setIsSuccess(false);
				response.setStatusCode(status);
				in = urlConnection.getErrorStream();
			} else {
				response.setIsSuccess(true);
				response.setStatusCode(status);
				in = urlConnection.getInputStream();
			}

			BufferedReader br = null;
			StringBuilder sb = new StringBuilder();
			String line;
			try {

				br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
				while ((line = br.readLine()) != null) {
					sb.append(line);
				}

			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (br != null) {
					try {
						br.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			response.setData(sb.toString());
			return response;
		} catch (UnknownHostException ex) {
			ex.printStackTrace();
		}
		response.setIsSuccess(false);
		return response;
	}
}
