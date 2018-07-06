package com.baihuodasha.bhds.permission;

import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;
import android.util.Log;

public class PermissionsChecker {
	private final Context mContext;

	public PermissionsChecker(Context context) {
		mContext = context.getApplicationContext();
	} 
	// 判断权限集合
	public boolean lacksPermissions(String... permissions) {
		for (String permission : permissions) {
			if (lacksPermission(permission)) {
				Log.e("aaaa", permission);
				return true;
			}
		}
		return false;
	}

	// 判断是否缺少权限
	private boolean lacksPermission(String permission) {
		return ContextCompat.checkSelfPermission(mContext, permission) == PackageManager.PERMISSION_DENIED;
//	return false;
	}
}
