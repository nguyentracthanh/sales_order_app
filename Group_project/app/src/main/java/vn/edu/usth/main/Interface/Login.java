package vn.edu.usth.main.Interface;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import vn.edu.usth.main.Function.Main_interface;
import vn.edu.usth.main.R;


public class Login extends Fragment {
    Button btnlogin;
    EditText edit_name;
    EditText edit_pass;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        btnlogin = view.findViewById(R.id.login2);
        edit_name= view.findViewById(R.id.edit_username);
        edit_pass=view.findViewById((R.id.edit_password));
        addListener();
        return view;
    }

    private void addListener() {
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                edit_name.getText().toString();
                edit_pass.getText().toString();
                Log.d("checker",edit_name.getText().toString());
                Log.d("checker",edit_pass.getText().toString());
                if (("thanhnt.usth@gmail.com".equals(edit_name.getText().toString()))&&("thanhnt".equals(edit_pass.getText().toString()) )){
                    Toast.makeText(v.getContext(), "Login success", Toast.LENGTH_SHORT).show();
                    Main_interface nextFrag = new Main_interface();
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, nextFrag)
                            .addToBackStack(null)
                            .commit();}
                else {
                    Toast.makeText(v.getContext(), " Login fail ", Toast.LENGTH_SHORT).show();

                }
            }

        });
    }
}
