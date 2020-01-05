package com.bailiban.socket.niohttptest2.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Method;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MethodInfo {

    private Method method;
    private String className;
}
