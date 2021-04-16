package com.teamsevi.sevi.Home;

import androidx.appcompat.app.AppCompatActivity;
import android.net.Uri;
import android.os.Bundle;
import android.content.Intent;
import android.widget.Button;
import android.view.View;
import com.teamsevi.sevi.R;

public class ContactUs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_contact_us);
    }

    public void ShowDialer(View view) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:+919265554533"));
        startActivity(intent);
    }

    public void ComposeEmail(View view){
        Intent intent=new Intent(Intent.ACTION_SEND);
        String[] recipients={"anshumankalathiya@gmail.com"};
        intent.putExtra(Intent.EXTRA_EMAIL, recipients);
        intent.putExtra(Intent.EXTRA_CC,"mailcc@gmail.com");
        intent.setType("text/html");
        intent.setPackage("com.google.android.gm");
        startActivity(Intent.createChooser(intent, "Send mail"));
    }

    public void callHomeScreen(View view) {
        super.onBackPressed();
    }
}