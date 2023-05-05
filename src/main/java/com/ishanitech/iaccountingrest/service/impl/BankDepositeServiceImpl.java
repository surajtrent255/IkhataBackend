package com.ishanitech.iaccountingrest.service.impl;

import com.ishanitech.iaccountingrest.dao.BankDepositeServiceDAO;
import com.ishanitech.iaccountingrest.dto.BankDepositDTO;
import com.ishanitech.iaccountingrest.exception.CustomSqlException;
import com.ishanitech.iaccountingrest.service.BankDepositeService;
import com.ishanitech.iaccountingrest.service.DbService;
import lombok.extern.slf4j.Slf4j;
import org.jdbi.v3.core.JdbiException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Slf4j
public class BankDepositeServiceImpl implements BankDepositeService {
    private final  DbService dbService;

    public BankDepositeServiceImpl(DbService dbService) {
        this.dbService = dbService;
    }


    @Override
    public List<BankDepositDTO> getAllByBankBankDeposite(int companyId, int branchId) {
        BankDepositeServiceDAO BankDepositeServiceDAO =dbService.getDao(BankDepositeServiceDAO.class);
        return
         BankDepositeServiceDAO.getALLBankDeposite(companyId,branchId);
        }

    @Override
    public int addBankDeposit(BankDepositDTO BankDepositDTO) {
        BankDepositeServiceDAO BankDepositeServiceDAO = dbService.getDao(BankDepositeServiceDAO.class);

            try{
                 BankDepositeServiceDAO.addBankDeposit( BankDepositDTO);
                    return  1;

            } catch(JdbiException jdbiException){
                log.error("error occured while fetching products : " + jdbiException.getMessage());
                throw new CustomSqlException("Error occured while adding bank");



            }

        }

    @Override
    public int updateDeposite(BankDepositDTO bankDepositDTO) {
        BankDepositeServiceDAO bankDepositeServiceDAO = dbService.getDao(BankDepositeServiceDAO.class);
        try{
            bankDepositeServiceDAO.updateDeposite(bankDepositDTO, bankDepositDTO.getDepositId());
            return 1;
        }
        catch (JdbiException jdbiException){

            System.out.println("error in update Deposite"+jdbiException.getMessage() );
            return 0;
        }
    }

    @Override
    public int deleteFromBankDepositeBranchId( int branchId , int depositId) {


        try{
            BankDepositeServiceDAO bankDepositeServiceDAO = dbService.getDao(BankDepositeServiceDAO.class);
            bankDepositeServiceDAO.deleteDeposite(branchId,depositId );
            return 1;
        }catch (JdbiException jdbiException){
            log.error(jdbiException.getMessage());
            return 0;
        }

    }

}


