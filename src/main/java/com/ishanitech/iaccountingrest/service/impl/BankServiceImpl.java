package com.ishanitech.iaccountingrest.service.impl;


import com.ishanitech.iaccountingrest.dao.*;
import com.ishanitech.iaccountingrest.dto.*;
import com.ishanitech.iaccountingrest.exception.CustomSqlException;
import com.ishanitech.iaccountingrest.service.BankService;
import com.ishanitech.iaccountingrest.service.DbService;
import lombok.extern.slf4j.Slf4j;
import org.jdbi.v3.core.JdbiException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class BankServiceImpl implements BankService {
    private final DbService dbService;

    public BankServiceImpl(DbService dbService) {
        this.dbService = dbService;
    }
    @Override
    public List<BankDTO> getAllByBankId(int Id) {
        BankDAO BankDAO = dbService.getDao(BankDAO.class);
        return BankDAO.getAllByBankId(Id);
    }
    public List<BankDTO> getAllByBankName(String bankName) {
        BankDAO BankDAO = dbService.getDao(BankDAO.class);
        return BankDAO.getAllByBankName(bankName);
    }

    @Override
    public List<BankDTO> getAllByBank() {
        BankDAO BankDAO = dbService.getDao(BankDAO.class);
        return BankDAO.getAllByBank();
    }




    @Override
    public List<BankDTO> getAllByBankCompany(int companyId) {
        BankDAO BankDAO = dbService.getDao(BankDAO.class);
        return  BankDAO.getAllByBankCompany(companyId);
    }

    @Override
    public List<BankDTO> getAllByBankBranch(int companyId, int branchId) {
        BankDAO BankDAO =dbService.getDao(com.ishanitech.iaccountingrest.dao.BankDAO.class);
        return BankDAO.getALLBYBranch(companyId,branchId);
    }

    @Override
    public int addbank(BankDTO bankDTO) {
        BankDAO BankDAO = dbService.getDao(BankDAO.class);
        int bankId =0;

            try{
                bankId = BankDAO.addBank(bankDTO);
            } catch(JdbiException jdbiException){
                log.info("error occured while adding user" + jdbiException.getMessage());
                throw new CustomSqlException(" something went wrong !!! ");
            }
            return bankId;
        }

    @Override
    public int updateBank(BankDTO bankDTO) {

            BankDAO BankDAO = dbService.getDao(BankDAO.class);
                return BankDAO.editbank(bankDTO, bankDTO.getBankId());
        }

    @Override
    public void deleteBankByCompanyAndBranchId( int companyId, int branchId) {
       BankDAO bankDAO = dbService.getDao(BankDAO.class);
       bankDAO.deleteBank(companyId,branchId);
    }

    @Override
    public int DeleteFromBankByAccountNo(Long accountNo) {
        try{
            BankDAO bankDAO = dbService.getDao(BankDAO.class);
            bankDAO.deleteFromBankByAccountNo(accountNo);
        }catch (JdbiException jdbiException){
            log.error(jdbiException.getMessage());
        }
        return 1;
    }


    @Override
    public List<BankListDTO> getAllBankList() {
        BankDAO BankDAO = dbService.getDao(BankDAO.class);
        return BankDAO.getAllBankList();
    }

    @Override
    public List<TypePaymentDTO> getAllPayment() {
        BankDAO BankDAO = dbService.getDao(BankDAO.class);
        return BankDAO.getAllPayment();

    }
    @Override
    public List<AccountTypeDTO> getAccountType() {
        BankDAO BankDAO = dbService.getDao(BankDAO.class);
        return BankDAO.getAccountType();

    }


}




