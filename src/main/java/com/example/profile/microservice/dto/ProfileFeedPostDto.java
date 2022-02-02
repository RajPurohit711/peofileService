package com.example.profile.microservice.dto;

import java.util.List;

public class ProfileFeedPostDto {
    private Long postId;
    private List<String> followers;

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public List<String> getFollowers() {
        return followers;
    }

    public void setFollowers(List<String> followers) {
        this.followers = followers;
    }
}
