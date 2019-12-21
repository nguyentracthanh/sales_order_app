package vn.edu.usth.ordernow.connect;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Connectionclass {
    String classs="com.mysql.jdbc.Driver";
    String url="jdbc:mysql://192.168.43.196:3306/sales_management";
    String un="aloalo";
    String password="123";

    @SuppressLint("NewApi")
    public Connection CONN(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection conn=null;
        String ConnURL=null;
        try{

            Class.forName(classs);

            conn= DriverManager.getConnection(url,un,password);
            conn= DriverManager.getConnection(ConnURL);
        } catch (SQLException se){
            Log.e("ERROR",se.getMessage());
        } catch (ClassNotFoundException e){
            Log.e("ERROR",e.getMessage());
        } catch (Exception e){
            Log.e("ERROR",e.getMessage());
        }
        return conn;
    }

}
