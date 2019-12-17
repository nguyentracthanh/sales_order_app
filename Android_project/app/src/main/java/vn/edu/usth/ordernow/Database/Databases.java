package vn.edu.usth.ordernow.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

import vn.edu.usth.ordernow.Model.Order;

public class Databases extends SQLiteOpenHelper {
    private static final String ORDER_DETAIL = "OrderDetail";
    private static final String DB_NAME="database.db";
    private static final int DB_VER=1;
    public Databases(Context context) {
        super( context, DB_NAME, null , DB_VER);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryCreateTable = "CREATE TABLE " + ORDER_DETAIL + " ( " +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                "ProductID VARCHAR(255) NOT NULL, "+
                "ProductName VARCHAR (255) NOT NULL, " +
                "Quantity VARCHAR (255) DEFAULT(0), " +
                "Price VARCHAR DEFAULT (0)," +
                "Discount VARCHAR (255) NOT NULL )";

        db.execSQL(queryCreateTable);

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Xoá bảng cũ
        db.execSQL("DROP TABLE IF EXISTS " + ORDER_DETAIL);
        //Tiến hành tạo bảng mới
        onCreate(db);
    }





    public List<Order> getCarts(){

        SQLiteDatabase db= getReadableDatabase();
        SQLiteQueryBuilder qb= new SQLiteQueryBuilder();
        String[] sqlSelect = {"ProductID","ProductName","Quantity","Price","Discount"};
        String sqltable="OrderDetail";
        qb.setTables(sqltable);

        Cursor c=qb.query(db,sqlSelect,null,null,null,null,null);

        final List<Order> result=new ArrayList<>();
        if (c.moveToFirst()){

            do {
                result.add(new Order(c.getString(c.getColumnIndex("ProductName")),
                        c.getString(c.getColumnIndex("ProductID")),
                        c.getString(c.getColumnIndex("Quantity")),
                        c.getString(c.getColumnIndex("Price")),
                        c.getString(c.getColumnIndex("Discount"))));
            } while (c.moveToNext());

        }
        return result;
    }

    public void addToCart(Order order){

        SQLiteDatabase db=getReadableDatabase();
        String query=String.format("INSERT INTO OrderDetail(ProductID,ProductName,Quantity,Price,Discount) VALUES('%s','%s','%s','%s','%s');",
                order.getProductId(),
                order.getProductName(),
                order.getQuantity(),
                order.getPrice(),
                order.getDiscount());
        db.execSQL(query);
    }
    public void cleanCart(){
        SQLiteDatabase db=getReadableDatabase();
        String query=String.format("DELETE FROM OrderDetail");
        db.execSQL(query);
    }



}
