package vn.edu.usth.ordernow.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import vn.edu.usth.ordernow.Interface.ItemClickListener;
import vn.edu.usth.ordernow.R;

public class FoodViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {



    public TextView foodName;
    public ImageView imageFood;
    private ItemClickListener itemClickListener;

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public FoodViewHolder(@NonNull View itemView) {
        super(itemView);

        foodName=itemView.findViewById(R.id.food_name);
        imageFood=itemView.findViewById(R.id.food_img);

        itemView.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v,getAdapterPosition(),false);

    }
}
