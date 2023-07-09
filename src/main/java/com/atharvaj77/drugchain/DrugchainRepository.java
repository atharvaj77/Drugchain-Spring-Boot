package com.atharvaj77.drugchain;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Repository
public class DrugchainRepository {

    public Map<String, Object> getRequestDetails(String reqId) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection("pendingRequests").document(reqId);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot documentSnapshot = future.get();

        PendingRequest pendingRequest;

        return documentSnapshot.getData();
    }

    public User getUserDetails(String id) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection("users").document(id);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot documentSnapshot = future.get();

        User user;

        if(documentSnapshot.exists()){
            user = documentSnapshot.toObject(User.class);
            return user;
        }

        else return null;
    }

    public String updateRequestDetails(String requestId,Map<String,Object> pendingRequest) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection("pendingRequests").document(requestId).set(pendingRequest);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public void deleteRequestDetails(String reqId){
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> writeResult = dbFirestore.collection("pendingRequests").document(reqId).delete();
    }

    public List<Map<String,Object>> getAllRequests(String userId) throws ExecutionException, InterruptedException {
        Firestore firestore = FirestoreClient.getFirestore();
        CollectionReference pendingRequestsCollection = firestore.collection("pendingRequests");

        Query query = pendingRequestsCollection.whereEqualTo("to_id", userId);
        ApiFuture<QuerySnapshot> querySnapshot = query.get();

        List<Map<String, Object>> pendingReqArr = new ArrayList<>();

        for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {
            Map<String, Object> reqData = document.getData();
            pendingReqArr.add(reqData);
        }

        return pendingReqArr;
    }

    public String creatependingRequest(Map<String,Object> requestData){

    }





}
