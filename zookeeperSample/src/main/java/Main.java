import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.base.Throwables;
import com.sssarm.entity.Base;
import com.sssarm.entity.Company;

import javax.validation.ConstraintViolation;
import javax.validation.Path;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 * @Author Eric
 * @Date 2017/5/9 13:50
 * @Desc
 */
public class Main extends Base {

    public static void main(String[] args) {
        Company company = Company.builder().ifPublic(true).address("address").aDouble(111.111D).build();
        System.out.println(company.toString());
        try {
            System.out.println(objectMapper.writeValueAsString(company));
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
