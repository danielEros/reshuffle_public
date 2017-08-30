package com.codecool.reshuffle;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Reshuffle {

    private final List<Team> oldTeams = new ArrayList<>();
    private final List<Team> newTeams = new ArrayList<>();

    public void letsRoll() {
        System.out.println("Some magic happens here");

        for(int i=0; i<oldTeams.size(); i++) {
            Team newTeam = new Team();
            for(int j=0; j<oldTeams.get(i).getMembers().size(); j++){
                int oldTeamIndex = (i+j) % oldTeams.size();
                newTeam.addMember(oldTeams.get(oldTeamIndex).getMembers().get(j));
            }
            newTeams.add(newTeam);
        }
    }

    public void loadOldTeams(String fileName) {
        oldTeams.clear();
        try {
            Scanner s = new Scanner(new File(fileName));
            Team team = null;
            int counter = 0;
            while (s.hasNext()) {
                String nextLine = s.next();
                if (nextLine.contentEquals("#")) {
                    if (counter < 4){
                        for(int i=0; i<4-counter; i++){
                            team.addMember("empty");
                        }
                    }
                    team = null;
                    counter = 0;
                } else {
                    if (team == null) {
                        team = new Team();
                        oldTeams.add(team);
                    }
                    team.addMember(nextLine);
                    counter++;
                }
            }
            s.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void printOldTeams() {
        System.out.println("Old: " + oldTeams);
    }

    public void printNewTeams() {
        System.out.println("New: " + newTeams);
    }

    public static void main(String[] args) {
        Reshuffle reshuffle = new Reshuffle();
        reshuffle.loadOldTeams("resources/teams_sample.txt");
        reshuffle.printOldTeams();
        reshuffle.letsRoll();
        reshuffle.printNewTeams();
    }

}
