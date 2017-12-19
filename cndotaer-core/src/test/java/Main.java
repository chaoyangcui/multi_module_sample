import com.fasterxml.jackson.core.JsonProcessingException;
import com.sssarm.entity.SssarmEntity;
import org.joda.time.DateTime;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static com.sssarm.util.Util.OBJECT_MAPPER;

/**
 * Created by cuiguiyang on 2017/5/8 23:12.
 * Desc
 */
public class Main {

    /*@Test
    public void test() throws JsonProcessingException {
        // test(new SssarmEntity());
        SssarmEntity entity = SssarmEntity.builder().modifyDate(DateTime.now().toDate()).build();
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<SssarmEntity>> set = validator.validate(entity);
        for (ConstraintViolation<SssarmEntity> violation : set) {
            System.out.println(violation.getPropertyPath().toString() + ":" + violation.getMessage());
        }
        System.out.println(OBJECT_MAPPER.writeValueAsString(entity));
        System.out.println(DateTime.now().toDateTimeISO());
        System.out.println(DateTime.now().isBefore(DateTime.now().plusHours(1)));
    }*/

    public static void main(String[] args) {
        SssarmEntity entity = SssarmEntity.builder().build();
        test(entity);
    }

    private static void test(@Valid SssarmEntity entity) {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<SssarmEntity>> set = validator.validate(entity);
        for (ConstraintViolation<SssarmEntity> violation : set) {
            System.out.println(violation.getPropertyPath().toString() + ":" + violation.getMessage());
        }
        System.out.println(set.size());
        System.out.println(entity.getAddress());
    }

}
