package com.example.spring_school.constant;

public enum Gender {
    MALE(1),
    FEMALE(2),
    ORTHER(3);
    public int val;

    Gender(int val) {
        this.val = val;
    }
}
