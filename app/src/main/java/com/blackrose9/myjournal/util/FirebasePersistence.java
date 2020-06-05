package com.blackrose9.myjournal.util;

import com.google.firebase.database.FirebaseDatabase;

public class FirebasePersistence {
    private static FirebaseDatabase firebaseDatabase;

    public static FirebaseDatabase getFirebaseDatabase() {
        if (firebaseDatabase == null) {
            firebaseDatabase = FirebaseDatabase.getInstance();
            firebaseDatabase.setPersistenceEnabled(true);
        }
        return firebaseDatabase;
    }
}
