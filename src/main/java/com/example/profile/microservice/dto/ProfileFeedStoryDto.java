package com.example.profile.microservice.dto;

import java.util.List;

public class ProfileFeedStoryDto {
    private Long storyId;
    private List<String> followers;

    public Long getStoryId() {
        return storyId;
    }

    public void setStoryId(Long storyId) {
        this.storyId = storyId;
    }

    public List<String> getFollowers() {
        return followers;
    }

    public void setFollowers(List<String> followers) {
        this.followers = followers;
    }
}
