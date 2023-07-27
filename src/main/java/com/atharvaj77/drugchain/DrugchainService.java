package com.atharvaj77.drugchain;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Service
public class DrugchainService {
    DrugchainRepository drugchainRepository;


    @Autowired
    DrugchainService(DrugchainRepository drugchainRepository){
        this.drugchainRepository = drugchainRepository;
    }

    public void deletePendingRequest(String requestId){
        drugchainRepository.deleteRequestDetails(requestId);
    }

    public Map<String,Object> getPendingRequestData(String requestId) throws ExecutionException, InterruptedException {
        return drugchainRepository.getRequestDetails(requestId);
    }

    public List<Map<String, Object>> getPendingRequests(String userId) throws ExecutionException, InterruptedException {
        return drugchainRepository.getAllRequests(userId);
    }

    public String createPendingRequest(Map<String,Object> requestData) throws ExecutionException, InterruptedException {
        return drugchainRepository.createPendingRequest(requestData);
    }

    public void updatePendingRequest(String requestId ,Map<String,Object> requestData) throws ExecutionException, InterruptedException {
        drugchainRepository.updateRequestDetails(requestId,requestData);
    }

    public User getUser(String userId) throws ExecutionException, InterruptedException {
        return drugchainRepository.getUserDetails(userId);
    }


    public List<Map<String, Object>> getManufacturers() throws ExecutionException, InterruptedException {
        return drugchainRepository.getUserForType("manufacturer");
    }

    public List<Map<String, Object>> getDistributors() throws ExecutionException, InterruptedException {
        return drugchainRepository.getUserForType("distributor");
    }
}
