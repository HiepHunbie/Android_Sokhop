package com.example.ev.SoKhop.Base;

import android.media.Image;

import java.util.ArrayList;

public class UploadResponse extends BaseData {
	private Album_data album_data;
	private ArrayList<Image> image_data;

	public Album_data getAlbum_data() {
		return album_data;
	}

	public void setAlbum_data(Album_data album_data) {
		this.album_data = album_data;
	}

	@Override
	public String toString() {
		return "ClassPojo [ error_code = "
				+ error_code + ", album_data = " + album_data + "]";
	}

	public ArrayList<Image> getImage_data() {
		return image_data;
	}

	public void setImage_data(ArrayList<Image> image_data) {
		this.image_data = image_data;
	}
	
	
}
