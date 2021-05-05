package com.kwetter.frits.followservice.logic;

import com.kwetter.frits.followservice.entity.Follow;
import com.kwetter.frits.followservice.interfaces.FollowLogic;
import com.kwetter.frits.followservice.repository.FollowRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FollowLogicImpl implements FollowLogic {

    private final FollowRepository followRepository;

    public FollowLogicImpl(FollowRepository followRepository) {
        this.followRepository = followRepository;
    }

    @Override
    public Follow followUser(Follow follow) {
        return followRepository.save(follow);
    }

    @Override
    public void unFollowUser(Follow follow) {
        followRepository.deleteFollowByUsernameAndFollowUsername(follow.getUsername(), follow.getFollowUsername());
    }

    @Override
    public List<Follow> getFollowing(String username) {
        return followRepository.findAllByUsername(username);
    }

    @Override
    public List<Follow> getFollowers(String username) {
        return followRepository.findAllByFollowUsername(username);
    }

    @Override
    public Boolean checkAlreadyExist(String username, String followUsername) {
        return followRepository.existsFollowByUsernameAndFollowUsername(username, followUsername);
    }
}
