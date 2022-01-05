package com.example.macruduinative;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.macruduinative.database.DrugViewModel;

public class NetworkBroadcastReceiver extends BroadcastReceiver {

    private DrugViewModel drugViewModel;

    public NetworkBroadcastReceiver(DrugViewModel drugViewModel){
        this.drugViewModel = drugViewModel;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        try{
            if(isOnline(context)){
                Toast.makeText(context, "Network Connected. Syncing data.", Toast.LENGTH_LONG).show();
                drugViewModel.syncOfflineData();
            }
            else{
                Toast.makeText(context, "No Network Connection", Toast.LENGTH_LONG).show();
            }
        }catch (NullPointerException e){
            e.printStackTrace();
        }
    }

    public boolean isOnline(Context context){
        try{
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = cm.getActiveNetworkInfo();
            return (networkInfo!=null && networkInfo.isConnected());
        }catch (NullPointerException e){
            e.printStackTrace();
            return false;
        }
    }
}