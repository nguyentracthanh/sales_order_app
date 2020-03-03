package vn.edu.usth.ordernow.ui.cart;

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

import vn.edu.usth.ordernow.Cart;
import vn.edu.usth.ordernow.Database.Databases;
import vn.edu.usth.ordernow.R;
import vn.edu.usth.ordernow.ViewHolder.CartAdapter;

public class CartFragment extends Fragment {



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        Intent intent = new Intent(getActivity(), Cart.class);
        startActivity(intent);
        return null;
    }


}