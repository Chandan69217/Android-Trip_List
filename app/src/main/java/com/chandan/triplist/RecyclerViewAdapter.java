package com.chandan.triplist;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private static Context context;
    private int lastPosition=-1;


    public RecyclerViewAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewHolder viewHolder = new ViewHolder(LayoutInflater.from(context).inflate(R.layout.details_card,parent,false));
        return viewHolder;
    }



    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        // start code for update
        holder.getEditTxt().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent updateActivity = new Intent(context,SecondAcitivity.class);
                updateActivity.addFlags(1);
                updateActivity.putExtra("position",holder.getAdapterPosition());
                context.startActivity(updateActivity);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView profile;
        private TextView name;
        private TextView roll;
        private TextView mobile;
        private TextView aadhar;
        private TextView amount;
        private CardView rootCardColor;
        private TextView EditTxt;

        public TextView getEditTxt() {
            return EditTxt;
        }

        public CardView getRootCardColor() {
            return rootCardColor;
        }

        public void setProfile(int profile) {
            this.profile.setImageResource(profile);
        }

        public void setName(String name) {
            this.name.setText(name);
        }

        public void setRoll(String roll) {
            this.roll.setText(roll);
        }

        public void setMobile(String mobile) {
            this.mobile.setText(mobile);
        }

        public void setAadhar(String aadhar) {
            this.aadhar.setText(aadhar);
        }

        public void setAmount(String amount) {
            this.amount.setText(amount);
        }

        @SuppressLint("ResourceAsColor")
        public void setRootCardColor(int rootCardColor) {
            this.rootCardColor.setCardBackgroundColor(rootCardColor);
        }

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.profile = itemView.findViewById(R.id.profile_img);
            this.name = itemView.findViewById(R.id.name_txt);
            this.roll = itemView.findViewById(R.id.roll_txt);
            this.mobile = itemView.findViewById(R.id.mobile_txt);
            this.aadhar = itemView.findViewById(R.id.aadhar_txt);
            this.amount = itemView.findViewById(R.id.amount_txt);
            this.EditTxt = itemView.findViewById(R.id.card_edit_txt);
            this.rootCardColor = itemView.findViewById(R.id.root_card_details);
        }
    }

    public void setAnimation(View view,int position){
        if(position>lastPosition) {
            Animation custom_animation = AnimationUtils.loadAnimation(context, R.anim.card_anim);
            view.setAnimation(custom_animation);
            lastPosition=position;
        }
    }
}
