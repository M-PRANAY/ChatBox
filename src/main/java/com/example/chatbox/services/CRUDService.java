package com.example.chatbox.services;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class CRUDService {
    public String createCRUD(CRUDSample crud) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionApiFuture = db.collection("sample").document(crud.getName()).set(crud);
        return collectionApiFuture.get().getUpdateTime().toString();
    }

    public CRUDSample getCRUD(String documentId) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference documentReference = db.collection("sample").document(documentId);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot documentSnapshot = future.get();
        CRUDSample crudSample;
        if(documentSnapshot.exists()){
            crudSample = documentSnapshot.toObject(CRUDSample.class);
            return crudSample;
        }
        return null;
    }

    public String putCRUD(CRUDSample crud){
        return "";
    }

    public String deleteCRUD(String documentId){
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> writeResultApiFuture = db.collection("sample").document(documentId).delete();
        return "Successfully deleted" + documentId;
    }
}
