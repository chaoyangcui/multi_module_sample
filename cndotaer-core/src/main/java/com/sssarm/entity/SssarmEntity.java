package com.sssarm.entity;

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

    /*public SssarmEntity() {
    }

    public String getName() {
        return name;
    }

    public SssarmEntity setName(String name) {
        this.name = name;
        return this;
    }

    public int getAge() {
        return age;
    }

    public SssarmEntity setAge(int age) {
        this.age = age;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public SssarmEntity setAddress(String address) {
        this.address = address;
        return this;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public SssarmEntity setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
        return this;
    }

    public Map<String, List<String>> getExternalAttr() {
        return externalAttr;
    }

    public SssarmEntity setExternalAttr(Map<String, List<String>> externalAttr) {
        this.externalAttr = externalAttr;
        return this;
    }*/
}
