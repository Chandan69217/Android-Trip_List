package com.chandan.triplist;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;

public class BoyRecyclerViewAdapter extends RecyclerViewAdapter{
    private Context context;
    public BoyRecyclerViewAdapter(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return DataBase.getBoysData().size();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            super.onBindViewHolder(holder, position);
            holder.setProfile(DataBase.getBoysData().get(holder.getPosition()).getProfile());
            holder.setName(DataBase.getBoysData().get(holder.getPosition()).getName());
            holder.setRoll(DataBase.getBoysData().get(holder.getAdapterPosition()).getRoll());
            holder.setMobile(DataBase.getBoysData().get(holder.getPosition()).getMobile());
            holder.setAadhar(DataBase.getBoysData().get(holder.getPosition()).getAadhar());
            holder.setAmount(DataBase.getBoysData().get(holder.getPosition()).getAmount());
            holder.setRootCardColor(DataBase.getBoysData().get(holder.getPosition()).getPaymentColor());
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
                                DataBase dataBase = new DataBase(context);
                                dataBase.deleteRecord(DataBase.getAllData().get(holder.getAdapterPosition()).getRoll());
                                DataBase.getAllData().addAll(dataBase.fetchData());
                                notifyItemRemoved(holder.getAdapterPosition());
                                if(ListFrag.getAdapter()!=null)
                                    ListFrag.getAdapter().notifyDataSetChanged();
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
