package com.ishanitech.iaccountingrest.service.impl;

import com.ishanitech.iaccountingrest.dao.BankDAO;
import com.ishanitech.iaccountingrest.dao.BankWithdrawDAO;
import com.ishanitech.iaccountingrest.dto.BankDTO;
import com.ishanitech.iaccountingrest.dto.BankListDTO;
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
        BankDAO bankDAO = dbService.getDao(BankDAO.class);
        List<BankWithdrawDTO> bWithdrawDTOs =  BankWithdrawDAO.getAllwithdraw(companyId,branchId);
        bWithdrawDTOs.forEach((bw)->{
            BankDTO b = bankDAO.getAllByBankId(bw.getBankId()).get(0);
            BankListDTO bankOne = bankDAO.getSingleBankById(b.getBankTypeId());
            bw.setBankName(bankOne.getName());
        });
        return bWithdrawDTOs;
    }



    @Override
    public int addwithdraw(BankWithdrawDTO bankWithdrawDTO) {
        BankWithdrawDAO BankWithdrawDAO = dbService.getDao(BankWithdrawDAO.class);

        try{

             BankWithdrawDAO.addwithdraw(bankWithdrawDTO);
            return 1;
        } catch(JdbiException jdbiException){

            log.error("error occured while withdraw" +jdbiException.getMessage());
            return 0;
        }

    }

    @Override
    public int updatewithdraw(BankWithdrawDTO BankWithdrawDTO) {
        BankWithdrawDAO BankWithdrawDAO = dbService.getDao(BankWithdrawDAO.class);

        try{
            BankWithdrawDAO.updatewithdraw(BankWithdrawDTO, BankWithdrawDTO.getWithdrawId());
            log.info("service imp in update withdraw"+ BankWithdrawDTO );
            return 1;
        }
        catch (JdbiException jdbiException){

            log.error("error in update withdraw"+jdbiException.getMessage() );
            return 0;
        }

    }

    @Override
    public int deletewithdraw(int branchId, int withdrawId) {
        try{
            BankWithdrawDAO BankWithdrawDAO = dbService.getDao(BankWithdrawDAO.class);
            BankWithdrawDAO.deletewithdraw(branchId,withdrawId);
            log.info("info in deletewithdraw"+branchId + withdrawId);
            return withdrawId;

        }catch (JdbiException jdbiException){
            log.error(jdbiException.getMessage());
            return 0;

        }
    }

    @Override
    public List<BankWithdrawDTO> getLimitedBanksWithdrawByCompIdAndBranchId(Integer offset, Integer pageTotalItems, Integer compId, Integer branchId) {
        List<BankWithdrawDTO> withdraws;
        withdraws = dbService.getDao(BankWithdrawDAO.class).getLimitedWithDrawsByCompanyAndBranchId(offset, pageTotalItems, compId, branchId);
        return withdraws;
    }

    @Override
    public BankWithdrawDTO getSingleBankWithdraw(Integer id, Integer companyId, Integer branchId) {
        BankWithdrawDTO singleWithDraw;
        singleWithDraw = dbService.getDao(BankWithdrawDAO.class).getSingleWithDraw(id, companyId, branchId);
        return singleWithDraw;
    }


}
