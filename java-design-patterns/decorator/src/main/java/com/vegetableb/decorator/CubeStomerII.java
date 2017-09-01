package com.vegetableb.decorator;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Eric
 * Date    2017/9/1 17:15
 * Desc    Setting | Editor | File and Code Templates
 */
public class CubeStomerII extends RobotDecorator {
    public CubeStomerII(Robot robot) {
        super(robot);
    }

    @Override
    public String action() {
        return super.action() + ", CubeStomerII can solve the cube quickly!!";
    }
}
