package com.wealthbook.android.utils;

public interface AppConstant {
    String INVALID_AUTH = "INVALID_AUTH";
    int MAX_WIDTH = 1024;
    int MAX_HEIGHT = 768;
    int size = (int) Math.ceil(Math.sqrt(MAX_WIDTH * MAX_HEIGHT));
    int USER_TYPE_ADVISER = 0;
    int USER_TYPE_CLIENT = 1;
}
