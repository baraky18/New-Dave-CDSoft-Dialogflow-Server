package com.cdsoft.dialogflowserver.repositories;

import com.cdsoft.dialogflowserver.entities.Session;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SessionRepository extends CrudRepository<Session, String> {

}
