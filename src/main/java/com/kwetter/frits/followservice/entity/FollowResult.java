package com.kwetter.frits.followservice.entity;

import java.util.List;

public class FollowResult {

    private List<Follow> followList;
    private int count;

    public FollowResult() {}

    public FollowResult(List<Follow> followList, int count) {
        this.followList = followList;
        this.count = count;
    }

    public List<Follow> getFollowList() {
        return followList;
    }

    public void setFollowList(List<Follow> followList) {
        this.followList = followList;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
