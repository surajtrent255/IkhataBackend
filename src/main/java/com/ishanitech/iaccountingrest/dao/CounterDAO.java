package com.ishanitech.iaccountingrest.dao;

import com.ishanitech.iaccountingrest.dto.CounterDTO;
import com.ishanitech.iaccountingrest.dto.UserConfigurationDTO;
import com.ishanitech.iaccountingrest.dto.UserCounterDTO;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

public interface CounterDAO {

//    For Counter Only

   @SqlQuery("SELECT * FROM counter WHERE company_id = :companyId")
    @RegisterBeanMapper(CounterDTO.class)
    List<CounterDTO> getAllConterList(@Bind int companyId);


   @SqlUpdate(" INSERT INTO public.counter( " +
           "  name, company_id, branch_id, date, status) " +
           " VALUES ( :name, :companyId, :branchId, :date, :status);")
    @RegisterBeanMapper(CounterDTO.class)
    Integer addCounter(@BindBean CounterDTO counterDTO);

   @SqlUpdate("UPDATE public.counter " +
           " SET  status= :status " +
           " WHERE id = :id;")
    Integer enableDisableCounter(@Bind boolean status,@Bind int id);


//   For User counter


@SqlQuery("  SELECT users.id AS userId, users.firstname AS firstName, users.lastname AS lastName, users.email AS email " +
        "    FROM users " +
        "    INNER JOIN user_branch ON user_branch.user_id = users.id " +
        "    WHERE user_branch.branch_id=:branchId" +
        " AND user_branch.company_id = :companyId " +
        " AND users.id NOT IN (SELECT user_id FROM user_counter);")
    @RegisterBeanMapper(UserConfigurationDTO.class)
    List<UserConfigurationDTO> getUsersForAssignCounter(@Bind int companyId,@Bind int branchId);

@SqlUpdate("INSERT INTO public.user_counter( " +
        "  counter_id, user_id, company_id, branch_id) " +
        " VALUES (:counterId, :userId, :companyId, :branchId);")
    @RegisterBeanMapper(CounterDTO.class)
    Integer AssignCounterToUser(@BindBean CounterDTO counterDTO);


//for counter user Listing
    @SqlQuery(" SELECT u.id as userId, u.firstname as firstName,u.lastname as lastName,u.email as email,u.phone as phone, " +
            " c.counter_id as counterId,c.company_id as companyId,c.branch_id as branchId,c.status as counterStatus " +
            " FROM users u " +
            " INNER JOIN user_counter c " +
            " ON c.user_id = u.id " +
            " WHERE c.company_id = :companyId")
    @RegisterBeanMapper(UserCounterDTO.class)
    List<UserCounterDTO> getUsersForCounterListing(@Bind int companyId);

//    enable disable users from counter

    @SqlUpdate(" UPDATE user_counter " +
            " SET  status=:status " +
            " WHERE user_id = :userId AND counter_id = :counterId;")
    void updateUserStatusInCounter(@Bind boolean status,@Bind int userId,@Bind int counterId);

   @SqlQuery("SELECT c.id as counterId, c.name as name , c.company_id as companyId " +
           " FROM counter c INNER JOIN " +
           "  user_counter uc ON uc.counter_id = c.id  " +
           "  WHERE uc.company_id = :companyId AND uc.user_id = :userId AND uc.status=true")
    @RegisterBeanMapper(CounterDTO.class)
    List<CounterDTO> getUserCounterDetailsForLocalStorage(@Bind int companyId,@Bind int userId);



}
