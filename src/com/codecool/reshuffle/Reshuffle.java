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
    }

    public void loadOldTeams(String fileName) {
        oldTeams.clear();
        try {
            Scanner s = new Scanner(new File(fileName));
            Team team = null;
            while (s.hasNext()) {
                String nextLine = s.next();
                if (nextLine.contentEquals("#")) {
                    team = null;
                } else {
                    if (team == null) {
                        team = new Team();
                        oldTeams.add(team);
                    }
                    team.addMember(nextLine);
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
