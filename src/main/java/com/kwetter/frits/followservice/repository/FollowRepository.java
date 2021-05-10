package com.kwetter.frits.followservice.repository;

import com.kwetter.frits.followservice.entity.Follow;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowRepository extends MongoRepository<Follow, String> {
    void deleteFollowByUsernameAndFollowingUsername(String username, String followingUsername);
    List<Follow> findAllByUsername(String username);
    List<Follow> findAllByFollowingUsername(String username);
    Boolean existsFollowByUsernameAndFollowingUsername(String username, String followingUsername);
}
