package com.example.ev.SoKhop.Utils;

public class Configuration {

	public static final String BASE_URL = "http://192.168.1.191:3000/";
	public static final String BASE_URL_UPLOAD = "http://192.168.1.191:3004/";
	public static final String REGISTER_URL = BASE_URL+"api/v1/users/register";
	public static final String UPLOAD_IMAGE_URL = BASE_URL_UPLOAD+"api/v1/files/upload";

	public static final int CONECTION_TIMEOUT = 10 * 1000;
	public static final int WRITE_TIMEOUT = 10 * 1000;
	public static final int READ_TIMEOUT = 30 * 1000;

}
