package com.example.profile.microservice.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {

    public static final String ROUTING_POST_PROFILE ="routing.PostProfile";
    public static final String ROUTING_STORY_PROFILE ="routing.StoryProfile";
    public static final String ROUTING_PROFILE_FEED_POST ="routing.ProfileFeedPost";
    public static final String ROUTING_PROFILE_FEED_STORY ="routing.ProfileFeedStory";


    @Bean
    Queue queueProfileFeedPost(){
        return new Queue("queue.ProfileFeedPost",false);
    }

    @Bean
    Queue queueProfileFeedStory(){
        return new Queue("queue.ProfileFeedStory",false);
    }

    @Bean
    Queue queuePostProfile(){
        return new Queue("queue.PostProfile",false);
    }

    @Bean
    Queue queueStoryProfile(){
        return new Queue("queue.StoryProfile",false);
    }



    @Bean
    DirectExchange exchangePostProfile(){
        return new DirectExchange("direct.exchangePostProfile");
    }

    @Bean
    DirectExchange exchangeProfileFeedPost(){
        return new DirectExchange("direct.exchangeProfileFeedPost");
    }

    @Bean
    DirectExchange exchangeProfileFeedStory(){
        return new DirectExchange("direct.exchangeProfileFeedStory");
    }

    @Bean
    DirectExchange exchangeStoryProfile(){
        return new DirectExchange("direct.exchangeStoryProfile");
    }

    @Bean
    Binding bindingPostProfile(Queue queuePostProfile, DirectExchange exchangePostProfile){
        return BindingBuilder.bind(queuePostProfile).to(exchangePostProfile).with(ROUTING_POST_PROFILE);
    }

    @Bean
    Binding bindingStoryProfile(Queue queueStoryProfile, DirectExchange exchangeStoryProfile){
        return BindingBuilder.bind(queueStoryProfile).to(exchangeStoryProfile).with(ROUTING_STORY_PROFILE);
    }

    @Bean
    Binding bindingProfileFeedPost(Queue queueProfileFeedPost, DirectExchange exchangeProfileFeedPost){
        return BindingBuilder.bind(queueProfileFeedPost).to(exchangeProfileFeedPost).with(ROUTING_PROFILE_FEED_POST);
    }

    @Bean
    Binding bindingProfileFeedStory(Queue queueProfileFeedStory, DirectExchange exchangeProfileFeedStory){
        return BindingBuilder.bind(queueProfileFeedStory).to(exchangeProfileFeedStory).with(ROUTING_PROFILE_FEED_STORY);
    }



    @Bean
    MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    RabbitTemplate rabbitTemplate(ConnectionFactory factory){
        RabbitTemplate rabbitTemplate=new RabbitTemplate(factory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }
}






