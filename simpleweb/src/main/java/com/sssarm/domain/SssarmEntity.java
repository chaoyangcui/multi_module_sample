package com.sssarm.domain;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by cuiguiyang on 2017/3/11 18:34.
 * Desc
 */
@Data(staticConstructor = "of")
@Builder
public class SssarmEntity implements Serializable {
    private String name;
    private int age;
    @NotNull
    @Size(min = 1, max = 10, message = "地址长度必须在范围（1-10）之间")
    private String address;
    @NotNull
    private Date modifyDate;
    private Map<String, List<String>> externalAttr;

}
