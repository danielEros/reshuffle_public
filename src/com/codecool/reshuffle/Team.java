package com.codecool.reshuffle;

import java.util.ArrayList;
import java.util.List;

public class Team {

    private List<String> members = new ArrayList<>();

    public List<String> getMembers() {
        return members;
    }

    public void addMember(String newMember) {
        members.add(newMember);
    }

    @Override
    public String toString() {
        return "Team of " + members;
    }
}
