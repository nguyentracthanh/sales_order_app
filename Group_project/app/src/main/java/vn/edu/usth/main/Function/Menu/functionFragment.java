package vn.edu.usth.main.Function.Menu;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vn.edu.usth.main.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class functionFragment extends Fragment {


    public functionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container_menu,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_function, container_menu, false);
    }

}
