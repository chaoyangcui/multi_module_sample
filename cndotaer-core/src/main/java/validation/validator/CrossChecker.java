package validation.validator;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * CrossCheck
 *
 * <p>交叉验证
 *
 * @author Eric Cui
 * @since 2020/4/12 21:45
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({
    ElementType.METHOD,
    ElementType.FIELD,
    ElementType.ANNOTATION_TYPE,
    ElementType.CONSTRUCTOR,
    ElementType.PARAMETER,
    ElementType.TYPE_USE
})
@Constraint(validatedBy = FollowUpUrlValidator.class)
public @interface CrossChecker {

    String message() default "字段值不符合要求";

    String fields();

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
