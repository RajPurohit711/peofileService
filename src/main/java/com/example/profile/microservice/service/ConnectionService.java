package com.example.profile.microservice.service;

import com.example.profile.microservice.entity.Connection;

import java.util.List;

public interface ConnectionService {

    Connection findConnectionsByUserEmailAndTargetEmail(String userEmail, String targetEmail);
    void saveConnection(Connection connection);
    List<Connection> findAll();
    void deleteConnection(String userEmail,String targetEmail);
    public Iterable<String> findByUserEmailAndConnectionType(String userEmail,String connectionType);
    public Iterable<String> findByTargetEmailAndConnectionType(String targetEmail,String connectionType);
}
