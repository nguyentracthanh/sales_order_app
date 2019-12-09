
package vn.edu.usth.ordernow;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

import vn.edu.usth.ordernow.Model.User;

public class SignUp extends AppCompatActivity {
    MaterialEditText editPhone, editName, editPass;
    Button btnSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        editName=findViewById(R.id.editName);
        editPass=findViewById(R.id.editPass);
        editPhone=findViewById(R.id.editPhone);
        btnSignup=findViewById(R.id.btn_signup);


        // Firebase

        FirebaseDatabase database=FirebaseDatabase.getInstance();
        final DatabaseReference table_user=database.getReference("User");
        btnSignup.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                final ProgressDialog mDialog=new ProgressDialog(SignUp.this);
                mDialog.setMessage("Please waiting...");
                mDialog.show();
                table_user.addListenerForSingleValueEvent(new ValueEventListener(){

                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.child(editPhone.getText().toString()).exists()){
                            mDialog.dismiss();
                        }
                        else {
                            mDialog.dismiss();
                            User user=new User(editName.getText().toString(),editPass.getText().toString());
                            table_user.child(editPhone.getText().toString()).setValue(user);
                            finish();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });



            }
        });
    }

}
