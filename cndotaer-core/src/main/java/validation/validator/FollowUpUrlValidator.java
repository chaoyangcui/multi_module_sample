package validation.validator;

import org.springframework.util.StringUtils;
import validation.ServiceMessage;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * FollowUpUrlValidator
 *
 * @author Eric Cui
 * @since 2020/4/12 22:00
 */
public class FollowUpUrlValidator implements ConstraintValidator<CrossChecker, ServiceMessage> {

    private String field;

    @Override
    public void initialize(CrossChecker constraintAnnotation) {
        this.field = constraintAnnotation.fields();
    }

    @Override
    public boolean isValid(ServiceMessage value, ConstraintValidatorContext context) {
        return check(value, context);
    }

    public boolean check(ServiceMessage value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        String messageTemplate = "";
        try {
            Object o = ServiceMessage.class.getField(field).get(value);
            System.out.println("o = " + o);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        switch (value.getClickAction()) {
            case "1":
                System.out.println("兑吧");
                messageTemplate = "兑吧页面设置不正确";
                break;
            case "2":
                System.out.println("问卷网");
                break;
            case "3":
                System.out.println("无参数URL");
                break;
            case "4":
                System.out.println("指定页面");
                break;
            default:
                System.out.println("其他");
                break;
        }
        // messageTemplate = String.format("用户名：%1$s和ID：%2$s不匹配", userVo.getUserName(), userVo.getId());

        if (StringUtils.hasText(messageTemplate)) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(messageTemplate).addConstraintViolation();

            return false;
        }
        return true;
    }
}
