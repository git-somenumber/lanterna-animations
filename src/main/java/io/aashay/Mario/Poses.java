package io.aashay.Mario;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Poses {
    private List<String> marioPoses = null;

    private Poses() {
        Path marioPath = Paths.get("src/main/resources/mario.txt");
        try {
            marioPoses = Files.readAllLines(marioPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static Poses poses = new Poses();

    public static Poses getPoseI() {
        return poses;
    }

    public String[] getPose(int poseNumber) {
        String[] pose = new String[3];
        for (int i = 0; i < 3; i++) {
            pose[i] = marioPoses.get(poseNumber);
            poseNumber++;
        }
        return pose;
    }
}