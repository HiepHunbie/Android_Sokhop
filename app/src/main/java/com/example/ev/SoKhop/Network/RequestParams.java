package com.example.ev.SoKhop.Network;

import org.apache.http.HttpEntity;
import org.apache.http.protocol.HTTP;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class RequestParams implements Serializable {

	protected final ConcurrentHashMap<String, String> urlParams = new ConcurrentHashMap<String, String>();
	protected final ConcurrentHashMap<String, List<FileWrapper>> fileArrayParams = new ConcurrentHashMap<String, List<FileWrapper>>();
    protected boolean isRepeatable = true;

	public void put(String key, String value) {
		if (key != null && value != null) {
			urlParams.put(key, value);
		}
	}

	public void put(String key, File files[]) throws FileNotFoundException {

		if (key != null) {
			List<FileWrapper> fileWrappers = new ArrayList<FileWrapper>();
			for (File file : files) {
				if (file == null || !file.exists()) {
					throw new FileNotFoundException();
				}
				fileWrappers.add(new FileWrapper(file, null, null));
			}
			fileArrayParams.put(key, fileWrappers);
		}
	}
	
    public void setHttpEntityIsRepeatable(boolean flag) {
        this.isRepeatable = flag;
    }
    
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (ConcurrentHashMap.Entry<String, String> entry : urlParams.entrySet()) {
            if (result.length() > 0)
                result.append("&");

            result.append(entry.getKey());
            result.append("=");
            result.append(entry.getValue());
        }

        /*for (ConcurrentHashMap.Entry<String, StreamWrapper> entry : streamParams.entrySet()) {
            if (result.length() > 0)
                result.append("&");

            result.append(entry.getKey());
            result.append("=");
            result.append("STREAM");
        }

        for (ConcurrentHashMap.Entry<String, FileWrapper> entry : fileParams.entrySet()) {
            if (result.length() > 0)
                result.append("&");

            result.append(entry.getKey());
            result.append("=");
            result.append("FILE");
        }*/

        for (ConcurrentHashMap.Entry<String, List<FileWrapper>> entry : fileArrayParams.entrySet()) {
            if (result.length() > 0)
                result.append("&");

            result.append(entry.getKey());
            result.append("=");
            result.append("FILES(SIZE=").append(entry.getValue().size()).append(")");
        }

        /*List<BasicNameValuePair> params = getParamsList(null, urlParamsWithObjects);
        for (BasicNameValuePair kv : params) {
            if (result.length() > 0)
                result.append("&");

            result.append(kv.getName());
            result.append("=");
            result.append(kv.getValue());
        }*/

        return result.toString();
    }
	
	public HttpEntity createMultipartEntity() {
		SimpleMultipartEntity entity = new SimpleMultipartEntity();
        entity.setIsRepeatable(isRepeatable);
        
        // Add string params
        for (ConcurrentHashMap.Entry<String, String> entry : urlParams.entrySet()) {
            entity.addPartWithCharset(entry.getKey(), entry.getValue(), HTTP.UTF_8);
        }
        
        // Add file collection
        for (ConcurrentHashMap.Entry<String, List<FileWrapper>> entry : fileArrayParams.entrySet()) {
            List<FileWrapper> fileWrapper = entry.getValue();
            for (FileWrapper fw : fileWrapper) {
                entity.addPart(entry.getKey(), fw.file, fw.contentType, fw.customFileName);
            }
        }
        return entity;
	}

    public ConcurrentHashMap<String, List<FileWrapper>> getFileArrayParams() {
        return fileArrayParams;
    }
    public ConcurrentHashMap<String, String> getUrlArrayParams() {
        return urlParams;
    }

	public static class FileWrapper implements Serializable {
		public final File file;
		public final String contentType;
		public final String customFileName;

		public FileWrapper(File file, String contentType, String customFileName) {
			this.file = file;
			this.contentType = contentType;
			this.customFileName = customFileName;
		}
	}
}
