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
public class CustomQueryCreator {
    private static final Integer COMPANYID = 0;
    private static final Integer BRANCHID = 0;
    private static final Integer PAGESIZE = 5;
    private static final Integer OFFSET = 1;
    private static final String SEARCHBY = "";
    private static final String SEARCHWILDCARD = "";
    private static final String ORDERBY = "";
    private static final String ORDERTYPE = "DESC";
    private static final String DATE = "";

    public static String generateQueryWithCase(HttpServletRequest request, PaginationTypeClass ptc,
            DbService dbService) {
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
                    if (searchBy.equals("category_id")) {
                        int[] catIds = dbService.getDao(CategoryProductDAO.class)
                                .getCategoriesIdsByCloseName("%" + searchWildCard + "%");
                        String categoryIds = "";
                        for (int i = 0; i < catIds.length; i++) {
                            categoryIds += catIds[i] + ",";

                        }
                        String newCatIds = categoryIds.substring(0, categoryIds.length() - 1);
                        caseQuery = " AND " + searchBy + " IN (" + newCatIds + ")";
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

            case CUSTOMER -> {
                caseQuery += " deleted = false and customer = true ";
                if (!searchBy.isEmpty()) {

                    caseQuery += " AND lower(" + searchBy + "::text) LIKE '%" + searchWildCard + "%'";

                }
                if (!orderBy.isEmpty()) {
                    caseQuery += " ORDER BY " + orderBy + " " + orderType;
                } else {
                    caseQuery += "order by company_id desc";
                }
                caseQuery += " OFFSET  " + (offset - 1) + " LIMIT " + pageSize;
            }

            case DEBTORS -> {
                caseQuery += " SELECT  customer_pan, customer_name,  sum(total_amount) as totalAmount, sum(tax_amount) as taxAmount, c.email as email from sales_bill sb inner join company c on sb.customer_pan::bigint = c.pan_no WHERE sb.company_id = "
                        + companyId + " AND branch_id =" + branchId + " AND sb.status = true ";
                if (!Boolean.parseBoolean(request.getParameter("salesTally"))) {
                    caseQuery += "  AND sale_type = 2 ";
                }
                if (!request.getParameter("currentFiscalYear").trim().isEmpty()) {
                    caseQuery += " AND fiscal_year = '" + request.getParameter("currentFiscalYear") + "' ";
                }

                if (!searchBy.isEmpty()) {
                    if (searchBy.equals("customer_pan")) {
                        caseQuery += " AND customer_pan= '" + searchWildCard + "'";
                    } else {
                        caseQuery += " AND lower(" + searchBy + "::text) LIKE lower('%" + searchWildCard + "%')  ";

                    }
                }
                caseQuery += " GROUP BY customer_pan, customer_name, c.email ";
                if (!orderBy.isEmpty()) {
                    caseQuery += " ORDER BY " + orderBy + " " + orderType;
                } else {
                    caseQuery += " ORDER BY customer_name desc";
                }
                caseQuery += " OFFSET " + (offset - 1) + " LIMIT " + pageSize;
            }

            case DEBTORBILLS -> {
                String debtor_pan = request.getParameter("debtorPan");
                caseQuery += " SELECT id, bill_no, customer_pan, customer_name,  total_amount as totalAmount, bill_date_nepali from sales_bill WHERE company_id = "
                        + companyId + " AND branch_id =" + branchId
                        + " AND status = true AND sale_type = 2  AND  draft = false AND customer_pan= '" + debtor_pan
                        + "' ";
                if (!searchBy.isEmpty() && !searchWildCard.isEmpty()) {
                    if (searchBy.equals("customer_pan") || searchBy.equals("bill_no")) {
                        caseQuery += " AND " + searchBy + " = '" + searchWildCard + "' ";
                    } else {
                        caseQuery += " AND lower(" + searchBy + "::text) LIKE '%" + searchWildCard + "%'  ";

                    }
                }
                if (!orderBy.isEmpty()) {
                    caseQuery += " ORDER BY " + orderBy + " " + orderType;
                } else {
                    caseQuery += " ORDER BY customer_name desc";
                }
                caseQuery += " OFFSET " + (offset - 1) + " LIMIT " + pageSize;
            }

            case CREDITORS -> {
                // caseQuery += " company_id = " + companyId + " AND branch_id =" + branchId + "
                // AND status = true ";
                // if (!Boolean.parseBoolean(request.getParameter("purchaseTally"))) {
                // caseQuery += " AND sale_type = 2 ";
                // }
                // if (!searchBy.isEmpty()) {
                // if (searchBy.equals("creditors")) {
                // caseQuery += " AND ( seller_pan= '" + searchWildCard + "' OR seller_name LIKE
                // '%"
                // + searchWildCard
                // + "%')";
                // }
                // }
                // if (!orderBy.isEmpty()) {
                // caseQuery += " ORDER BY " + orderBy + " " + orderType;
                // } else {
                // caseQuery += " ORDER BY seller_pan desc";
                // }
                // caseQuery += " OFFSET " + (offset - 1) + " LIMIT " + pageSize;

                // mystart 
                caseQuery += "SELECT fQ.seller_pan, fQ.seller_name,  fQ.tax_amount, fQ.email, (fQ.total_amount -sQ.amount) as totalAmount FROM ";
                caseQuery += " ( SELECT  seller_pan, seller_name,  sum(total_amount) as total_amount, sum(tax_amount) as tax_amount, c.email as email from purchase_bill sb inner join company c on sb.seller_pan::bigint = c.pan_no WHERE sb.company_id = "
                        + companyId + " AND branch_id =" + branchId + " AND sb.status = true ";
                if (!Boolean.parseBoolean(request.getParameter("salesTally"))) {
                    caseQuery += "  AND sale_type = 2 ";
                }
                if (request.getParameter("currentFiscalYear") != null && !request.getParameter("currentFiscalYear").trim().isEmpty()) {
                    caseQuery += " AND fiscal_year = '" + request.getParameter("currentFiscalYear") + "' ";
                }

                if (!searchBy.isEmpty()) {
                    if (searchBy.equals("seller_pan")) {
                        caseQuery += " AND seller_pan= '" + searchWildCard + "'";
                    } else {
                        caseQuery += " AND lower(" + searchBy + "::text) LIKE lower('%" + searchWildCard + "%')  ";

                    }
                }
                caseQuery += " GROUP BY seller_pan, seller_name, c.email ";
                if (!orderBy.isEmpty()) {
                    caseQuery += " ORDER BY " + orderBy + " " + orderType;
                } else {
                    caseQuery += " ORDER BY seller_name desc";
                }
                caseQuery += " OFFSET " + (offset - 1) + " LIMIT " + pageSize;
                caseQuery +=" ) as  fQ LEFT JOIN ";
                caseQuery+=" ( SELECT p.party_id, sum(p.amount) as amount from payment p where company_id= "+ companyId + " and branch_id = "+branchId+"  group by p.party_id ) as  sQ on fQ.seller_pan = sQ.party_id";
            }

            case SUPERADMIN -> {
                caseQuery += " customer = true ";
                if (!searchWildCard.isEmpty()) {
                    if (searchWildCard.matches("\\d+")) {
                        caseQuery += " AND ( pan_no= " + searchWildCard + " ) ";
                    } else if (searchWildCard.matches("[a-zA-Z]+")) {
                        caseQuery += " AND ( name LIKE '%" + searchWildCard + "%' OR email LIKE '%" + searchWildCard
                                + "%' ) ";
                    }
                }
                if (!orderBy.isEmpty()) {
                    caseQuery += " ORDER BY " + orderBy + " " + orderType;
                } else {
                    caseQuery += " ORDER BY name asc";
                }
                caseQuery += " OFFSET " + (offset - 1) + " LIMIT " + pageSize;
            }
        }
        return caseQuery;
    }

    private static <T> T getParameterOrDefault(String paramName, HttpServletRequest request, T defaultValue,
            Class<T> classz) {
        String paramValue = request.getParameter(paramName);
        if (paramValue != null) {
            if (classz == Integer.class) {
                T t = classz.cast(Integer.parseInt(paramValue));
                return t;
            } else if (classz == String.class) {
                T t = classz.cast(paramValue);
                return t;
            }
        }
        return defaultValue;
    }
}
