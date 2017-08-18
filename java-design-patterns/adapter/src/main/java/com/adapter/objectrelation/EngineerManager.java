package com.adapter.objectrelation;

import com.iluwatar.adapter.Engineer;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Eric
 * Date    2017/8/18 15:14
 * Desc    Setting | Editor | File and Code Templates
 */
class EngineerManager {
    private Engineer engineer;

    EngineerManager(Engineer engineer) {
        this.engineer = engineer;
    }

    void operateDevice() {
        engineer.operateDevice();
    }
}
