package com.atharvaj77.drugchain;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DrugchainService {

    @Autowired
    private DrugchainRepository drugchainRepository;

    public void getAllManufacturers() {
    }
}
