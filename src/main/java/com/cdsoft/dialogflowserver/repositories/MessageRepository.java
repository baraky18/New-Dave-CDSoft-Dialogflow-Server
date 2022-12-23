package com.cdsoft.dialogflowserver.repositories;

import com.cdsoft.dialogflowserver.entities.Message;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;


@Repository
public interface MessageRepository extends CrudRepository<Message, String> {

    Optional<Message> findMessageBySentDateTimeAndText(LocalDateTime sentDate, String text);
}
