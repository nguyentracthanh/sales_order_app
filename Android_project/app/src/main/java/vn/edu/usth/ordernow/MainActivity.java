package vn.edu.usth.ordernow;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button btnSignup, btnSignin;
    TextView txtSlogan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSignin=findViewById(R.id.btn_signin);
        btnSignup=findViewById(R.id.btn_signup);
        txtSlogan = findViewById(R.id.text_slogan);
        Typeface face= Typeface.createFromAsset(getAssets(),"fonts/cambriab.ttf");
        txtSlogan.setTypeface(face);
        btnSignin.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

            }
        });
        btnSignup.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

            }
        });
        btnSignin.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent signIn=new Intent(MainActivity.this, SignIn.class);
                startActivity(signIn);
            }
        });
        btnSignup.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent signup=new Intent(MainActivity.this, SignUp.class);
                startActivity(signup);
            }
        });
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] grantResults) {
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.INTERNET)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
        }
    }
}
