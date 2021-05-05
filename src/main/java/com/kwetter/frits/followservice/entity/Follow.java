package com.kwetter.frits.followservice.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "follow")
public class Follow {

    @Id
    private String id;

    private String username;
    private String followUsername;

    public Follow() {}

    public Follow(String username, String followUsername) {
        this.username = username;
        this.followUsername = followUsername;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFollowUsername() {
        return followUsername;
    }

    public void setFollowUsername(String followUsername) {
        this.followUsername = followUsername;
    }
}
