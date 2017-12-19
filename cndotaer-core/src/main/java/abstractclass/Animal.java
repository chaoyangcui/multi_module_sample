package abstractclass;

/**
 * Created by cuiguiyang on 2017/3/18 19:10.
 * Desc
 */
public abstract class Animal {

    Animal move() {
        System.out.println("animal moved ...");
        return this;
    }

    abstract Animal eat();

}
