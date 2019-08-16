package com.example.testfirestore;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class Activity2 extends MainActivity {
    private DocumentReference mUserRef = FirebaseFirestore.getInstance().document("Contact/user1");

    public static final String NAME_KEY = "name";
    public static final String SIP_KEY = "sip";
    //String key;
    TextView mInfo ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.run_test);
        mInfo = (TextView) findViewById(R.id.textViewFetch);
    }

    public void addContact(View view){
        // TODO: Fill this out
        EditText nameView = (EditText) findViewById(R.id.editTextName);
        EditText sipView = (EditText) findViewById(R.id.editTextSip);
        String nameText = nameView.getText().toString();
        String sipText = sipView.getText().toString();

        String name = nameText + NAME_KEY;
        String sip = nameText + SIP_KEY;

        if(nameText.isEmpty() || sipText.isEmpty()){
            return;
        }
        Map<String, Object> dataToSave = new HashMap<String, Object>();
        dataToSave.put(name, nameText);
        dataToSave.put(sip, sipText);
        mUserRef.update(dataToSave).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.d("contact", "Contact has been saved");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e("contact", "Contact was not saved", e);
            }
        });
    }
    public void fetchContact(View view){
        mUserRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d("contact", "DocumentSnapshot data: " + document.getData().toString());
                        String text = document.toString();
                        //mInfo.setText(text);
                        System.out.println(document);
                    } else {
                        Log.d("contact", "No such document");
                    }
                }else {
                     Log.d("contact", "get failed with", task.getException());
                    }
                }
        });
    }
}
