package com.chandan.triplist;

import android.provider.ContactsContract;

import java.util.ArrayList;

public class DataBase {
    private static ArrayList<DataModel> data;

    public static ArrayList<DataModel> getData(){
        if(data==null){
            addRecord();
        }
        return data;
    }

    private static void addRecord() {
        data = new ArrayList<DataModel>();

        data.add(new DataModel("Chandan Sharma","24","8969893457","821061985417","due","","MALE"));
        data.add(new DataModel("Ritesh","175","6299960371","514074989929","2000","CASH","MALE"));
        data.add(new DataModel("Shivam","203","7061765628","464157757733","2000","CASH","MALE"));
        data.add(new DataModel("Rocky","94","9006442494","499463367907","2000","ONLINE","MALE"));
        data.add(new DataModel("Sahil","124","9905833882","463088369578","2000","ONLINE","MALE"));
        data.add(new DataModel("Kaushal","167","9931039215","551950399143","2000","CASH","MALE"));
        data.add(new DataModel("Shivam","107","9835072859","216003715556","2000","CASH","MALE"));
        data.add(new DataModel("Ankush","60","8292839485","596581203928","2000","ONLINE","MALE"));
        data.add(new DataModel("Aryan","28","7323982413","646398708226","due","","MALE"));
        data.add(new DataModel("soni Kumari","123","9263235967","257875085089","2000","CASH","FEMALE"));
        data.add(new DataModel("Kanhaiya","219","7352409219","364666520731","2000","CASH","MALE"));
        data.add(new DataModel("Kunal","259","9504714701","339524695822","2000","CASH","MALE"));
        data.add(new DataModel("Manish","80","7294062588","985702421176","2000","CASH","MALE"));
        data.add(new DataModel("Abhitesh","106","7667617547","510401586458","2000","CASH","MALE"));
        data.add(new DataModel("Rohan","","7870067280","526016593865","1500","ONLINE","MALE"));
        data.add(new DataModel("Himanshu","51","9934632124","990787276015","1500","ONLINE","MALE"));
        data.add(new DataModel("Saurabh Kumar","72","6203311780","933876083987","2000","ONLINE","MALE"));
        data.add(new DataModel("Rohit Kumar","86","9693901754","673596404579","2000","CASH","MALE"));
        data.add(new DataModel("Aman","177","9471438907","975122972864","2000","CASH","MALE"));
        data.add(new DataModel("Ritesh","175","6299960371","514074989929","2000","CASH","MALE"));
    }

}
