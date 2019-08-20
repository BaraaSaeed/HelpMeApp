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


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import co.grandcircus.HelpMeApp.model.Message;

public interface MessageDao extends JpaRepository<Message, Long> {
	
	List<Message> findAllByUserIdAndOrgId(Long userId, Long orgId);


}