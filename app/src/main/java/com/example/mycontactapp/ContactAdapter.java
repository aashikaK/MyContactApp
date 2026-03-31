package com.example.mycontactapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {

    private ArrayList<Contact> contactList;

    // 🔹 listener variable
    private OnItemClickListener listener;

    // 🔹 constructor (UPDATED)
    public ContactAdapter(ArrayList<Contact> contactList, OnItemClickListener listener) {
        this.contactList = contactList;
        this.listener = listener;
    }

    // 🔹 INTERFACE goes HERE (INSIDE CLASS, OUTSIDE METHODS)
    public interface OnItemClickListener {
        void onClick(Contact contact);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvName, tvPhone;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tvName);
            tvPhone = itemView.findViewById(R.id.tvPhone);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.contact_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Contact contact = contactList.get(position);

        holder.tvName.setText(contact.getName());
        holder.tvPhone.setText(contact.getPhone());


        holder.itemView.setOnClickListener(v -> {
            listener.onClick(contact);
        });
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }
}