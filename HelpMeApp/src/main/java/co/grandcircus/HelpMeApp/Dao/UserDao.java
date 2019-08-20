/**
 * Copyright (c) 2019.
 * This program and the accompanying materials are made available
 * under my granted permission provided that this note is kept intact, unmodified and unchanged.
 * @ Author: Baraa Ali, Gerard Breitenbeck, Sienna Harris -  API and implementation.
 * All rights reserved.
*/
/**
 * 
 */
package co.grandcircus.HelpMeApp.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import co.grandcircus.HelpMeApp.model.User;

public interface UserDao extends JpaRepository<User, Long> {

	@Query("FROM User WHERE email = :email AND password= :password")
	User findAllByEmailAndPassowrd(String email, String password);

	User findAllById(Long userId);
}
