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

import co.grandcircus.HelpMeApp.model.Message;

public interface UserMessageDao extends JpaRepository<Message, Long> {


}