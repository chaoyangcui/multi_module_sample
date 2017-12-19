package com.sssarm.util;

/**
 * Created by Intellij IDEA.
 * Author: Eric Cui
 * Date  : 2017/7/29 18:10
 * Desc  :
 */
public class Calculator {
    class ImmutableValue {
        private int value = 0;

        public ImmutableValue(int value) {
            this.value = value;
        }

        public int getValue() {
            return this.value;
        }

        public ImmutableValue add(int valueToAdd) {
            return new ImmutableValue(this.value + valueToAdd);
        }
    }


    private ImmutableValue currentValue = null;

    public ImmutableValue getValue() {
        return currentValue;
    }


    public void setValue(ImmutableValue newValue) {
        this.currentValue = newValue;
    }

    public void add(int newValue) {
        this.currentValue = this.currentValue.add(newValue);
    }


}
