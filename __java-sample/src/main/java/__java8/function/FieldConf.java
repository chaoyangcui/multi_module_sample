package __java8.function;

import java.util.function.Function;

/**
 * Created by Intellij IDEA.
 * Date  : 2018/1/7 16:42
 *
 * @author Eric Cui
 * Desc  : 描述信息
 */
public enum  FieldConf {
    Id("Id", "编号", Person::getId),
    Name("Name", "姓名", Person::getName),
    Able("Able", "能力", Person::able);

    private String name;
    private String title;
    private Function<Person, String> method;

    FieldConf(String name, String title, Function<Person,String> method) {
        this.name = name;
        this.title = title;
        this.method = method;
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public Function<Person, String> getMethod() {
        return method;
    }
}
