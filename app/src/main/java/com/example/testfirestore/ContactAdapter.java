package com.example.testfirestore;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {

    private Context mCtx;
    private List<Contact> contactList;

    public ContactAdapter(Context mCtx, List<Contact> contactList) {
        this.mCtx = mCtx;
        this.contactList = contactList;
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.adapter_view, parent, false);
        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        Contact contact = contactList.get(position);

        holder.textViewName.setText(contact.getName());
        holder.textViewSip.setText(contact.getSip());

    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    class ContactViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textViewName, textViewSip;

        public ContactViewHolder(View itemView) {
            super(itemView);

            textViewName = itemView.findViewById(R.id.textView_ConName);
            textViewSip = itemView.findViewById(R.id.textView_ConSip);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            Contact contact = contactList.get(getAdapterPosition());
            Intent intent = new Intent(mCtx, DetailActivity.class);
            intent.putExtra("contact", contact);
            mCtx.startActivity(intent);
        }
    }

}
