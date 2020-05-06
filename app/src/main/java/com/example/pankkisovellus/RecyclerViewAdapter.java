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

//Adapter for the recyclerview that shows user all of their accounts in the dashboard
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    private ArrayList<Account> account_array;
    private OnAccountListener mOnAccountListener;
    private Context mContext;

    public RecyclerViewAdapter(Context context, ArrayList<Account> accounts, OnAccountListener onAccountListener) {
        account_array = accounts;
        this.mOnAccountListener = onAccountListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem, parent, false);
        ViewHolder holder = new ViewHolder(view, mOnAccountListener);
        return holder;
    }


    //Reading accounts from the account_array list
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.accountName.setText(account_array.get(position).getAccountName());
        holder.accountBalance.setText(Float.toString(account_array.get(position).getAccountBalance()) + "€");
    }

    @Override
    public int getItemCount() {
        return account_array.size();
    }


    //Assigning the account_array values to their own layout_listitem slots
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView accountName, accountBalance;
        RelativeLayout parentLayout;
        OnAccountListener onAccountListener;
        public ViewHolder(View itemView, OnAccountListener onAccountListener) {
            super(itemView);
            accountName = itemView.findViewById(R.id.textEvent);
            accountBalance = itemView.findViewById(R.id.textAmount);
            parentLayout = itemView.findViewById(R.id.parent_layout);
            this.onAccountListener = onAccountListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onAccountListener.onAccountClick(getAdapterPosition());
        }
    }

    public interface OnAccountListener {
        void onAccountClick(int position);
    }

}
