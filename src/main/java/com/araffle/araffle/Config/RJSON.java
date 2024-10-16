package com.araffle.araffle.Config;

import lombok.Data;

@Data
public class RJSON<T> {

    private boolean success;

    private String message;

    private T data;
}