package com.example.testfirestore;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ContactActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ContactAdapter adapter;
    private List<Contact> contactList;
    private ProgressBar progressBar;

    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        progressBar = findViewById(R.id.progressBar);

        contactList = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView_contact);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new ContactAdapter(this, contactList);
        recyclerView.setAdapter(adapter);

        db = FirebaseFirestore.getInstance();
        db.collection("Contact").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        progressBar.setVisibility(View.GONE);

                        if (!queryDocumentSnapshots.isEmpty()){
                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                            for (DocumentSnapshot d : list){
                                Contact p = d.toObject(Contact.class);
                                p.setId(d.getId());
                                contactList.add(p);
                            }
                            adapter.notifyDataSetChanged();
                        }
                    }
                });

    }

}
