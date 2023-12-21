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
    private RadioButton maleRadio,femaleRadio,onlineRadio,cashRadio,dueRadio;
    private Button saveBtn;

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

            nameEdt.setText(forUpdate.getStringExtra("name"));
            rollEdt.setText(forUpdate.getStringExtra("roll"));
            mobileEdt.setText(forUpdate.getStringExtra("mobile"));
            aadharEdt.setText(forUpdate.getStringExtra("aadhar"));
            amountEdt.setText(forUpdate.getStringExtra("amount"));


            if(forUpdate.getStringExtra("gender").equalsIgnoreCase("MALE")){
                maleRadio.setChecked(true);
            }else{
                femaleRadio.setChecked(true);
            }

          if(forUpdate.getStringExtra("payment_mode").equalsIgnoreCase("ONLINE")){

            }else if(forUpdate.getStringExtra("payment_mode").equalsIgnoreCase("CASH")){
                cashRadio.setChecked(true);
            }else{
                dueRadio.setChecked(true);
            }

        }

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(forUpdate.getFlags()==0){
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
        amountEdt = findViewById(R.id.amount_edt_txt);
        maleRadio = findViewById(R.id.male_radio_btn);
        femaleRadio = findViewById(R.id.female_radio_btn);
        onlineRadio = findViewById(R.id.online_radio_btn);
        cashRadio = findViewById(R.id.cash_radio_btn);
        dueRadio = findViewById(R.id.due_radio_btn);
        saveBtn = findViewById(R.id.save_btn);

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