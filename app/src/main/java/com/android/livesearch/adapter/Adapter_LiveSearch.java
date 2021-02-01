package com.android.livesearch.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.livesearch.R;
import com.android.livesearch.model.Users;

import java.util.List;

public class Adapter_LiveSearch extends RecyclerView.Adapter<Adapter_LiveSearch.MyViewHolder> {

    private List<Users> listUsers;
    private Context context;

    public Adapter_LiveSearch(List<Users> listUsers, Context context) {
        this.listUsers = listUsers;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.txtName.setText(listUsers.get(position).getName());
        holder.txtEmail.setText(listUsers.get(position).getEmail());
        holder.txtPhone.setText(listUsers.get(position).getPhone());
    }

    @Override
    public int getItemCount() {
        return listUsers.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtName, txtEmail, txtPhone;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = (TextView) itemView.findViewById(R.id.txtName);
            txtEmail = (TextView) itemView.findViewById(R.id.txtEmail);
            txtPhone = (TextView) itemView.findViewById(R.id.txtPhone);
        }
    }
}
