package com.chandan.triplist;

import android.graphics.Color;
import android.os.Build;

public class DataModel {
private String Name;
private String Roll;
private String Mobile;
private String Aadhar;
private String Amount ;
private String PaymentMode;
private String Gender;

    public DataModel(String name, String roll, String mobile, String aadhar, String amount, String paymentMode, String gender) {
        this.Name = name;
        this.Roll = roll;
        this.Mobile = mobile;
        this.Aadhar = aadhar;
        this.Amount = amount;
        this.PaymentMode = paymentMode;
        this.Gender = gender;
    }

    public DataModel(){}
    public int getProfile(){
    if(Gender.equalsIgnoreCase("MALE")){
        return R.drawable.boy;
    }else if (Gender.equalsIgnoreCase("FEMALE")){
        return R.drawable.girl;
    }else{
        return R.drawable.man;
    }
}

public void setGender(String Gender){
    this.Gender = Gender;
}
public String getGender(){ return this.Gender;}
    public String getName() {
        return this.Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getRoll() {
        return this.Roll;
    }

    public void setRoll(String roll) {
        this.Roll = roll;
    }

    public String getMobile() {
        return this.Mobile;
    }

    public void setMobile(String mobile) {
        this.Mobile = mobile;
    }

    public String getAadhar() {
        return this.Aadhar;
    }

    public void setAadhar(String aadhar) {
        this.Aadhar = aadhar;
    }

    public String getAmount() {
        return this.Amount;
    }

    public void setAmount(String amount) {
        this.Amount = amount;
    }

    public int  getPaymentColor() {
        if(this.PaymentMode.equalsIgnoreCase("ONLINE")){
            return Color.rgb(106,181,106);
        }else if (this.PaymentMode.equalsIgnoreCase("CASH")){
                return Color.rgb(229,229,129);

        }else{
                return Color.rgb(255,255,255);
        }
    }

    public void setPaymentMode(String paymentMode) {
        PaymentMode = paymentMode;
    }
    public String getPaymentMode(){return this.PaymentMode;}

}
