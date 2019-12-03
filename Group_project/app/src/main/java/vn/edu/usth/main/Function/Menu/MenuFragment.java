package vn.edu.usth.main.Function.Menu;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import vn.edu.usth.main.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MenuFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container2,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();

        ProfileFragment avt_fragment = new ProfileFragment();
        avt_fragment.setArguments(this.getArguments());

        functionFragment function_fragment = new functionFragment();
        function_fragment.setArguments(this.getArguments());

        fragmentTransaction.replace(R.id.avt_fr, avt_fragment);
        fragmentTransaction.replace(R.id.function_fr, function_fragment);

        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);

        fragmentTransaction.commit();

        LinearLayout fragment_weather = (LinearLayout) inflater.inflate(R.layout.fragment_menu,container2,false);

        return fragment_weather;
    }

}
