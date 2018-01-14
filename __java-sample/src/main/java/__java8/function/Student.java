package __java8.function;

/**
 * Created by Intellij IDEA.
 * Date  : 2018/1/7 16:41
 *
 * @author Eric Cui
 * Desc  : 描述信息
 */
public class Student implements Person {
    private String studentId;
    private String name;
    private String able;

    public Student(String studentId, String name, String able) {
        this.studentId = studentId;
        this.name = name;
        this.able = able;
    }

    @Override
    public String getId() {
        return studentId;
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
