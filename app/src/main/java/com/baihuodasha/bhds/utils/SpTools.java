package com.baihuodasha.bhds.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class SpTools {
	
	public static void setString(Context context,String key,String value){
		SharedPreferences sp = context.getSharedPreferences(Constants.CONFIGFILE, Context.MODE_PRIVATE);
		sp.edit().putString(key, value).commit();
	}
	
	public static String getString(Context context,String key,String value){
		SharedPreferences sp = context.getSharedPreferences(Constants.CONFIGFILE, Context.MODE_PRIVATE);
		return sp.getString(key, value);
	}
	
	public static void setBoolean(Context context,String key,boolean value){
        SharedPreferences sp = context.getSharedPreferences(Constants.CONFIGFILE,Context.MODE_PRIVATE);
        sp.edit().putBoolean(key,value).commit();
    }

    public static boolean getBoolean(Context context,String key,boolean value){
        SharedPreferences sp = context.getSharedPreferences(Constants.CONFIGFILE,Context.MODE_PRIVATE);
        return sp.getBoolean(key,value);
    }
    
    public static void setInt(Context context,String key,int value){
		SharedPreferences sp = context.getSharedPreferences(Constants.CONFIGFILE, Context.MODE_PRIVATE);
		sp.edit().putInt(key, value).commit();
	}
	
	public static int getInt(Context context,String key,int value){
		SharedPreferences sp = context.getSharedPreferences(Constants.CONFIGFILE, Context.MODE_PRIVATE);
		return sp.getInt(key, value);
	}
	public static void setLong(Context context,String key,Long value){
		SharedPreferences sp = context.getSharedPreferences(Constants.CONFIGFILE, Context.MODE_PRIVATE);
		sp.edit().putLong(key, value).commit();
	}
	
	public static Long getLong(Context context,String key,Long value){
		SharedPreferences sp = context.getSharedPreferences(Constants.CONFIGFILE, Context.MODE_PRIVATE);
		return sp.getLong(key, value);
	}
	
	public static String InputToString(Context context,String key){
		String result = null;
    	try {
			InputStream is = context.getAssets().open(key);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int len = 0;
			while((len = is.read(buffer))!=-1){
				baos.write(buffer, 0, len);
			}
			is.close();
			result = baos.toString();			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
}
