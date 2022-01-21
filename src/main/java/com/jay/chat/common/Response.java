package com.jay.chat.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author Jay Yang
 * @date 2022/1/18
 */
@Getter
@RequiredArgsConstructor
public class Response<T> {

    private final int code;

    private final boolean success;

    private final String message;

    private final T data;

    public static <T> Response<T> success(T data) {
        return new Response<>(200, true, "SUCCESS", data);
    }

    public static <T> Response<T> error(int code, String message) {
        return new Response<>(code, false, message, null);
    }

    public static <T> Response<T> error(String message) {
        return error(500, message);
    }

}
