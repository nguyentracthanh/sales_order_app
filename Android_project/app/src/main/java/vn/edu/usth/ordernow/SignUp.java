
package vn.edu.usth.ordernow;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.rengwuxian.materialedittext.MaterialEditText;


import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;
public class SignUp extends AppCompatActivity {

    MaterialEditText editPhone, editName, editPass, editMail;
    Button btnSignup;
    ProgressDialog mDialog;
    String URL= "http://192.168.1.8/test_android/index.php";

    JSONParser jsonParser=new JSONParser();

    int i=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mDialog =new ProgressDialog(this);


        editMail=findViewById(R.id.editEmail);
        editName = findViewById(R.id.editName);
        editPass = findViewById(R.id.editPass);
        editPhone = findViewById(R.id.editPhone);
        btnSignup = findViewById(R.id.btn_signup);


        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                    AttemptLogin attemptLogin= new AttemptLogin();
                    attemptLogin.execute(editName.getText().toString(),editMail.getText().toString(),editPhone.getText().toString(),editPass.getText().toString());
                    mDialog.setMessage("Please waiting...");
                    mDialog.show();
                    finish();

            }
        });


//        btnSignup.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Doregister doregister = new Doregister();
//                doregister.execute("");
//            }
//        });
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

            ArrayList params = new ArrayList();
            params.add(new BasicNameValuePair("name", name));
            if(email.length()>0)
                params.add(new BasicNameValuePair("email_adress",email));


            if(phone.length()>0)
                params.add(new BasicNameValuePair("mobile_phone",phone));
            params.add(new BasicNameValuePair("password", password));

            JSONObject json = jsonParser.makeHttpRequest(URL, "POST", params);
            mDialog.dismiss();

            return json;

        }

        protected void onPostExecute(JSONObject result) {

            // dismiss the dialog once product deleted
            //Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();

            try {
                if (result != null) {
                    Toast.makeText(getApplicationContext(),result.getString("message"),Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Unable to retrieve any data from server", Toast.LENGTH_LONG).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }

    }

        // Firebase

//        FirebaseDatabase database=FirebaseDatabase.getInstance();
//        final DatabaseReference table_user=database.getReference("User");
//        btnSignup.setOnClickListener(new View.OnClickListener(){
//
//            @Override
//            public void onClick(View v) {
//
//                final ProgressDialog mDialog=new ProgressDialog(SignUp.this);
//                mDialog.setMessage("Please waiting...");
//                mDialog.show();
//                table_user.addListenerForSingleValueEvent(new ValueEventListener(){
//
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                        if (dataSnapshot.child(editPhone.getText().toString()).exists()){
//                            mDialog.dismiss();
//                        }
//                        else {
//                            mDialog.dismiss();
//                            User user=new User(editName.getText().toString(),editPass.getText().toString());
//                            user.setPhone(editPhone.getText().toString());
//                            table_user.child(editPhone.getText().toString()).setValue(user);
//                            finish();
//                        }
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                    }
//                });
//
//
//
//            }
//        });
//    }

//    public class Doregister extends AsyncTask {
//
//        String namestr=editName.getText().toString();
//        String passstr=editPass.getText().toString();
//        String phonestr=editPhone.getText().toString();
//        String mailstr=editMail.getText().toString();
//        String z="";
//        Boolean isSuccess=false;
//
//        @Override
//        protected void onPreExecute() {
//
//            mDialog.setMessage("Loading.....");
//            mDialog.show();
//        }
//
//        @Override
//        protected Object doInBackground(Object[] objects) {
//            if (namestr.trim().equals("")||mailstr.trim().equals("")||phonestr.trim().equals("")||passstr.trim().equals(""))
//                z="Please enter all fields!";
//            else {
//                try{
//                    Connection con= connectionclass.CONN();
//                    if(con.equals(null)){
//                        z="Please check your connection";
//                    }else {
//
//                       String query="INSERT INTO staff (name,email_address,mobile_phone,password) VALUES('"+namestr+"','"+mailstr+"','"+phonestr+"','"+passstr+"');";
//                       Statement statement = con.createStatement();
//                       statement.executeQuery(query);
//                       z="Register Successful";
//                       isSuccess=true;
//
//                    }
//                    }
//                catch (Exception ex)
//                    {
//                        isSuccess=false;
//                        z="Exception"+ex;
//                    }
//            }
//            return z;
//        }
//
//        @Override
//        protected void onPostExecute(Object o) {
//            if (isSuccess) {
//
//                Toast.makeText(getBaseContext(), "" + z, Toast.LENGTH_SHORT).show();
//            }
//
//            mDialog.hide();
//        }
//    }

}