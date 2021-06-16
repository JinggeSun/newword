package com.item.manager.exception;

import com.item.manager.vo.ResultVO;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 控制器异常监听
 * @author sunjg
 */
@RestControllerAdvice
public class ManagerExceptionAdvice {

    @ExceptionHandler(ManagerException.class)
    public ResultVO<Object> customException(ManagerException e){
        System.out.println("进入异常。。。");
        return ResultVO.builder().code(e.getCode()).message(e.getMessage()).build();
    }
}
