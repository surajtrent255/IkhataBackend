package com.ishanitech.iaccountingrest.service.impl;

import com.ishanitech.iaccountingrest.dao.BankWithdrawDAO;
import com.ishanitech.iaccountingrest.dto.BankWithdrawDTO;
import com.ishanitech.iaccountingrest.service.BankWithdrawService;
import com.ishanitech.iaccountingrest.service.DbService;
import lombok.extern.slf4j.Slf4j;
import org.jdbi.v3.core.JdbiException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Slf4j
public class BankWithdrawServiceImpl implements BankWithdrawService {
    private final DbService dbService;

    public BankWithdrawServiceImpl(DbService dbService) {
        this.dbService = dbService;
    }

    @Override
    public List<BankWithdrawDTO> getAllWithdraw(int companyId, int branchId) {
        BankWithdrawDAO BankWithdrawDAO = dbService.getDao(BankWithdrawDAO.class);
        return BankWithdrawDAO.getAllwithdraw(companyId,branchId);
    }



    @Override
    public int addwithdraw(BankWithdrawDTO bankWithdrawDTO) {
        BankWithdrawDAO BankWithdrawDAO = dbService.getDao(BankWithdrawDAO.class);

        try{
            int bankId =0;
            bankId = BankWithdrawDAO.addwithdraw(bankWithdrawDTO);
        } catch(JdbiException jdbiException){

            System.out.println("error occured while adding user");
        }
        return 1;
    }
}
