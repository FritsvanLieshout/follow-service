package com.kwetter.frits.followservice.controller;

import com.kwetter.frits.followservice.entity.Follow;
import com.kwetter.frits.followservice.entity.FollowResult;
import com.kwetter.frits.followservice.entity.FollowViewModel;
import com.kwetter.frits.followservice.logic.FollowLogicImpl;
import com.kwetter.frits.followservice.logic.TimelineLogicImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/follow")
public class FollowController {

    private final FollowLogicImpl followLogic;
    private final TimelineLogicImpl timelineLogic;

    public FollowController(FollowLogicImpl followLogic, TimelineLogicImpl timelineLogic) {
        this.followLogic = followLogic;
        this.timelineLogic = timelineLogic;
    }

    @PostMapping("/user/follow")
    public ResponseEntity<Follow> followUser(@RequestBody FollowViewModel follow) {
        try {
            if (follow.getUsername() != null && follow.getFollowingUsername() != null) {
                if (followLogic.checkAlreadyExist(follow.getUsername(), follow.getFollowingUsername())) {
                    return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
                }
                var result = followLogic.followUser(new Follow(follow.getUsername(), follow.getFollowingUsername()));
                timelineLogic.timeLineFollow(result);
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        catch (Exception ex) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/user/unfollow")
    public ResponseEntity<Follow> unFollowUser(@RequestBody FollowViewModel follow) {
        try {
            if (follow.getUsername() != null && follow.getFollowingUsername() != null) {
                if (followLogic.checkAlreadyExist(follow.getUsername(), follow.getFollowingUsername())) {
                    var object = new Follow(follow.getUsername(), follow.getFollowingUsername());
                    followLogic.unFollowUser(object);
                    timelineLogic.timeLineUnfollow(object);
                    return new ResponseEntity<>(null, HttpStatus.OK);
                }
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        catch (Exception ex) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/following")
    public ResponseEntity<FollowResult> getFollowingAccounts(@RequestParam String username) {
        try {
            List<Follow> following = new ArrayList<>(followLogic.getFollowing(username));

            if (following.isEmpty()) {
                return new ResponseEntity<>(new FollowResult(following, 0), HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(new FollowResult(following, following.size()), HttpStatus.OK);
        }
        catch (Exception ex) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/followers")
    public ResponseEntity<FollowResult> getFollowersAccounts(@RequestParam String username) {
        try {
            List<Follow> followers = new ArrayList<>(followLogic.getFollowers(username));

            if (followers.isEmpty()) {
                return new ResponseEntity<>(new FollowResult(followers, 0), HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(new FollowResult(followers, followers.size()), HttpStatus.OK);
        }
        catch (Exception ex) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
