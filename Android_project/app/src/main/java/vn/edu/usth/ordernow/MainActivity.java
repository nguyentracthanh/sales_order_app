package vn.edu.usth.ordernow;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;
import vn.edu.usth.ordernow.common.Common;
import vn.edu.usth.ordernow.Model.User;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText editEmail, editPassword, editName, editPhone;
    Button btnSignIn, btnRegister;

    String URL= "http://192.168.43.196/test_android/index.php";

    JSONParser jsonParser=new JSONParser();

    int i=0;
    TextView slg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (getIntent().getBooleanExtra("LOGOUT", false))
        {
            finish();
        }
        slg=findViewById(R.id.slogan);
        Typeface typeface=Typeface.createFromAsset(getAssets(), "fonts/HALLEGIE.TTF");
        slg.setTypeface(typeface);


        editEmail=(EditText)findViewById(R.id.editEmail);
        editName=(EditText)findViewById(R.id.editName);
        editPassword=(EditText)findViewById(R.id.editPassword);
        editPhone=findViewById(R.id.editPhone);

        btnSignIn=(Button)findViewById(R.id.btnSignIn);
        btnRegister=(Button)findViewById(R.id.btnRegister);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                AttemptLogin attemptLogin= new AttemptLogin();
//                attemptLogin.execute(editName.getText().toString(),editPassword.getText().toString(),"");
                Intent intent=new Intent(MainActivity.this,Home.class);
                startActivity(intent);
                finish();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(i==0)
                {
                    i=1;
                    editEmail.setVisibility(View.VISIBLE);
                    editPhone.setVisibility(View.VISIBLE);
                    btnSignIn.setVisibility(View.GONE);
                    btnRegister.setText("CREATE ACCOUNT");
                }
                else{

                    btnRegister.setText("REGISTER");
                    editEmail.setVisibility(View.GONE);
                    editPhone.setVisibility(View.GONE);
                    btnSignIn.setVisibility(View.VISIBLE);
                    i=0;

                    AttemptLogin attemptLogin= new AttemptLogin();
                    attemptLogin.execute(editName.getText().toString(),editPassword.getText().toString(),editEmail.getText().toString(),editPhone.getText().toString());

                }

            }
        });


    }

    private class AttemptLogin extends AsyncTask<String, String, JSONObject> {

        @Override

        protected void onPreExecute() {

            super.onPreExecute();

        }

        @Override

        protected JSONObject doInBackground(String... args) {



            String email = args[2];
            String password = args[1];
            String name= args[0];
            String phone=args[2];

            ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("username", name));
            params.add(new BasicNameValuePair("password", password));
            if(email.length()>0)
                params.add(new BasicNameValuePair("email",email));
            if(phone.length()>0)
                params.add(new BasicNameValuePair("phone",phone));

            JSONObject json = jsonParser.makeHttpRequest(URL, "POST", params);


            return json;

        }

        protected void onPostExecute(JSONObject result) {

            // dismiss the dialog once product deleted
            //Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();
            User user = new User();

            try {
                if (result != null) {
                    Toast.makeText(getApplicationContext(),result.getString("message"),Toast.LENGTH_LONG).show();
                    if (result.getInt("success")==1)
                    {
                        Intent intent=new Intent(MainActivity.this,Home.class);
                        Common.currentUser = user;
                        startActivity(intent);

                    }


                } else {
                    Toast.makeText(getApplicationContext(), "Unable to retrieve any data from server", Toast.LENGTH_LONG).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }

    }
}
