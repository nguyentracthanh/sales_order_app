package vn.edu.usth.ordernow;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import info.hoang8f.widget.FButton;
import vn.edu.usth.ordernow.Database.Databases;
import vn.edu.usth.ordernow.Model.Order;
import vn.edu.usth.ordernow.connect.Requests;
import vn.edu.usth.ordernow.ViewHolder.CartAdapter;
import vn.edu.usth.ordernow.common.Common;

public class Cart extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    DatabaseReference requests;

    TextView txtTotalprice;
    FButton btnOrder;

    List<Order> cart=new ArrayList<>();
    CartAdapter cartAdapter;
    String URL= "http://192.168.43.196/test_android/request.php";

    JSONParser jsonParser=new JSONParser();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        //Firebase

//        database=FirebaseDatabase.getInstance();
//        requests=database.getReference("Requests");


        //init

        recyclerView=findViewById(R.id.listCart);
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        txtTotalprice=findViewById(R.id.total);
        btnOrder=findViewById(R.id.btn_get_Order);

        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialog();
                Request request=new Request();
                request.execute(txtTotalprice.getText().toString());
            }
        });
        loadListFood();

    }

    private void showAlertDialog() {

        AlertDialog.Builder alertDialog=new AlertDialog.Builder(Cart.this);
        alertDialog.setIcon(R.drawable.ic_shopping_cart_black_24dp);
        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Requests request = new Requests(
                        Common.currentUser.getName(),
                        Common.currentUser.getPhone(),
                        txtTotalprice.getText().toString(),
                        cart);
//                Submit
                requests.child(String.valueOf(System.currentTimeMillis()))
                        .setValue(request);
                new Databases(getBaseContext()).cleanCart();
                Toast.makeText(Cart.this, "Thank you", Toast.LENGTH_SHORT).show();
                finish();

            }

        });
        alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertDialog.show();

    }

    private class Request extends AsyncTask<String, String, JSONObject> {

        @Override

        protected void onPreExecute() {

            super.onPreExecute();

        }

        @Override

        protected JSONObject doInBackground(String... args) {



            String total = args[2];
            String list_food = args[1];
            String name= args[0];


            ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("username", name));
            params.add(new BasicNameValuePair("total", total));
            params.add(new BasicNameValuePair("list_food",list_food));

            JSONObject json = jsonParser.makeHttpRequest(URL, "POST", params);


            return json;

        }

        protected void onPostExecute(JSONObject result) {

            // dismiss the dialog once product deleted
            //Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();

            try {
                if (result != null) {
                    Toast.makeText(getApplicationContext(),result.getString("message"),Toast.LENGTH_LONG).show();

                        Intent intent=new Intent(Cart.this,Home.class);
                        startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Unable to retrieve any data from server", Toast.LENGTH_LONG).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }

    }
    private void loadListFood() {

        cart=new Databases(this).getCarts();
        cartAdapter=new CartAdapter(cart,this);
        recyclerView.setAdapter(cartAdapter);

        //calulate

        int total=0;
        for (Order order:cart)
            total+=(Integer.parseInt(order.getPrice()))*(Integer.parseInt(order.getQuantity()));
        Locale locale=new Locale("en","US");
        NumberFormat fmt=NumberFormat.getCurrencyInstance(locale);
        txtTotalprice.setText(fmt.format(total));

    }
}
