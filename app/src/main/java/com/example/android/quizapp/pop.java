package com.example.android.quizapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by android on 2017.12.02..
 */

public class pop extends Activity {

    public EditText editText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pop);

        //set the pop up window smaller than the Device screen
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8), (int)(height*.6));

        editText = findViewById(R.id.pop_getName);

    }

    //Method for send the name back for the MainActivity
    public void getName(View view) {
        Intent i = new Intent();
        i.putExtra("name", editText.getText().toString());
        setResult(RESULT_OK,i);
        finish();
    }
}
