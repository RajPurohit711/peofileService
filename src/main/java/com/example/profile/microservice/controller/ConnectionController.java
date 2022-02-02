package com.example.profile.microservice.controller;

import com.example.profile.microservice.dto.ConnectionDto;
import com.example.profile.microservice.dto.ResponseDto;
import com.example.profile.microservice.entity.Connection;
import com.example.profile.microservice.service.ConnectionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/connection")
public class ConnectionController {



    @Autowired
    ConnectionService connectionService;


    @RequestMapping(value = "/add",method = {RequestMethod.POST,RequestMethod.PUT})
    public void add(@RequestBody ConnectionDto connectionDto){
        Connection connection=new Connection();
        BeanUtils.copyProperties(connectionDto,connection);
        connectionService.saveConnection(connection);

    }


    @GetMapping("/{id}")
    public List<String> findFollowingByUserEmail(@PathVariable("id") String userEmail){
        return (List<String>) connectionService.findByUserEmailAndConnectionType(userEmail,"following");
    }

    @GetMapping("/{id}/{type}")
    public List<String> findFollowerByUserEmail(@PathVariable("id") String userEmail, @PathVariable("type") String type){
        return (List<String>) connectionService.findByTargetEmailAndConnectionType(userEmail,type);
    }



    @GetMapping("/all")
    public List<ConnectionDto> getAll(){
        List<ConnectionDto> connectionDtos=new ArrayList<>();
        List<Connection> connections=connectionService.findAll();
        for (Connection connection:connections){
            ConnectionDto connectionDto=new ConnectionDto();
            BeanUtils.copyProperties(connection,connectionDto);
            connectionDtos.add(connectionDto);
        }
        return connectionDtos;
    }

    @DeleteMapping("/delete/{userEmail}/{targetEmail}")
    void deleteConnection(@PathVariable("userEmail") String userEmail,@PathVariable("targetEmail") String targetEmail){
        connectionService.deleteConnection(userEmail,targetEmail);
    }

    @RequestMapping(value = "/response",method = {RequestMethod.POST,RequestMethod.PUT})
    public Connection updateConnection(@RequestBody ResponseDto responseDto){
        Connection connection=connectionService.findConnectionsByUserEmailAndTargetEmail(responseDto.getUserEmail(),responseDto.getTargetEmail());
        connection.setConnectionType(responseDto.getResponse());
        connectionService.saveConnection(connection);
        return connection;

    }

}


//    @GetMapping("/{id}/{type}")
//    public List<ConnectionDto> findFollowingByUserId(@PathVariable("id") String userId, @PathVariable("type") String type){
//        List<ConnectionDto> connectionDtos=new ArrayList<>();
//        List<Connection> connections= (List<Connection>) connectionService.findConnectionsByUserIdAndType(userId,type);
//        BeanUtils.copyProperties(connections,connectionDtos);
//
//        return connectionDtos;
//    }