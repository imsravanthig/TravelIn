package com.gpslocation.travelin.Helper;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;

/**
 * Created by HOME on 7/2/2017.
 */
public class CheckInternetConnection {

    public static boolean isInternetOn(Context mContext) {

        // get Connectivity Manager object to check connection
        ConnectivityManager connec =
                (ConnectivityManager)mContext. getSystemService(mContext.getApplicationContext().CONNECTIVITY_SERVICE);

        // Check for network connections
        if (connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTED ||
                connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTED) {

            // if connected with internet

            return true;

        } else if (
                connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.DISCONNECTED ||
                        connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.DISCONNECTED) {

            //    Toast.makeText(this, "Check your internet connection", Toast.LENGTH_LONG).show();

            AlertDialog.Builder alertBuilder=new AlertDialog.Builder(mContext);
            alertBuilder.setMessage("Please switch on your internet");
            alertBuilder.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });

            alertBuilder.show();
            return false;
        }
        return false;
    }
}
