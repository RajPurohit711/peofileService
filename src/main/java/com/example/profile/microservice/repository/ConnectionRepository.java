package com.example.profile.microservice.repository;

import com.example.profile.microservice.entity.Connection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ConnectionRepository extends CrudRepository<Connection,Long> {

    Connection findByUserEmailAndTargetEmail(String userEmail, String targetEmail);


    void deleteByUserEmailAndTargetEmail(String userEmail, String targetEmail);

    @Query(value="select target_email from Connection where user_email = ?1 and connection_type=?2",nativeQuery=true)
    Iterable<String> findByUserEmailAndConnectionType(String userEmail,String connectionType);

    @Query(value="select user_email from Connection where target_email = ?1 and connection_type=?2",nativeQuery=true)
    Iterable<String> findByTargetEmailAndConnectionType(String targetId,String connectionType);

}
