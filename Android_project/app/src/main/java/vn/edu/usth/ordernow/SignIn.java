package vn.edu.usth.ordernow;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import vn.edu.usth.ordernow.Model.User;
import vn.edu.usth.ordernow.common.Common;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SignIn extends AppCompatActivity {
    EditText edtPhone, editPass;
    Button btnSignin;
    String URL = "http://192.168.1.8/test_android/index.php";
    ProgressDialog mDialog;
    JSONParser jsonParser = new JSONParser();

    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);


        editPass = findViewById(R.id.editPass);
        edtPhone = findViewById(R.id.editPhone);
        btnSignin = findViewById(R.id.btn_signin);


        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AttemptLogin attemptLogin= new AttemptLogin();
                attemptLogin.execute(edtPhone.getText().toString(),editPass.getText().toString(),"");
            }
        });
        // Firebase

//        FirebaseDatabase database=FirebaseDatabase.getInstance();
//        final DatabaseReference table_user=database.getReference("User");
//        btnSignin.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//
//
//                final ProgressDialog mDialog=new ProgressDialog(SignIn.this);
//                mDialog.setMessage("Please waiting...");
//                mDialog.show();
//                table_user.addListenerForSingleValueEvent(new ValueEventListener(){
//
//                    @Override
//                    public void onDataChange(DataSnapshot dataSnapshot) {
//                        //check user exits or not
//                        if (dataSnapshot.child(edtPhone.getText().toString()).exists()) {
//
//                            //Get information
//                            mDialog.dismiss();
//
//                            User user = dataSnapshot.child(edtPhone.getText().toString()).getValue(User.class);
//                            if (user.getPassword().equals(editPass.getText().toString())) {
//                                {
//                                    Intent homeIntent = new Intent(SignIn.this, Home.class);
//                                    Common.currentUser = user;
//                                    startActivity(homeIntent);
//                                    finish();
//                                }
//                            } else {
//                                Toast.makeText(SignIn.this, "Sign in false!", Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                        else {
//                            Toast.makeText(SignIn.this, "User is not existed!", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//
//                    @Override
//                    public void onCancelled(DatabaseError databaseError) {
//
//                    }
//                });
//            }
//        });
//    }
    }
    private class AttemptLogin extends AsyncTask<String, String, JSONObject> {

        @Override

        protected void onPreExecute() {

            super.onPreExecute();

        }

        @Override

        protected JSONObject doInBackground(String... args) {




            String password = args[1];

            String phone=args[2];

            ArrayList params = new ArrayList();
            if(phone.length()>0)
                params.add(new BasicNameValuePair("mobile_phone",phone));
            params.add(new BasicNameValuePair("password", password));



            JSONObject json = jsonParser.makeHttpRequest(URL, "POST", params);
            

            return json;

        }

        protected void onPostExecute(JSONObject result) {

            // dismiss the dialog once product deleted
            //Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();

            try {
                if (result != null) {
                    Toast.makeText(getApplicationContext(),result.getString("message"),Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(SignIn.this,Home.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Unable to retrieve any data from server", Toast.LENGTH_LONG).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }

    }
}
