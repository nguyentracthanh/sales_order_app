package vn.edu.usth.ordernow;

import android.content.Intent;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

import vn.edu.usth.ordernow.Interface.ItemClickListener;
import vn.edu.usth.ordernow.Model.Category;
import vn.edu.usth.ordernow.ViewHolder.MenuViewHolder;
import vn.edu.usth.ordernow.common.Common;

public class Home extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference category;
    TextView txtFullName;
    RecyclerView recycler_menu;
    RecyclerView.LayoutManager layoutManager;
   FirebaseRecyclerAdapter<Category, MenuViewHolder> adapter;
    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Menu");
        setSupportActionBar(toolbar);


        // Init firebase
        database=FirebaseDatabase.getInstance();
        category=database.getReference("categories");


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Home.this,Cart.class);
                startActivity(intent);
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_menu, R.id.nav_cart,
                R.id.nav_log_out,R.id.nav_exit)
                .setDrawerLayout(drawer)
                .build();


        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        //Set name for user
        View headerView=navigationView.getHeaderView(0);
        txtFullName=headerView.findViewById(R.id.txtFullname);
//        txtFullName.setText(Common.currentUser.getName());

        //Load Menu
        recycler_menu=findViewById(R.id.recycler_menu);
        recycler_menu.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        recycler_menu.setLayoutManager(layoutManager);
        loadMenu();
    }

    private void loadMenu() {
       adapter=new FirebaseRecyclerAdapter<Category, MenuViewHolder>(Category.class,
               R.layout.menu_item,
               MenuViewHolder.class,
               category) {
            @Override
            protected void populateViewHolder(MenuViewHolder menuViewHolder, Category category, int i) {
                menuViewHolder.txtMenuName.setText(category.getName());
                Picasso.get().load(category.getImage()).into(menuViewHolder.imageView);
                final Category clickItem=category;
                menuViewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        //Get Category

                        Intent foodlist=new Intent(Home.this,FoodList.class);

                        //
                        foodlist.putExtra("CategoryId",adapter.getRef(position).getKey());
                        startActivity(foodlist);
                    }
                });
            }
        };

        recycler_menu.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
