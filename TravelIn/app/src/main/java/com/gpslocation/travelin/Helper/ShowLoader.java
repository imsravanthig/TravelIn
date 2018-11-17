package com.gpslocation.travelin.Helper;

import android.app.ProgressDialog;
import android.content.Context;

public class ShowLoader {
    static ProgressDialog progressDialog;

    public static void Loader(Context context) {
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Please wait...");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
    }

    public static void Dismiss(){
        progressDialog.dismiss();
    }
}
