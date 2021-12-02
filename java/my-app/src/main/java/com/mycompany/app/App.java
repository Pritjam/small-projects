package com.mycompany.app;

import java.io.*;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.auth.oauth2.GoogleCredentials;
/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) throws IOException{

        FileInputStream serviceAccount = new FileInputStream("src/main/my-first-project-f5101-firebase-adminsdk-c10kz-cd5d5ae1a9.json");

        FirebaseOptions options = FirebaseOptions.builder()
        .setCredentials(GoogleCredentials.fromStream(serviceAccount))
        .setDatabaseUrl("https://my-first-project-f5101-default-rtdb.firebaseio.com")
        .build();

        FirebaseApp.initializeApp(options);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference rootRef = database.getReference("/");

        rootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
              int post = dataSnapshot.getValue(Integer.class);
              System.out.println(post);
            }
          
            @Override
            public void onCancelled(DatabaseError databaseError) {
              System.out.println("The read failed: " + databaseError.getCode());
            }
          });

        System.out.println("Hello World!");
    }
}
