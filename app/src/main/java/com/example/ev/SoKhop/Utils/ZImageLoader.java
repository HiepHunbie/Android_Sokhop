package com.example.ev.SoKhop.Utils;

import android.graphics.Bitmap;
import android.os.Handler;
import android.widget.ImageView;

import com.example.ev.SoKhop.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;


public class ZImageLoader {
	private static ImageLoader sImageLoader;
	public static DisplayImageOptions sSimpleOptions;

	private ZImageLoader() {
	}

	public static void init() {
		sImageLoader = ImageLoader.getInstance();
		sSimpleOptions = new DisplayImageOptions.Builder()
				// .showImageOnLoading(R.drawable.ic_stub)
				// .showImageForEmptyUri(R.drawable.ic_empty)
				 .showImageOnFail(R.drawable.logo)
				 .delayBeforeLoading(1000)
				// .preProcessor(...)
				// .postProcessor(...)
				// .extraForDownloader(...)
				// .decodingOptions(...)
				.resetViewBeforeLoading(true).cacheInMemory(true).cacheOnDisk(true)
				.considerExifParams(false).imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2)
				.bitmapConfig(Bitmap.Config.RGB_565).displayer(new SimpleBitmapDisplayer())
				.handler(new Handler()).build();
	}

	public static DisplayImageOptions getSimpleStubOption(int stubRes) {
		return new DisplayImageOptions.Builder().showImageOnLoading(stubRes)
				.showImageForEmptyUri(stubRes).showImageOnFail(stubRes)
				.resetViewBeforeLoading(true).cacheInMemory(true).cacheOnDisk(true)
				.considerExifParams(false).imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2)
				.bitmapConfig(Bitmap.Config.RGB_565).displayer(new SimpleBitmapDisplayer())
				.handler(new Handler()).build();
	}

	public static void asyncLoadImage(String url, ImageView iv) {
		asyncLoadImage(url, iv, sSimpleOptions, null);
	}

	public static void asyncLoadImage(String url, ImageView iv, DisplayImageOptions options,
									  ImageLoadingListener listener) {
		sImageLoader.displayImage(url, iv, options, listener);
	}

	public static void asyncLoadImage(String url, ImageLoadingListener listener) {
		sImageLoader.loadImage(url, listener);
	}
}
