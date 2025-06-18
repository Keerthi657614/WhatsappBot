// package com.example.whatsapp.service;

// import com.google.api.core.ApiFuture;
// import com.google.cloud.firestore.DocumentReference;
// import com.google.cloud.firestore.Firestore;
// import com.google.firebase.cloud.FirestoreClient;
// import org.springframework.stereotype.Service;

// import java.util.Map;

// @Service
// public class FirebaseService {

//     public String saveData(String collection, Map<String, Object> data) throws Exception {
//         Firestore db = FirestoreClient.getFirestore();
//         ApiFuture<DocumentReference> future = db.collection(collection).add(data);
//         DocumentReference ref = future.get();
//         return ref.getId();
//     }
// }
package com.example.whatsapp.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class FirebaseService {

    public String saveData(String collection, Map<String, Object> data) throws Exception {
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<DocumentReference> future = db.collection(collection).add(data);
        DocumentReference ref = future.get();
        return ref.getId();
    }
}
