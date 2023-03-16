package com.ishanitech.iaccountingrest.dao;

import com.ishanitech.iaccountingrest.model.Role;
import com.ishanitech.iaccountingrest.model.User;
import org.jdbi.v3.core.result.LinkedHashMapRowReducer;
import org.jdbi.v3.core.result.RowView;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.statement.UseRowReducer;
import org.jdbi.v3.sqlobject.transaction.Transaction;

import java.util.Map;
import java.util.Optional;

@RegisterBeanMapper(value = User.class, prefix = "u")
@RegisterBeanMapper(value = Role.class, prefix = "r")
public interface UserDAO {
	@GetGeneratedKeys
	@SqlUpdate("INSERT INTO users ( "
			+ " firstname, "
			+ " lastname, "
			+ " email, "
			+ " password "
			+ " ) "
			+ " VALUES ("
			+ " :firstname, "
			+ " :lastname, "
			+ " :email, "
			+ " :password "
			+ "  )")
	int addUser(@BindBean User user);

	@Transaction
	default int addUserWithRole(User user, int roleId){
		int savedUserId = addUser(user);
		assignRole(savedUserId, roleId);
		return savedUserId;
	}
	@SqlUpdate("INSERT INTO user_role (user_id, role_id) VALUES ( :savedUserId, :roleId)")
	void assignRole(@Bind int savedUserId, @Bind int roleId);

	@SqlQuery(" SELECT u.id AS  u_id, "
			+ " u.firstname AS  u_firstname, "
			+ " u.lastname AS  u_lastname, "
			+ " u.password AS u_password,"
			+ " u.email AS u_email, "
			+ " u.create_date AS u_created_date, "
			+ " u.edit_date AS u_edited_date, "
			+ " r.id AS r_id, "
			+ " r.role as r_role FROM users u INNER JOIN user_role ur ON u.id = ur.user_id "
			+ " INNER JOIN role r ON r.id = ur.role_id "
			+ " WHERE u.id = :userId;")
	@UseRowReducer(UserReducer.class)
	Optional<User> getUserById(@Bind("userId") int userId);

	@SqlQuery(" SELECT u.id AS  u_id, "
			+ " u.firstname AS  u_firstname, "
			+ " u.lastname AS  u_lastname, "
			+ " u.password AS u_password,"
			+ " u.email AS u_email, "
			+ " r.id AS r_id, "
			+ " r.role as r_role FROM users u INNER JOIN user_role ur ON u.id = ur.user_id "
			+ " INNER JOIN role r ON r.id = ur.role_id "
			+ " WHERE u.email = :email;")
	@UseRowReducer(UserReducer.class)
	Optional<User> getUserByEmail(@Bind("email") String email) ;


	class UserReducer implements LinkedHashMapRowReducer<Integer,User> {
		@Override
		public void accumulate(Map<Integer, User> container, RowView rowView) {
			User user = container.computeIfAbsent(rowView.getColumn("u_id", Integer.class), id -> rowView.getRow(User.class));
			if(rowView.getColumn("r_id", Integer.class) != null) {
				Role role = rowView.getRow(Role.class);
				user.getRoles().add(role);
			}
		}
	}

}
