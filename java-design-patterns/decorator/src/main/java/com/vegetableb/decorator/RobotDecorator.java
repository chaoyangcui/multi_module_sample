package com.vegetableb.decorator;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Eric
 * Date    2017/9/1 17:11
 * Desc    Setting | Editor | File and Code Templates
 */
public abstract class RobotDecorator implements Robot {

    private Robot robot;

    public RobotDecorator(Robot robot) {
        this.robot = robot;
    }

    @Override
    public String action() {
        return robot.action();
    }
}
