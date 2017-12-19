package abstractclass;

/**
 * Created by cuiguiyang on 2017/3/18 19:11.
 * Desc
 */
public abstract class Human extends Animal {

    @Override
    Animal move() {
        System.out.println("Human walking with his legs ...");
        return this;
    }

    @Override
    Animal eat() {
        System.out.println("Human eat with tableware.");
        return this;
    }


    enum TableWareType {
        WEST,EAST,NORTH,SOUTH
    }

    class Character {
        String character;
    }

}
