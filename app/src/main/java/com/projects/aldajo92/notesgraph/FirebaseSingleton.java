package com.projects.aldajo92.notesgraph;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseSingleton {

    private static FirebaseDatabase firebaseDatabase;

    static FirebaseDatabase getInstance(){
        if(firebaseDatabase == null){
            firebaseDatabase = FirebaseDatabase.getInstance();
            firebaseDatabase.setPersistenceEnabled(true);
        }

        return firebaseDatabase;
    }
}
