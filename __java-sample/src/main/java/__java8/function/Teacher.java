package __java8.function;

/**
 * Created by Intellij IDEA.
 * Date  : 2018/1/7 16:42
 *
 * @author Eric Cui
 * Desc  : 描述信息
 */
public class Teacher implements Person {
    private String teacherId;
    private String name;
    private String able;

    public Teacher(String teacherId, String name, String able) {
        this.teacherId = teacherId;
        this.name = name;
        this.able = able;
    }

    @Override
    public String getId() {
        return teacherId;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String able() {
        return able;
    }
}
