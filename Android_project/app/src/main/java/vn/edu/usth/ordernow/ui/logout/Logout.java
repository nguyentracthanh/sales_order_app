package vn.edu.usth.ordernow.ui.logout;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import vn.edu.usth.ordernow.MainActivity;
import vn.edu.usth.ordernow.R;

public class Logout extends Fragment {



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


       Intent intent=new Intent(getActivity(), MainActivity.class);
       startActivity(intent);
       getActivity().finish();
        return null;
    }
}