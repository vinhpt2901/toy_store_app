package com.toast;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.shopee.R;

public class CustomToast extends Toast {

    public CustomToast(Context context) {
        super(context);
    }

    public static Toast makeText(Context context, String message, int duration) {
        View layout = LayoutInflater.from(context).inflate(R.layout.customtoast_layout, null, false);
        Toast toast = new Toast(context);
        toast.setDuration(duration);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setView(layout);
        return toast;
    }
}
