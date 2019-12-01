package vn.edu.usth.main.Function;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;

import vn.edu.usth.main.R;


public class Main_interface extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container2,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_main_interface, container2, false);
        final PagerAdapter adapter = new HomePagerAdapter(
                getActivity().getSupportFragmentManager());
        final ViewPager pager= view.findViewById(R.id.pager);
        pager.setOffscreenPageLimit(3);
        pager.setAdapter(adapter);
        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(pager);
        // Inflate the layout for this fragment

        return view;
    }


}
