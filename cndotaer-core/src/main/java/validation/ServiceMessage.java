package validation;

import lombok.Builder;
import lombok.Data;
import validation.validator.CrossChecker;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 服务消息
 *
 * @author Eric Cui
 * @since 2020/4/12 17:56
 */
@Data
@Builder
@CrossChecker(fields = "clickAction")
public class ServiceMessage {

    @NotNull(message = "点击动作类型不能为空")
    @Size(min = 1, max = 5, message = "点击动作类型:[1, 2, 3]")
    public String clickAction;

    @NotNull(message = "动作响应不能为空")

    public String clickResponse;

}
