package com.atharvaj77.drugchain;


import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Service
public class DrugchainService {

    @Autowired
    private DrugchainRepository drugchainRepository;

    public void deletePendingRequest(String requestId){
        drugchainRepository.deleteRequestDetails(requestId);
    }

    public Map<String,Object> getPendingRequestData(String requestId) throws ExecutionException, InterruptedException {
        return drugchainRepository.getRequestDetails(requestId);
    }

    public List<Map<String, Object>> getPendingRequests(String userId) throws ExecutionException, InterruptedException {
        return drugchainRepository.getAllRequests(userId);
    }

    public String createPendingRequest(Map<String,Object> requestData){
        return drugchainRepository.creatependingRequest(requestData);
    }

    public void updatePendingRequest(String requestId ,Map<String,Object> requestData) throws ExecutionException, InterruptedException {
        drugchainRepository.updateRequestDetails(requestId,requestData);
    }

    public User getUser(String userId){
        //TODO: Implement the logic to fetch user from the database
        return new User();
    }







}
