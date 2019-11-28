package vn.edu.usth.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class Open_fragment extends Fragment {

    Button btnLog;
    TextView btnSign;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_open_fragment, container, false);
        btnLog= view.findViewById(R.id.login);
        btnSign= view.findViewById(R.id.signin);
        addListener();
        return view;
    }

    private void addListener(){
        btnSign.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

               Toast.makeText(v.getContext(),"Create new account",Toast.LENGTH_SHORT).show();
                Create nextFrag= new Create();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, nextFrag, "findThisFragment")
                        .addToBackStack(null)
                        .commit();

            }

        });

        btnLog.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                Toast.makeText(v.getContext(),"Login",Toast.LENGTH_SHORT).show();
                Login nextFrag= new Login();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, nextFrag, "findThisFragment")
                        .addToBackStack(null)
                        .commit();
            }
        });
    }








}
