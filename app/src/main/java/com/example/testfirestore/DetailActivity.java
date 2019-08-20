package com.example.testfirestore;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText editTextName;
    private EditText editTextSip;

    private FirebaseFirestore db;

    private Contact contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        editTextName = findViewById(R.id.editText_CurName);
        editTextSip = findViewById(R.id.editText_CurSip);

        contact = (Contact) getIntent().getSerializableExtra("contact");
        db = FirebaseFirestore.getInstance();

        editTextName.setText(contact.getName());
        editTextSip.setText(contact.getSip());

        Toast.makeText(DetailActivity.this, contact.getName(), Toast.LENGTH_LONG).show();
        //System.out.println(editTextSip.toString());


        findViewById(R.id.button_EdCon).setOnClickListener(this);
        findViewById(R.id.button_DelCon).setOnClickListener(this);
    }

    private void updateContact() {
        String name = editTextName.getText().toString().trim();
        String sip = editTextSip.getText().toString().trim();

        Contact p = new Contact(
                name, sip
        );

        db.collection("Contact").document(contact.getId())
                .set(p)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(DetailActivity.this, "Contact Updated", Toast.LENGTH_LONG).show();
                    }
                });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_EdCon:
                updateContact();
                break;
        }
    }
}
