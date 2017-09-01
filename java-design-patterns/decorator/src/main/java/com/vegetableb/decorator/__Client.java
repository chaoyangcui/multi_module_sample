package com.vegetableb.decorator;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Eric
 * Date    2017/9/1 17:16
 * Desc    Setting | Editor | File and Code Templates
 */
public class __Client {
    public static void main(String[] args) {
        Robot robot = new V1Robot();
        println(robot);

        Robot cubeRobot = new CubeRobot(robot);
        println(cubeRobot);

        Robot cubeStomerII = new CubeStomerII(cubeRobot);
        println(cubeStomerII);
    }

    private static void println(Robot robot) {
        System.out.println(robot.action());
    }
}
