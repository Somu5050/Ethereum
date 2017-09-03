package com.somu.darwinslab.ethereum.Util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by somu on 02-09-2017.
 */

public class ToastMessage {

    private Context context;
    private Toast toast;

    public ToastMessage(Context context, Toast toast) {
        this.context = context;
        this.toast = toast;
    }

    public void displayToast(String message) {
        if(toast != null)
            toast.cancel();
        toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        toast.show();
    }
}
