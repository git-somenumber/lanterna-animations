package io.aashay.Mario;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class GameCharacter {
    static int nextID = 1;
    int id = 0;
    Poses poses = null;


    public GameCharacter(String poseFile) {
        id = nextID;
        nextID++;
        poses = new Poses(poseFile);
    }

    public String[] move(Pose move){
        return poses.getPose(move);
    }
}

enum Pose {
    STAND, RIGHT, LEFT, DOWN, UP
}


class Poses {
    private List<String> Poses = null;
    Pose pose_enum = Pose.STAND;
    static int[] poseIndex = new int[5];


    public Poses(String poseFile) {
        Path poseFile_Path = Paths.get(poseFile);
        try {
            Poses = Files.readAllLines(poseFile_Path);
            String poseIndexString = Poses.get(0);

            //Ingests the correct line numbers for the pose file into the int[] poseIndex
            for(int i = 0;i<5;i++){
                int x = poseIndexString.indexOf(",");
                poseIndex[i] = Integer.parseInt(poseIndexString.substring(0,x));
                poseIndexString = poseIndexString.substring(x+1);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // public static void main(String[] args) {
    //     Poses poses = new Poses("src/main/resources/mario.txt");
    //     for (int p : poseIndex) {
    //         System.out.println(p);
    //     }
    //     String[] a = poses.getPose(Pose.RIGHT);
    // }
    //  Test apparatus for future use. Prints out values where the poses are located.

    //return a string array containing the pose requested
    public String[] getPose(Pose pose_enum) {
        int poseNumber = useEnum(pose_enum) - 1;
        String[] pose = new String[3];
        for (int i = 0; i < 3; i++) {
            pose[i] = Poses.get(poseNumber);
            poseNumber++;
        }
        return pose;
    }

    private int useEnum(Pose pose_enum){
        switch(pose_enum){
            case STAND:
                return poseIndex[0];
            case RIGHT:
                return poseIndex[1];
            case LEFT:
                return poseIndex[2];
            case DOWN:
                return poseIndex[3];
            case UP:
                return poseIndex[4];
            default:
                return  -1;
        }
    }
}