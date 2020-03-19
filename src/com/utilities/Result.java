package com.utilities;

public enum Result{
    SUCCESS(1),
    FAIL(0),
    WHAT(-1);

    int result;

    Result(int res) {
        this.result = res;
    }
}
