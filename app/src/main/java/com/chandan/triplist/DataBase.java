package com.chandan.triplist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DataBase extends SQLiteOpenHelper {
    private static ArrayList<DataModel> Alldata,boysData,girlsData;
    private static final String DATABASE_NAME = "TripList";
    private static final String TABLE_NAME = "data";
    private static final int DATABASE_VER = 1;
    private static final String NAME = "name";
    private static final String ROLL = "roll";
    private static final String MOBILE_NO = "mobile";
    private static final String AADHAR_NO = "aadhar";
    private static final String AMOUNT = "amount";
    private static final String PAYMENT_MOOD = "payment_mood";
    private static final String GENDER = "gender";

    public DataBase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VER);
    }

    public static ArrayList<DataModel> getAllData(){
        if(Alldata==null){
            Alldata = new ArrayList<DataModel>();
            getBoysData();
            getGirlsData();
        }
        Log.e("AllData Size",String.valueOf(Alldata.size()));
        return Alldata;
    }

    public static ArrayList<DataModel> getBoysData(){
        if(boysData == null){
            boysData = new ArrayList<DataModel>();
        }
        boysData.removeAll(boysData);
        for(DataModel e : Alldata){
            if(e.getGender().equalsIgnoreCase("MALE"))
                boysData.add(e);
        }
        Log.e("BoyData Size",String.valueOf(boysData.size()));
        return boysData;
    }

    public static ArrayList<DataModel> getGirlsData(){
        if(girlsData == null){
            girlsData = new ArrayList<DataModel>();
        }
        girlsData.removeAll(girlsData);
        for(DataModel e : Alldata){
            if(e.getGender().equalsIgnoreCase("FEMALE"))
                girlsData.add(e);

        }
        Log.e("GirlData Size",String.valueOf(girlsData.size()));
        return girlsData;
    }


//    private static void addRecord() {
//        Alldata = new ArrayList<DataModel>();
//
//        Alldata = fetchData();

//        Alldata.add(new DataModel("Chandan Sharma","24","8969893457","821061985417","due","","MALE"));
//        Alldata.add(new DataModel("Ritesh","175","6299960371","514074989929","2000","CASH","MALE"));
//        Alldata.add(new DataModel("Shivam","203","7061765628","464157757733","2000","CASH","MALE"));
//        Alldata.add(new DataModel("Rocky","94","9006442494","499463367907","2000","ONLINE","MALE"));
//        Alldata.add(new DataModel("Sahil","124","9905833882","463088369578","2000","ONLINE","MALE"));
//        Alldata.add(new DataModel("Kaushal","167","9931039215","551950399143","2000","CASH","MALE"));
//        Alldata.add(new DataModel("Shivam","107","9835072859","216003715556","2000","CASH","MALE"));
//        Alldata.add(new DataModel("Ankush","60","8292839485","596581203928","2000","ONLINE","MALE"));
//        Alldata.add(new DataModel("Aryan","28","7323982413","646398708226","due","","MALE"));
//        Alldata.add(new DataModel("soni Kumari","123","9263235967","257875085089","2000","CASH","FEMALE"));
//        Alldata.add(new DataModel("Kanhaiya","219","7352409219","364666520731","2000","CASH","MALE"));
//        Alldata.add(new DataModel("Kunal","259","9504714701","339524695822","2000","CASH","MALE"));
//        Alldata.add(new DataModel("Manish","80","7294062588","985702421176","2000","CASH","MALE"));
//        Alldata.add(new DataModel("Abhitesh","106","7667617547","510401586458","2000","CASH","MALE"));
//        Alldata.add(new DataModel("Rohan","","7870067280","526016593865","1500","ONLINE","MALE"));
//        Alldata.add(new DataModel("Himanshu","51","9934632124","990787276015","1500","ONLINE","MALE"));
//        Alldata.add(new DataModel("Saurabh Kumar","72","6203311780","933876083987","2000","ONLINE","MALE"));
//        Alldata.add(new DataModel("Rohit Kumar","86","9693901754","673596404579","2000","CASH","MALE"));
//        Alldata.add(new DataModel("Aman","177","9471438907","975122972864","2000","CASH","MALE"));
//        Alldata.add(new DataModel("Ritesh","175","6299960371","514074989929","2000","CASH","MALE"));
//    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + "("
                    + NAME + " TEXT, "
                    + ROLL + " TEXT , "
                    + MOBILE_NO + " TEXT, "
                    + AADHAR_NO + " TEXT, "
                    + AMOUNT + " TEXT, "
                    + PAYMENT_MOOD + " TEXT,"
                    + GENDER + " TEXT " + " )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addRecord(String name,String roll,String mobile,String aadhar,String amount,String payment_mode,String gender){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME,name); values.put(ROLL,roll); values.put(MOBILE_NO,mobile); values.put(AADHAR_NO,aadhar);
        values.put(AMOUNT,amount); values.put(PAYMENT_MOOD,payment_mode); values.put(GENDER,gender);
        db.insert(TABLE_NAME,null,values);
    }

    public ArrayList<DataModel> fetchData(){
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<DataModel> data = new ArrayList<DataModel>();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME ,null);

        while (cursor.moveToNext()){
            DataModel dataModel = new DataModel();
            dataModel.setName(cursor.getString(0));
            dataModel.setRoll(cursor.getString(1));
            dataModel.setMobile(cursor.getString(2));
            dataModel.setAadhar(cursor.getString(3));
            dataModel.setAmount(cursor.getString(4));
            dataModel.setPaymentMode(cursor.getString(5));
            dataModel.setGender(cursor.getString(6));
            data.add(dataModel);
        }
        if(!DataBase.getAllData().isEmpty()){
            DataBase.getAllData().removeAll(DataBase.getAllData());
        }
        return data;
    }

    public void updateRecord(String name,String roll,String mobile,String aadhar,String amount,String payment_mode,String gender){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values  = new ContentValues();
        values.put(NAME,name);values.put(MOBILE_NO,mobile); values.put(AADHAR_NO,aadhar);
        values.put(AMOUNT,amount); values.put(PAYMENT_MOOD,payment_mode); values.put(GENDER,gender);
        db.update(TABLE_NAME,values,  ROLL + " = " + roll,null);
        if(!DataBase.getAllData().isEmpty()){
            DataBase.getAllData().removeAll(DataBase.getAllData());
        }
    }

    public void deleteRecord(String roll){
        SQLiteDatabase db = this.getWritableDatabase();
        if(!DataBase.getAllData().isEmpty()){
            DataBase.getAllData().removeAll(DataBase.getAllData());
        }
        db.delete(TABLE_NAME,ROLL + " = ? ",new String[]{roll});
    }
}
