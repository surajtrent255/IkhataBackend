package com.ishanitech.iaccountingrest.dao;

import com.ishanitech.iaccountingrest.dto.FixedAssetsDTO;
import jakarta.annotation.Nullable;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

public interface FixedAssetsDAO {

    @SqlUpdate("INSERT INTO fixed_assets( " +
            " company_id, name, amount, date,nepali_date, bill_no, cash, loan, loan_id, branch_id) " +
            " VALUES ( :companyId, :name, :amount, :date,:nepaliDate, :billNo, :cash, :loan, :loanId, :branchId);")
    Integer addFixAssetsDetails(@BindBean FixedAssetsDTO fixedAssetsDTO);


    @SqlQuery("SELECT * FROM fixed_assets WHERE company_id= :companyId")
    @RegisterBeanMapper(FixedAssetsDTO.class)
    List<FixedAssetsDTO> getFixedAssetsDetails(@Bind int companyId);

    @SqlQuery("SELECT * FROM fixed_assets WHERE sn= :SN")
    @RegisterBeanMapper(FixedAssetsDTO.class)
    FixedAssetsDTO getFixedAssetsDetailsBySN(@Bind int SN);

    @SqlUpdate("UPDATE fixed_assets " +
            " SET  name=:name, amount=:amount, date=:date,nepali_date=:nepaliDate, bill_no=:billNo, cash=:cash, loan=:loan, loan_id=:loanId " +
            " WHERE sn=:SN;")
    void updateFixedAssets(@BindBean FixedAssetsDTO fixedAssetsDTO);

    @SqlUpdate("DELETE FROM fixed_assets WHERE sn= :SN")
    @Nullable
    void deleteFromFixedAssets(@Bind int SN);

    @SqlQuery("""
            select * from fixed_assets fa where fa.company_id = :compId and fa.branch_id = :branchId order by SN desc limit :pageTotalItems offset (:offset -1) ;
            """)
    @RegisterBeanMapper(FixedAssetsDTO.class)
    List<FixedAssetsDTO> getLimitedFixedAssetsByCompanyAndBranchId(Integer offset, Integer pageTotalItems, Integer compId, Integer branchId);
}
