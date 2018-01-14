package __java8.function;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Eric Cui
 *
 * Created by Intellij IDEA.
 * Date  : 2018/1/7 16:44
 * Desc  : 描述信息
 */
public class Report {
    public static void main(String[] args) {
        List<Person> persons = getPersons();
        report(Arrays.asList("Id", "Name"), persons);
        report(Arrays.asList("Name", "Able"), persons);
    }

    public static void report(List<String> fields, List<Person> persons) {
        String reportTitles = fields.stream().map(
                field -> FieldConfAccompany.getInstance(field).getTitle()
        ).collect(Collectors.joining(","));

        List<String> rows = persons.stream().map(
                p -> fields.stream().map(
                        field -> FieldConfAccompany.getInstance(field).getMethod().apply(p)
                ).collect(Collectors.joining(","))
        ).collect(Collectors.toList());

        System.out.println(reportTitles);
        System.out.println(String.join("\n", rows));
    }

    private static List<Person> getPersons() {
        Person s1 = new Student("s1", "liming", "Study");
        Person s2 = new Student("s2", "xueying", "Piano");
        Person t1 = new Teacher("t1", "Mr.Q", "Swim");
        Person t2 = new Teacher("t2", "Mrs.L", "Dance");
        return Arrays.asList(s1, s2, t1, t2);
    }
}
