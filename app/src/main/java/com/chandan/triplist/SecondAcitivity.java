package com.chandan.triplist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.ActionMenuItem;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
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
    private String paymentMode=null,gender=null;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_acitivity);
        findID();
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Add Student");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // For update data will set here !!!
        forUpdate = getIntent();
        if(forUpdate.getFlags()== 1){
            getSupportActionBar().setTitle("Update Details");
            index = forUpdate.getIntExtra("position",0);
            nameEdt.setText(DataBase.getData().get(index).getName());
            rollEdt.setText(DataBase.getData().get(index).getRoll());
            mobileEdt.setText(DataBase.getData().get(index).getMobile());
            aadharEdt.setText(DataBase.getData().get(index).getAadhar());
            amountEdt.setText(DataBase.getData().get(index).getAmount());
            gender = DataBase.getData().get(index).getGender();
            paymentMode = DataBase.getData().get(index).getPaymentMode();


            if(DataBase.getData().get(index).getGender().equalsIgnoreCase("MALE")){
                genderGroup.check(R.id.male_radio_btn);
            }else if (DataBase.getData().get(index).getGender().equalsIgnoreCase("FEMALE")){
                genderGroup.check(R.id.female_radio_btn);
            }

            if(DataBase.getData().get(index).getPaymentMode().equalsIgnoreCase("ONLINE")){
                paymentGroup.check(R.id.online_radio_btn);
            }else if(DataBase.getData().get(index).getPaymentMode().equalsIgnoreCase("CASH")){
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
            @Override
            public void onClick(View view) {
                if(forUpdate.getFlags()==0){

                    String name,roll,mobile,aadhar,amount;

                    name = nameEdt.getText().toString();
                    roll = rollEdt.getText().toString();
                    mobile = mobileEdt.getText().toString();
                    aadhar = aadharEdt.getText().toString();
                    amount = amountEdt.getText().toString();

                    DataBase.getData().add(new DataModel(name,roll,mobile,aadhar,amount,paymentMode,gender));
                    ListFrag.getAdapter().notifyItemInserted(index);
                    ListFrag.getRecyclerView().scrollToPosition(index);
                    finish();

                }else if(forUpdate.getFlags()==1){

                        // code here for update
                    String name,roll,mobile,aadhar,amount;

                    name = nameEdt.getText().toString();
                    roll = rollEdt.getText().toString();
                    mobile = mobileEdt.getText().toString();
                    aadhar = aadharEdt.getText().toString();
                    amount = amountEdt.getText().toString();

                    DataBase.getData().set(index,new DataModel(name,roll,mobile,aadhar,amount,paymentMode,gender));
                    ListFrag.getAdapter().notifyDataSetChanged();
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