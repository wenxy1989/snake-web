package com.web.${app.code}.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.reflect.AnnotatedElement;
import java.util.Arrays;

@ControllerAdvice(annotations = Controller.class)
public class ResponseBodyHandler implements ResponseBodyAdvice {

    private static ThreadLocal<ObjectMapper> mapperThreadLocal = ThreadLocal.withInitial(ObjectMapper::new);

    private static final Class[] annotations = {
        RequestMapping.class,
        GetMapping.class,
        PostMapping.class,
        DeleteMapping.class,
        PutMapping.class
    };

    public static Object encode(@Nullable Object body) {
        if (null == body) {
            return ResponseBodyResult.SUCCESSFUL;
        } else if (body instanceof ResponseBodyResult) {
            return body;
        } else if (body instanceof String) {
            ResponseBodyResult result = ResponseBodyResult.success((String) body);
            try {
            //因为是String类型，我们要返回Json字符串，否则SpringBoot框架会转换出错
                return mapperThreadLocal.get().writeValueAsString(result);
            } catch (JsonProcessingException e) {
                return ResponseBodyResult.error(2, e.getMessage());
            }
        } else {
            return ResponseBodyResult.success(body);
        }
    }

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        AnnotatedElement element = returnType.getAnnotatedElement();
        return Arrays.stream(annotations).anyMatch(annotation -> annotation.isAnnotation() && element.isAnnotationPresent(annotation));
    }

    @Override
    public Object beforeBodyWrite(@Nullable Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (body instanceof ResponseEntity) {
            return body;
        } else {
            response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
            return encode(body);
        }
    }

}
