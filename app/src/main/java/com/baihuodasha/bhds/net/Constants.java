package com.baihuodasha.bhds.net;

/**
 * 
 * 类名称 ：Constants
 * 类描述 ：networimageview包下的所公用参数类
 * 创建人 ：李章丰
 * 创建时间：下午2:49:15
 */
public class Constants {
	/**
	 * TAG
	 */
	public static final String TAG = "NetWorkImageView";
	/**
	 * DEBUG
	 */
	public static final boolean DEBUG = true;

	
	/**
	 * 默认图片ID
	 */
	//public static int DEFAULT_IMAGE_ID = R.drawable.ic_camera;
	
	
	/**
	 * 存储的文件名后缀
	 */
	public static final String WHOLESALE_CONV = ".png";
	
	
	
	/**
	 * 在外部存储上的地址
	 */
	public static final String IMAGE_CACHE_DIR = "fenqifu/imagecache";
//	public static final String IMAGE_CACHE_LOCAL_DIR = "fenqifu/LocalPic";
	
	/**
	 * 网络连接超时时间
	 */
	public final static int CONNECT_TIMEOUT = 60;
	
	/**
	 * 下载图片的最大线程数
	 */
	public final static int MAX_THREADS = 2;
	
	/**
	 * 图片的二级缓存容量
	 */
	public static final int LEVEL_TWO_CACHE_CAPACITY = 10;
	
	public static final String APP_CONFIG_FILE_NAME = "AppConfig.json";
}
