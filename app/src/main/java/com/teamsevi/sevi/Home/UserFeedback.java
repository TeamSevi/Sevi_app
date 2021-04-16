package com.teamsevi.sevi.Home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import com.teamsevi.sevi.R;

public class UserFeedback extends AppCompatActivity {

    private RatingBar ratingBar;
    private TextView RateMessage;
    private int ratedValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_user_feedback);

        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        RateMessage = (TextView) findViewById(R.id.RateMessage);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {

            @Override

            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                ratedValue = (int) ratingBar.getRating();

                if(ratedValue==0){   RateMessage.setText("ohh ho...");     }
                else if(ratedValue==1){  RateMessage.setText("Ok.");   }
                else if(ratedValue==2){  RateMessage.setText("Not bad.");  }
                else if(ratedValue==3){  RateMessage.setText("Nice");  }
                else if(ratedValue==4){  RateMessage.setText("Very Nice");     }
                else if(ratedValue==5){  RateMessage.setText("Thank you..!!!");    }

            }

        });

    }

    public void callHomeScreen(View view) {
        super.onBackPressed();
    }
}