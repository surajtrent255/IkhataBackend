package com.ishanitech.iaccountingrest.utils;

import com.ishanitech.iaccountingrest.dao.CategoryProductDAO;
import com.ishanitech.iaccountingrest.dto.PaginationTypeClass;
import com.ishanitech.iaccountingrest.service.DbService;
import jakarta.annotation.Nullable;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomQueryCreator{
    private static final Integer COMPANYID = 0;
    private static final Integer BRANCHID = 0;
    private static final Integer PAGESIZE  = 5;
    private static final Integer OFFSET = 1;
    private static final String SEARCHBY = "";
    private static final String SEARCHWILDCARD="";
    private static final String ORDERBY="";
    private static final String ORDERTYPE="DESC";
    private static final String DATE="";
    public static String generateQueryWithCase(HttpServletRequest request, PaginationTypeClass ptc, DbService dbService){
        String caseQuery = "";
        Integer companyId;
        Integer branchId;
        Integer pageSize;
        Integer offset;
        String searchBy;
        String searchWildCard;
        String orderBy;
        String orderType;

        companyId = getParameterOrDefault("compId", request, COMPANYID, Integer.class);
        branchId = getParameterOrDefault("branchId", request, BRANCHID, Integer.class);
        pageSize = getParameterOrDefault("pageTotalItems", request, PAGESIZE, Integer.class);
        offset = getParameterOrDefault("offset", request, OFFSET, Integer.class);
        searchBy = getParameterOrDefault("searchBy", request, SEARCHBY, String.class);
        searchWildCard = getParameterOrDefault("searchWildCard", request, SEARCHWILDCARD, String.class);
        orderBy = getParameterOrDefault("orderBy", request, ORDERBY, String.class);
        orderType = getParameterOrDefault("orderType", request, ORDERTYPE, String.class);

        switch (ptc) {
            case EMPLOYEE -> {
                caseQuery += " company_id = " + companyId + " AND branch_id =" + branchId;
                if (!searchBy.isEmpty()) {
                    caseQuery += " AND lower(" + searchBy + "::text) LIKE '%" + searchWildCard.toLowerCase() + "%'";
                }
                caseQuery += " AND deleted = false ";
                if (!orderBy.isEmpty()) {
                    caseQuery += " ORDER BY " + orderBy + " " + orderType;
                }
                caseQuery += " OFFSET  " + (offset - 1) + " LIMIT " + pageSize;
            }

            case PRODUCT -> {
                caseQuery += " AND p.company_id = " + companyId + " AND p.branch_id =" + branchId;
                if (!searchBy.isEmpty()) {
                    if(searchBy.equals("category_id")){
                        int [] catIds =
                                dbService.getDao(CategoryProductDAO.class).getCategoriesIdsByCloseName("%"+searchWildCard+"%");
                        String categoryIds = "";
                        for (int i = 0; i < catIds.length; i++) {
                            categoryIds += catIds[i] + ",";

                        }
                        String newCatIds = categoryIds.substring(0, categoryIds.length() - 1);
                        caseQuery = " AND "+searchBy+" IN ("+newCatIds+")";
                    } else {
                        caseQuery += " AND lower(" + searchBy + "::text) LIKE '%" + searchWildCard + "%'";

                    }
                }
                if (!orderBy.isEmpty()) {
                    caseQuery += " ORDER BY " + orderBy + " " + orderType;
                } else {
                    caseQuery += "ORDER BY ID DESC";
                }
                caseQuery += " OFFSET  " + (offset - 1) + " LIMIT " + pageSize;
            }
        }
        return caseQuery;
    }

    private static<T> T getParameterOrDefault(String paramName, HttpServletRequest request, T defaultValue, Class<T> classz ) {
        String paramValue =  request.getParameter(paramName);
        if(paramValue != null){
            if(classz == Integer.class){
                T t= classz.cast(Integer.parseInt(paramValue));
                return t;
            } else if (classz == String.class){
                 T t = classz.cast(paramValue);
                 return t;
            }
        }
        return defaultValue;
    }
}