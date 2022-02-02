package com.example.profile.microservice.service.Impl;


import com.example.profile.microservice.dto.PostProfileDto;
import com.example.profile.microservice.dto.ProfileFeedPostDto;
import com.example.profile.microservice.dto.ProfileFeedStoryDto;
import com.example.profile.microservice.dto.StoryProfileDto;
import com.example.profile.microservice.service.ConnectionService;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListenerService {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    DirectExchange exchangeProfileFeedPost;

    @Autowired
    DirectExchange exchangeProfileFeedStory;

    @Autowired
    ConnectionService connectionService;

    @RabbitListener(queues = "queue.PostProfile")
    public void getFollowers(PostProfileDto postProfileDto){
        List<String> followers= (List<String>) connectionService.findByTargetEmailAndConnectionType(postProfileDto.getUserEmail(),"following");
        ProfileFeedPostDto profileFeedPostDto=new ProfileFeedPostDto();
        profileFeedPostDto.setPostId(postProfileDto.getPostId());
        profileFeedPostDto.setFollowers(followers);
        rabbitTemplate.convertAndSend(exchangeProfileFeedPost.getName(),"routing.ProfileFeedPost",profileFeedPostDto);
    }


    @RabbitListener(queues = "queue.StoryProfile")
    public void getFollowersForStory(StoryProfileDto storyProfileDto){
        List<String> followers= (List<String>) connectionService.findByTargetEmailAndConnectionType(storyProfileDto.getUserEmail(),"following");
        ProfileFeedStoryDto profileFeedStoryDto =new ProfileFeedStoryDto();
        profileFeedStoryDto.setStoryId(storyProfileDto.getStoryId());
        profileFeedStoryDto.setFollowers(followers);
        rabbitTemplate.convertAndSend(exchangeProfileFeedStory.getName(),"routing.ProfileFeedStory",profileFeedStoryDto);
    }




}
