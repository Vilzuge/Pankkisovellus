package com.example.pankkisovellus;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    private ArrayList<Account> account_array;
    private Context mContext;

    public RecyclerViewAdapter(Context context, ArrayList<Account> accounts) {
        account_array = accounts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.accountName.setText(account_array.get(position).getAccountName());
        holder.accountBalance.setText(Float.toString(account_array.get(position).getAccountBalance()));
    }

    @Override
    public int getItemCount() {
        return account_array.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView accountName, accountBalance;
        RelativeLayout parentLayout;
        public ViewHolder(View itemView) {
            super(itemView);
            accountName = itemView.findViewById(R.id.textAccName);
            accountBalance = itemView.findViewById(R.id.textAccBalance);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }

}
