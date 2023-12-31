package com.chandan.triplist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class SecondAcitivity extends AppCompatActivity {

    private Toolbar toolbar;
    private Intent forUpdate;
    private EditText nameEdt,rollEdt,mobileEdt,aadharEdt,amountEdt;
    private RadioButton radioButton;
    private Button saveBtn;
    private int index;
    private RadioGroup genderGroup,paymentGroup;
    private String paymentMode="DUE",gender="MALE";
    private DataBase dataBase;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_acitivity);
        findID();
        dataBase = new DataBase(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Add Student");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        forUpdate = getIntent();

        // For update data will set here !!!
        if(forUpdate.getFlags()== 1){
            getSupportActionBar().setTitle("Update Details");
            index = forUpdate.getIntExtra("position",0);
            nameEdt.setText(DataBase.getAllData().get(index).getName());
            rollEdt.setText(DataBase.getAllData().get(index).getRoll());
            mobileEdt.setText(DataBase.getAllData().get(index).getMobile());
            aadharEdt.setText(DataBase.getAllData().get(index).getAadhar());
            amountEdt.setText(DataBase.getAllData().get(index).getAmount());
            gender = DataBase.getAllData().get(index).getGender();
            paymentMode = DataBase.getAllData().get(index).getPaymentMode();

            rollEdt.setInputType(InputType.TYPE_NULL);

            if(DataBase.getAllData().get(index).getGender().equalsIgnoreCase("MALE")){
                genderGroup.check(R.id.male_radio_btn);
            }else if (DataBase.getAllData().get(index).getGender().equalsIgnoreCase("FEMALE")){
                genderGroup.check(R.id.female_radio_btn);
            }

            if(DataBase.getAllData().get(index).getPaymentMode().equalsIgnoreCase("ONLINE")){
                paymentGroup.check(R.id.online_radio_btn);
            }else if(DataBase.getAllData().get(index).getPaymentMode().equalsIgnoreCase("CASH")){
                paymentGroup.check(R.id.cash_radio_btn);
            }else{
                paymentGroup.check(R.id.due_radio_btn);
            }

        }

        genderGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                radioButton = findViewById(i);
                if(radioButton.getText().toString().equalsIgnoreCase("MALE")){
                    gender = "MALE";
                }else if (radioButton.getText().toString().equalsIgnoreCase("FEMALE")){
                    gender = "FEMALE";
                }
            }
        });

        paymentGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                radioButton = findViewById(i);
                if(radioButton.getText().toString().equalsIgnoreCase("ONLINE")){
                    paymentMode = "ONLINE";
                }else if(radioButton.getText().toString().equalsIgnoreCase("CASH")){
                    paymentMode = "CASH";
                }else if(radioButton.getText().toString().equalsIgnoreCase("DUE")){
                    paymentMode = "DUE";
                }
            }
        });
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SuspiciousIndentation")
            @Override
            public void onClick(View view) {
                if(forUpdate.getFlags()==0){
                    // code for add new student

                    String name,roll,mobile,aadhar,amount;

                    if(!nameEdt.getText().toString().isEmpty()){
                        name = nameEdt.getText().toString();
                    }else{
                        name = "XXXXXXXXXXXXXXX";
                    }
                    if(!mobileEdt.getText().toString().isEmpty()){
                        mobile = mobileEdt.getText().toString();
                    }else{
                       mobile = "XXXXXXXXXX";
                    }
                    if(!aadharEdt.getText().toString().isEmpty()){
                        aadhar = aadharEdt.getText().toString();
                    }else{
                        aadhar = "XXXXXXXXXXXXXXXX";
                    }
                    if(!amountEdt.getText().toString().isEmpty()){
                        amount = amountEdt.getText().toString();
                    }else{
                        amount = "XXXX";
                    }

                    if(!rollEdt.getText().toString().isEmpty()){
                        roll = rollEdt.getText().toString();
                    }else{
                        roll = null;
                    }
                    if(roll!=null){
                        Log.e("all","ok");
                        dataBase.addRecord(name,roll,mobile,aadhar,amount,paymentMode,gender);
                        DataBase.getAllData().addAll(dataBase.fetchData());
                        if(ListFrag.getAdapter()!=null) {
                            ListFrag.getAdapter().notifyDataSetChanged();
                            ListFrag.getRecyclerView().scrollToPosition(DataBase.getAllData().size()-1);
                        }
                        if(BoysFrag.getAdapter()!=null) {
                            BoysFrag.getAdapter().notifyDataSetChanged();
                            BoysFrag.getRecyclerView().scrollToPosition(DataBase.getBoysData().size()-1);
                        }
                        if(GirlsFrag.getAdapter()!=null) {
                            GirlsFrag.getAdapter().notifyDataSetChanged();
                            GirlsFrag.getRecyclerView().scrollToPosition(DataBase.getGirlsData().size()-1);
                        }
                        finish();
                    }else {
                        Toast toast = Toast.makeText(getApplicationContext(),"Please enter roll no",Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.TOP,0,0);
                        toast.show();
                    }
                }else if(forUpdate.getFlags()==1){

                        // code here for update
                    String name,roll,mobile,aadhar,amount;

                    if(!nameEdt.getText().toString().isEmpty()){
                        name = nameEdt.getText().toString();
                    }else{
                        name = "XXXXXXXXXXXXXXX";
                    }
                    if(!mobileEdt.getText().toString().isEmpty()){
                        mobile = mobileEdt.getText().toString();
                    }else{
                        mobile = "XXXXXXXXXX";
                    }
                    if(!aadharEdt.getText().toString().isEmpty()){
                        aadhar = aadharEdt.getText().toString();
                    }else{
                        aadhar = "XXXXXXXXXXXXXXXX";
                    }
                    if(!amountEdt.getText().toString().isEmpty()){
                        amount = amountEdt.getText().toString();
                    }else{
                        amount = "XXXX";
                    }
                    roll = rollEdt.getText().toString();

                    dataBase.updateRecord(name,roll,mobile,aadhar,amount,paymentMode,gender);
                    DataBase.getAllData().addAll(dataBase.fetchData());
                    if(ListFrag.getAdapter()!=null)
                    ListFrag.getAdapter().notifyItemChanged(index);
                    if(BoysFrag .getAdapter()!=null)
                    BoysFrag.getAdapter().notifyItemChanged(index);
                    if(GirlsFrag.getAdapter()!=null)
                    GirlsFrag.getAdapter().notifyItemChanged(index);
                    finish();
                }
            }
        });
    }

    private void findID() {
        toolbar = findViewById(R.id.second_toolbar);
        nameEdt = findViewById(R.id.name_edt_txt);
        rollEdt = findViewById(R.id.roll_edt_txt);
        mobileEdt = findViewById(R.id.mobile_edt_txt);
        aadharEdt = findViewById(R.id.aadhar_edt_txt);
        amountEdt = findViewById(R.id.amount_edt_txt);saveBtn = findViewById(R.id.save_btn);
        genderGroup = findViewById(R.id.group_radio_gender);
        paymentGroup = findViewById(R.id.payment_group_radio);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}