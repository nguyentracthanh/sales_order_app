package vn.edu.usth.ordernow.ui.menu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import vn.edu.usth.ordernow.R;

public class Home extends Fragment {



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        homeViewModel =


//                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_menu, container, false);
//        final TextView textView = root.findViewById(R.id.text_home);
//        homeViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        return root;
    }

}