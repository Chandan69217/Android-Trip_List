package com.chandan.triplist;

import static androidx.core.content.ContextCompat.startActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
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

import java.util.ArrayList;

public class ListRecyclerAdapter extends RecyclerView.Adapter<ListRecyclerAdapter.ViewHolder> {

    private ArrayList<DataModel> data;
    private Context context;
    private int lastPosition=-1;
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewHolder viewHolder = new ViewHolder(LayoutInflater.from(context).inflate(R.layout.details_card,parent,false));
        return viewHolder;
    }

    public ListRecyclerAdapter(Context context, ArrayList<DataModel> data) {
        this.data = data;
        this.context = context;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.setProfile(data.get(holder.getPosition()).getProfile());
        holder.setName(data.get(holder.getPosition()).getName());
        holder.setRoll(data.get(holder.getAdapterPosition()).getRoll());
        holder.setMobile(data.get(holder.getPosition()).getMobile());
        holder.setAadhar(data.get(holder.getPosition()).getAadhar());
        Log.e("amount",data.get(holder.getAdapterPosition()).getAmount());
        holder.setAmount(data.get(holder.getPosition()).getAmount());
        holder.setRootCardColor(data.get(holder.getPosition()).getPaymentColor());
        setAnimation(holder.itemView,holder.getAdapterPosition());

        // start code for delete
        holder.getRootCardColor().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog.Builder deleteDialog = new AlertDialog.Builder(context);
                deleteDialog.setMessage("Do you want to delete")
                        .setTitle("Delete")
                        .setCancelable(true)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                data.remove(holder.getAdapterPosition());
                                notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        })
                        .setIcon(R.drawable.delete)
                        .show();


                return true;
            }
        });

        // start code for update
        holder.getEditTxt().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent updateActivity = new Intent(context,SecondAcitivity.class);
                updateActivity.addFlags(1);
                updateActivity.putExtra("name",data.get(holder.getAdapterPosition()).getName());
                updateActivity.putExtra("roll",data.get(holder.getAdapterPosition()).getRoll());
                updateActivity.putExtra("mobile",data.get(holder.getAdapterPosition()).getMobile());
                updateActivity.putExtra("aadhar",data.get(holder.getAdapterPosition()).getAadhar());
                updateActivity.putExtra("amount",data.get(holder.getAdapterPosition()).getAmount());
                updateActivity.putExtra("gender",data.get(holder.getAdapterPosition()).getGender());
                updateActivity.putExtra("payment_mode",data.get(holder.getAdapterPosition()).getPaymentMode());
                updateActivity.putExtra("position",holder.getAdapterPosition());
                context.startActivity(updateActivity);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public void setData(ArrayList<DataModel> data) {
        this.data = data;
    }

    public void setContext(Context context) {
        this.context = context;
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

    private void setAnimation(View view,int position){
        if(position>lastPosition) {
            Animation custom_animation = AnimationUtils.loadAnimation(context, R.anim.card_anim);
            view.setAnimation(custom_animation);
            lastPosition=position;
        }
    }
}