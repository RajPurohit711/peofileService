package com.example.profile.microservice.service.Impl;

import com.example.profile.microservice.entity.Connection;
import com.example.profile.microservice.repository.ConnectionRepository;
import com.example.profile.microservice.service.ConnectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ConnectionServiceImpl implements ConnectionService {

    @Autowired
    ConnectionRepository connectionRepository;

    @Override
    public Connection findConnectionsByUserEmailAndTargetEmail(String userEmail,String targetEmail){
        return connectionRepository.findByUserEmailAndTargetEmail(userEmail,targetEmail);
    }

    @Override
    public Iterable<String> findByUserEmailAndConnectionType(String userEmail,String connectionType){
        return connectionRepository.findByUserEmailAndConnectionType(userEmail,connectionType);
    }

    @Override
    public void saveConnection(Connection connection){
        connectionRepository.save(connection);
    }

    @Override
    public List<Connection> findAll() {
        return (List<Connection>) connectionRepository.findAll();
    }

    @Transactional
    @Override
    public void deleteConnection(String userEmail, String targetEmail) {
        connectionRepository.deleteByUserEmailAndTargetEmail(userEmail,targetEmail);
    }

    @Override
    public Iterable<String> findByTargetEmailAndConnectionType(String targetEmail,String connectionType){
        return connectionRepository.findByTargetEmailAndConnectionType(targetEmail,connectionType);
    }




}
