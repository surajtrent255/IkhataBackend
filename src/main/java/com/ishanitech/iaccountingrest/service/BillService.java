package com.ishanitech.iaccountingrest.service;

import com.ishanitech.iaccountingrest.dto.BillDTO;

import java.util.List;

public interface BillService {

        List<BillDTO> getAllBills();

        BillDTO getBillById(int id);

        Integer addNewBill(BillDTO billDTO);

        void updateBill(BillDTO billDTO, int id);

        void deleteBillById(int id);


    }