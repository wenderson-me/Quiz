package br.com.quiz.model;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import br.com.quiz.R;
import br.com.quiz.controller.QuizActivity;

 @SuppressLint("Registered")
 public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    public void btnJogarOnClick(View v) {
        Intent intent = new Intent(this, QuizActivity.class);
        startActivity(intent);
    }
}
