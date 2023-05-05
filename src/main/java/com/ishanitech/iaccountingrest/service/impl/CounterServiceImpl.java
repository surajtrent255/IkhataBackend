package com.ishanitech.iaccountingrest.service.impl;

import com.ishanitech.iaccountingrest.dao.CounterDAO;
import com.ishanitech.iaccountingrest.dto.CounterDTO;
import com.ishanitech.iaccountingrest.dto.UserConfigurationDTO;
import com.ishanitech.iaccountingrest.dto.UserCounterDTO;
import com.ishanitech.iaccountingrest.service.CounterService;
import com.ishanitech.iaccountingrest.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CounterServiceImpl implements CounterService {
    @Autowired
    private DbService dbService;
    @Override
    public List<CounterDTO> getAllCounterDetails(int companyId) {
        CounterDAO counterDAO = dbService.getDao(CounterDAO.class);
        return counterDAO.getAllConterList(companyId);
    }

    @Override
    public Integer addCounter(CounterDTO counterDTO) {
        CounterDAO counterDAO = dbService.getDao(CounterDAO.class);
        int success = counterDAO.addCounter(counterDTO);
        return success;
    }

    @Override
    public Integer enableDisableCounter(boolean status, int id) {
        CounterDAO counterDAO = dbService.getDao(CounterDAO.class);
        int success = counterDAO.enableDisableCounter(status,id);
        return null;
    }

    @Override
    public List<UserConfigurationDTO> getUsersForAssignCounter(int companyId, int branchId) {
        CounterDAO counterDAO = dbService.getDao(CounterDAO.class);
        return counterDAO.getUsersForAssignCounter(companyId,branchId);
    }

    @Override
    public Integer assignUsersToCounter(CounterDTO counterDTO) {
        CounterDAO counterDAO = dbService.getDao(CounterDAO.class);
        int success = counterDAO.AssignCounterToUser(counterDTO);
        return success;
    }

    @Override
    public List<UserCounterDTO> getUsersForCounterListing(int companyId) {
        CounterDAO counterDAO = dbService.getDao(CounterDAO.class);
        return counterDAO.getUsersForCounterListing(companyId);
    }

    @Override
    public void updateUserStatusInCounter(boolean status, int userId, int counterId) {
        CounterDAO counterDAO = dbService.getDao(CounterDAO.class);
        counterDAO.updateUserStatusInCounter(status,userId,counterId);
    }

    @Override
    public List<CounterDTO> getUserCounterDetailsForLocalStorage(int companyId, int userId) {
        CounterDAO counterDAO = dbService.getDao(CounterDAO.class);
        return counterDAO.getUserCounterDetailsForLocalStorage(companyId,userId);
    }
}
