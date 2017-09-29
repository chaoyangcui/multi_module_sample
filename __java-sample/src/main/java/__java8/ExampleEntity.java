package __java8;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Eric
 * Date    2017/9/27 15:31
 * Desc    Setting | Editor | File and Code Templates
 */
public class ExampleEntity {
    private String target;

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public static void main(String[] args) {
        ExampleEntity entity = new ExampleEntity() {{
            setTarget("");
            setTarget("Hello");
            setTarget("World");
        }};

        System.out.println(entity.getTarget());
    }
}
