package com.vti.repository;

import com.vti.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Integer> {

    boolean existsByUsername(String userName);

    boolean existsByEmail(String email);

    //
//	@Query("	SELECT 	status 		"
//			+ "	FROM 	User 		"
//			+ " WHERE 	email = :email")
//	public UserStatus findStatusByEmail(@Param("email") String email);
//
    User findByUsername(String name);

    User findByEmail(String email);
}
