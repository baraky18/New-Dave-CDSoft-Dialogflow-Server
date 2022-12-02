package com.cdsoft.dialogflowserver.repositories;

import com.cdsoft.dialogflowserver.entities.Message;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MessageRepository extends CrudRepository<Message, String> {

}
