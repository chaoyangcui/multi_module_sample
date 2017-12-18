package entity;

import lombok.Builder;
import lombok.Data;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Eric Cui
 * @date 2017/12/6 16:53
 * Desc    Setting | Editor | File and Code Templates
 */
@Data
@Builder
public class ExampleEntity {
    private int id;
    private String desc;
}
