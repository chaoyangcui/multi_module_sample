import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.base.Throwables;
import demo.Base;
import entity.Company;

import javax.validation.ConstraintViolation;
import javax.validation.Path;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 * @author Eric
 * Date 2017/5/9 13:50
 * desc
 */
public class Main extends Base {

    public static void main(String[] args) {
        Company company = Company.builder().ifPublic(true).address("address").aDouble(111.111D).build();
        System.out.println(company.toString());
        try {
            System.out.println(OBJECT_MAPPER.writeValueAsString(company));
        } catch (JsonProcessingException e) {
            System.out.println(Throwables.getStackTraceAsString(e));
            e.printStackTrace();
        }

        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Company>> set = validator.validate(company);
        for (ConstraintViolation<Company> violation : set) {
            Path path = violation.getPropertyPath();
            System.out.println(path.toString() + ": " + violation.getMessage());
        }
    }

}
