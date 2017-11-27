package com.sssarm.entity;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 *
 * @author Eric
 * @since  2017/5/5 11:17
 */

@Data
@Builder
public class Company {
    @Pattern(regexp = "\\w*")
    private String address;
    @NotNull()
    @Size(max = 20, message = "length must be less then 20")
    private String industry;
    public byte aByte;
    public short aShort;
    @NotNull
    public int number;
    public float aFloat;
    public double aDouble;
    public long aLong;
    @AssertTrue
    public boolean ifPublic;
    public char aChar;
}
