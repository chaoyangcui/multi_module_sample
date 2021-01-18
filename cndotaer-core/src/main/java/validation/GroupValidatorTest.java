package validation;

import org.springframework.util.PropertyPlaceholderHelper;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Properties;
import java.util.Set;

/**
 * GroupValidator
 *
 * 分组参数验证
 *
 * @author Eric Cui
 * @since 2020/4/12 17:52
 */
public class GroupValidatorTest {

    public static void main(String[] args) {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        ServiceMessage serviceMessage = ServiceMessage.builder().clickAction("1").clickResponse("https://duiba.com/activity/1#1639287").build();
        Set<ConstraintViolation<ServiceMessage>> constraintViolationSet = validator.validate(serviceMessage);
        System.out.println("constraintViolationSet = " + constraintViolationSet);
        constraintViolationSet.forEach(
                (ConstraintViolation<ServiceMessage> violation) -> {
                    String message = violation.getMessage();
                    Object invalidValue = violation.getInvalidValue();
                    System.out.println("invalidValue = " + invalidValue + " message = " + message);
                });

        final PropertyPlaceholderHelper helper = new PropertyPlaceholderHelper("${", "}", ":", false);

        Properties properties = new Properties();
        properties.put("place1", "值1");
        properties.put("place", "值2");
        String placeholders = helper.replacePlaceholders("placeholders1-->${place1:默认值1}\nplaceholders2-->${place2:}", properties);
        System.out.println(placeholders);
    }
}
