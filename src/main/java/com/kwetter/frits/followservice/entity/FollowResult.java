package com.kwetter.frits.followservice.entity;

import java.util.List;

public class FollowResult {

    private List<Follow> follows;
    private int count;

    public FollowResult() {}

    public FollowResult(List<Follow> follows, int count) {
        this.follows = follows;
        this.count = count;
    }

    public List<Follow> getFollows() {
        return follows;
    }

    public void setFollows(List<Follow> follows) {
        this.follows = follows;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
