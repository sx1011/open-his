package com.bjsxt.config.exception;

import com.bjsxt.vo.AjaxResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 全局异常处理
 */
// 以json的形式返回出去
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 当系统出现MethodArgumentNotValidException异常时，会调用下面的方法
     * @param e
     * @return
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public AjaxResult jsonErrorHandler(MethodArgumentNotValidException e) {
        List<ObjectError> allErrors = e.getBindingResult().getAllErrors();
        // 构造一个数据结构，存放json
        List<Map<String,Object>> list = new ArrayList<>();
        for (ObjectError allError : allErrors) {
            Map<String, Object> map = new HashMap<>();
            map.put("defaultMessage",allError.getDefaultMessage());
            map.put("objectName",allError.getObjectName());
            // 使用allerrors取不到field的值
            // 打断点发现，filed是在ViolationFieldError中，ViolationFieldError是private的但是继承了FieldError
            FieldError fieldError = (FieldError) allError;
            map.put("field", fieldError.getField());
            list.add(map);
        }
        return AjaxResult.fail("后端数据校验异常",list);
    }
}
