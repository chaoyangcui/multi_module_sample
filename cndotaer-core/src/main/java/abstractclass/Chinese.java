package abstractclass;

/**
 * Created by cuiguiyang on 2017/3/18 19:22.
 * Desc
 */
public class Chinese extends Human {

    @Override
    Animal eat() {
        System.out.println("Chinese eat with chopsticks.");
        return this;
    }

    public static void main(String[] args) {
        new Character1();
        new Chinese().getInner();
    }
    public void getInner() {
        new Character();
    }

    class Character {
        String character;
    }
    static class Character1 {
        String character;
    }

}
