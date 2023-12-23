package com.chandan.triplist;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;

import androidx.annotation.NonNull;

public class ListRecyclerViewAdapter extends RecyclerViewAdapter{
    private Context context;
    public ListRecyclerViewAdapter(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return DataBase.getAllData().size();
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        holder.setProfile(DataBase.getAllData().get(holder.getPosition()).getProfile());
        holder.setName(DataBase.getAllData().get(holder.getPosition()).getName());
        holder.setRoll(DataBase.getAllData().get(holder.getAdapterPosition()).getRoll());
        holder.setMobile(DataBase.getAllData().get(holder.getPosition()).getMobile());
        holder.setAadhar(DataBase.getAllData().get(holder.getPosition()).getAadhar());
        holder.setAmount(DataBase.getAllData().get(holder.getPosition()).getAmount());
        holder.setRootCardColor(DataBase.getAllData().get(holder.getPosition()).getPaymentColor());
        super.setAnimation(holder.itemView, holder.getAdapterPosition());


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
                                DataBase.getAllData().remove(holder.getAdapterPosition());
                                notifyItemRemoved(holder.getAdapterPosition());
                                if(BoysFrag.getAdapter()!=null)
                                    BoysFrag.getAdapter().notifyDataSetChanged();
                                if(GirlsFrag.getAdapter()!=null)
                                    GirlsFrag.getAdapter().notifyDataSetChanged();
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
    }
}
