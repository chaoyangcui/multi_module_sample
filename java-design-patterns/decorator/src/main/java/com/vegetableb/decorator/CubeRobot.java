package com.vegetableb.decorator;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Eric
 * Date    2017/9/1 17:13
 * Desc    Setting | Editor | File and Code Templates
 */
public class CubeRobot extends RobotDecorator {
    public CubeRobot(Robot robot) {
        super(robot);
    }

    @Override
    public String action() {
        return super.action() + (", CubeRobot can also solve the Cube");
    }
}
