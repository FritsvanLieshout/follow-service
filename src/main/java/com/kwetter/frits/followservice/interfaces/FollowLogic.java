package com.kwetter.frits.followservice.interfaces;

import com.kwetter.frits.followservice.entity.Follow;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public interface FollowLogic {
    Follow followUser(Follow follow);
    void unFollowUser(Follow follow);
    List<Follow> getFollowing(String username);
    List<Follow> getFollowers(String username);
    Boolean checkAlreadyExist(String username, String followUsername);
}
