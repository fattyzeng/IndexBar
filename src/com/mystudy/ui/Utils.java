package com.mystudy.ui;

import android.content.Context;
import android.widget.Toast;

/**
 * this is a class for tools
 * 
 * @author Administrator
 * 
 */
public class Utils {

	// this is a instance for toast
	private static Toast toast;

	public static void makeToast(Context mContext, String msg) {
		// initial the toast
		if (toast == null) {
			toast = Toast.makeText(mContext, msg, 0);
		}
		// set the text for toast
		toast.setText(msg);
		// the toast show
		toast.show();
	}
}
