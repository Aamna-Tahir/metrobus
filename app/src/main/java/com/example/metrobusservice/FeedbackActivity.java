package com.example.metrobusservice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.TextView;

public class FeedbackActivity extends AppCompatActivity {
    TextView tvFeedBck;
    RatingBar fbStars;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        getSupportActionBar().hide();

        tvFeedBck=findViewById(R.id.rateTxtView);
        fbStars=findViewById(R.id.rateBarStars);

        fbStars.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                if(rating==0){
                    tvFeedBck.setText("Very Dissatisfied");
                }
                else if(rating==1){
                    tvFeedBck.setText("Dissatisfied");
                }
                else if(rating==2 || rating==3){
                    tvFeedBck.setText("OK");
                }
                else if(rating==4){
                    tvFeedBck.setText("Satisfied");
                }
                else if(rating==5){
                    tvFeedBck.setText("Very Satisfied");
                }
                else{

                }
            }
        });
    }
}