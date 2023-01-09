package com.example.hellocompat;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView mHelloTextView;
    private String[] mColorArray = {"red", "pink", "purple", "deep_purple",
            "indigo", "blue", "light_blue", "cyan", "teal", "green",
            "light_green", "lime", "yellow", "amber", "orange", "deep_orange",
            "brown", "grey", "blue_grey", "black" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mHelloTextView = findViewById(R.id.hello_textview);

        // restore saved instance state (the text colour)
        if (savedInstanceState != null){
            mHelloTextView.setTextColor(savedInstanceState.getInt("color"));
        }

    }

    public void changeColor(View view) {
        Random random = new Random();
        String colorName = mColorArray[random.nextInt(20)];
        int colorResourceName = getResources().getIdentifier(colorName, "color", getApplicationContext().getPackageName());
//        the next line will crash since getColor isn't a supported method for this API
//        int colorRes = getResources().getColor(colorResourceName, this.getTheme());
//        better to use ContextCompat when getting the resources since will allow compatibility
//        it addresses API differences in the application context and resource files
        int colorRes = ContextCompat.getColor(this,colorResourceName);
        mHelloTextView.setTextColor(colorRes);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("color", mHelloTextView.getCurrentTextColor());
    }
}