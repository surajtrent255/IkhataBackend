package com.ishanitech.iaccountingrest.service.impl;

import com.ishanitech.iaccountingrest.dao.MergeProductDAO;
import com.ishanitech.iaccountingrest.dao.SplitProductDAO;
import com.ishanitech.iaccountingrest.dto.MergeProductDTO;
import com.ishanitech.iaccountingrest.exception.CustomSqlException;
import com.ishanitech.iaccountingrest.service.DbService;
import com.ishanitech.iaccountingrest.service.MergeProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jdbi.v3.core.JdbiException;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MergeProductServiceImpl implements MergeProductService {
    private final DbService dbService;
    @Override
    public List<MergeProductDTO> getAllMergeProduct(int companyId, int branchId) {

        MergeProductDAO MergeProductDAO = dbService.getDao(MergeProductDAO.class);

        return MergeProductDAO.getAllMergeProduct(companyId,branchId);
    }

    @Override
    public int addMerge(MergeProductDTO mergeProductDTO) {
        MergeProductDAO MergeProductDAO = dbService.getDao(MergeProductDAO.class);
        int Id =0;


        try{

            Id = MergeProductDAO.addMerge(mergeProductDTO);
            return Id;
        } catch(JdbiException jdbiException) {

            log.error("error occured while adding merging : " + jdbiException.getMessage());
            throw new CustomSqlException("Error occured while adding Merging");

        }
    }

    @Override
    public int updateMerge(MergeProductDTO mergeProductDTO) {
        MergeProductDAO MergeProductDAO = dbService.getDao(MergeProductDAO.class);
        return MergeProductDAO.updateMerge(mergeProductDTO, mergeProductDTO.getId());
    }
}
