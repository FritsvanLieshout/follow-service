package com.kwetter.frits.followservice.repository;

import com.kwetter.frits.followservice.entity.Follow;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowRepository extends MongoRepository<Follow, String> {
    void deleteFollowByUsernameAndFollowUsername(String username, String followUsername);
    List<Follow> findAllByUsername(String username);
    List<Follow> findAllByFollowUsername(String username);
    Boolean existsFollowByUsernameAndFollowUsername(String username, String followUsername);
}
