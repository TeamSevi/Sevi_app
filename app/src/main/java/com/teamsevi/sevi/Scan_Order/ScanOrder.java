package com.teamsevi.sevi.Scan_Order;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.se.omapi.Session;
import android.util.Log;
import android.content.SharedPreferences;
import com.teamsevi.sevi.Database.SessionManager;
import com.teamsevi.sevi.Home.HomePage;
import com.teamsevi.sevi.Login_Signup.LoginScreen;
import com.teamsevi.sevi.R;
import me.dm7.barcodescanner.zbar.ZBarScannerView;
import com.teamsevi.sevi.Hotel_Menu.Hotel1;


public class ScanOrder extends AppCompatActivity implements ZBarScannerView.ResultHandler {
    private ZBarScannerView mScannerView;
    SharedPreferences pref;
    public String scnres;
    //camera permission is needed.
    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        mScannerView = new ZBarScannerView(this);    // Programmatically initialize the scanner view
        setContentView(mScannerView);                // Set the scanner view as the content view
    }
    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        mScannerView.startCamera();          // Start camera on resume
    }
    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();           // Stop camera on pause
    }
    @Override
    public void handleResult(me.dm7.barcodescanner.zbar.Result result) {
        // Do something with the result here
        Log.v("kkkk", result.getContents());
        Log.v("uuuu", result.getBarcodeFormat().getName());

        scnres=result.getContents();
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("scan_result",scnres);
        editor.commit();
        //hotel_list.tvresult.setText(result.getContents());
        onBackPressed();
        // If you would like to resume scanning, call this method below:
        //mScannerView.resumeCameraPreview(this);
    }
}