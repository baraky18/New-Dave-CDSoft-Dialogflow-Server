package com.cdsoft.dialogflowserver.repositories;

import com.cdsoft.dialogflowserver.entities.Session;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface SessionRepository extends CrudRepository<Session, String> {

    Optional<Session> findBySessionUuid(String sessionUuid);

    Optional<Session> findBySessionName(String sessionName);
}
