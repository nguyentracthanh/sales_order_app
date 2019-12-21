
package vn.edu.usth.ordernow;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.rengwuxian.materialedittext.MaterialEditText;


import java.sql.Connection;
import java.sql.Statement;

import vn.edu.usth.ordernow.connect.Connectionclass;

public class SignUp extends AppCompatActivity {
    MaterialEditText editPhone, editName, editPass, editMail;
    Button btnSignup;
    ProgressDialog mDialog;
    Connectionclass connectionclass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mDialog =new ProgressDialog(this);
        connectionclass = new Connectionclass();

        editMail=findViewById(R.id.editEmail);
        editName = findViewById(R.id.editName);
        editPass = findViewById(R.id.editPass);
        editPhone = findViewById(R.id.editPhone);
        btnSignup = findViewById(R.id.btn_signup);




        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Doregister doregister = new Doregister();
                doregister.execute("");
            }
        });
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

    public class Doregister extends AsyncTask {

        String namestr=editName.getText().toString();
        String passstr=editPass.getText().toString();
        String phonestr=editPhone.getText().toString();
        String mailstr=editMail.getText().toString();
        String z="";
        Boolean isSuccess=false;

        @Override
        protected void onPreExecute() {

            mDialog.setMessage("Loading.....");
            mDialog.show();
        }

        @Override
        protected Object doInBackground(Object[] objects) {
            if (namestr.trim().equals("")||mailstr.trim().equals("")||phonestr.trim().equals("")||passstr.trim().equals(""))
                z="Please enter all fields!";
            else {
                try{
                    Connection con= connectionclass.CONN();
                    if(con.equals(null)){
                        z="Please check your connection";
                    }else {

                       String query="INSERT INTO staff (name,email_address,mobile_phone,password) VALUES('"+namestr+"','"+mailstr+"','"+phonestr+"','"+passstr+"');";
                       Statement statement = con.createStatement();
                       statement.executeQuery(query);
                       z="Register Successful";
                       isSuccess=true;

                    }
                    }
                catch (Exception ex)
                    {
                        isSuccess=false;
                        z="Exception"+ex;
                    }
            }
            return z;
        }

        @Override
        protected void onPostExecute(Object o) {
            if (isSuccess) {

                Toast.makeText(getBaseContext(), "" + z, Toast.LENGTH_SHORT).show();
            }

            mDialog.hide();
        }
    }

}