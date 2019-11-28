package vn.edu.usth.main;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("onCreate","The weather is being created");
        setContentView(R.layout.activity_main);
    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.i("abc","The weather is being started");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("abc","The weather is being stopped");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("abc","The weather is being destroyed");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("abc","The weather is being paused");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("abc","The weather is being resumed");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("abc","The weather is being restarted");
    }
}