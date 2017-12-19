package proxy.impl;

import proxy.inter.Animal;

/**
 * Created by cuiguiyang on 2017/3/18 16:06.
 * Desc
 */
public class Rabbit implements Animal {
    @Override
    public void move(String feelings) {
        System.out.println("rabbit run ....." + " & " + feelings);
    }
}
