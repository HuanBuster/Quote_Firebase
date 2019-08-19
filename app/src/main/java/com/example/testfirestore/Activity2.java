package com.example.testfirestore;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Text;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Activity2 extends MainActivity {
    private CollectionReference mUserRef = FirebaseFirestore.getInstance().collection("Contact");

    //public static final String NAME_KEY = "name";
    //public static final String SIP_KEY = "sip";
    //String key;
    TextView mInfo ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.run_test);
        //mInfo = (TextView) findViewById(R.id.textViewFetch);
    }

    public void addContact(View view){
        // TODO: Fill this out
        EditText nameView = (EditText) findViewById(R.id.editTextName);
        EditText sipView = (EditText) findViewById(R.id.editTextSip);
        String nameText = nameView.getText().toString();
        String sipText = sipView.getText().toString();

//        String name = nameText + NAME_KEY;
//        String sip = nameText + SIP_KEY;

        if(nameText.isEmpty() || sipText.isEmpty()){
            return;
        }

        Contact contact = new Contact(
                nameText,
                sipText
        );

//        Map<String, Object> dataToSave = new HashMap<String, Object>();
//       dataToSave.put(name, nameText);
//        dataToSave.put(sip, sipText);
        mUserRef.add(contact).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Log.d("contact", "Document has been saved");
                Toast.makeText(Activity2.this, "Contact Added", Toast.LENGTH_LONG).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e("contact", "Contact was not saved", e);
                Toast.makeText(Activity2.this, e.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
   public void fetchContact(View view){
  /*      mUserRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
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
        });*/
  //----------------------------------------------------------
       System.out.println("Running");
       Intent runIntent = new Intent(getApplicationContext(), ContactActivity.class);
       startActivity(runIntent);
    }

}
