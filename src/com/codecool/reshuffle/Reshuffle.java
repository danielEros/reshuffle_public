package com.codecool.reshuffle;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Reshuffle {

    private final List<Team> oldTeams = new ArrayList<>();
    private final List<Team> newTeams = new ArrayList<>();

    public void letsRoll() {
        System.out.println("Some magic happens here");

        if(oldTeams.size()<4){
            System.out.println("Reshuffle cannot be done with less than 4 groups");
            return;
        }
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

    public void testResults(){
        /**
         * Creating formerMembersOf map with key values of each student, the values
         * hold lists of names of former teammates
         */
        Map<String, List<String>> formerMembersOf = new TreeMap<>();
        for(int i=0; i<oldTeams.size(); i++) {
            for (int j=0; j < oldTeams.get(i).getMembers().size(); j++) {
                String key = oldTeams.get(i).getMembers().get(j);
                List<String> valueList = new ArrayList<>();
                for(int k=1; k<4; k++) {
                    String member = oldTeams.get(i).getMembers().get((j + k) % 4);
                    if(!member.equals("empty")){
                        valueList.add(member);
                    }
                }
                formerMembersOf.put(key, valueList);
            }
        }
        System.out.println(formerMembersOf);

        /**
         * For each student check if any of their teammates was their teammate
         * in the previous round
         */
    }

    public static void main(String[] args) {
        Reshuffle reshuffle = new Reshuffle();
        reshuffle.loadOldTeams("resources/teams_sample.txt");
        reshuffle.printOldTeams();
        reshuffle.letsRoll();
        reshuffle.printNewTeams();
        reshuffle.testResults();
    }

}
