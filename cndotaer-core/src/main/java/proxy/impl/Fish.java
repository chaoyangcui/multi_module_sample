package proxy.impl;

import proxy.inter.Animal;

/**
 * Created by cuiguiyang on 2017/3/18 16:07.
 * Desc
 */
public class Fish implements Animal {
    @Override
    public void move(String feelings) {
        System.out.println("fish swim ....." + " & " + feelings);
    }
}
